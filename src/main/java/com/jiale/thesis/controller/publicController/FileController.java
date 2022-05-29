package com.jiale.thesis.controller.publicController;

import com.jiale.thesis.entity.FileInfo;
import com.jiale.thesis.service.FileInfoService;
import com.jiale.thesis.util.Result;
import com.jiale.thesis.util.SnowflakeUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/public")
public class FileController {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(FileController.class);
    private static final String fileDir = "D:\\upload";
    @Value("${images-save-path}")
    private String fileSavePath;
    private static final String rootPath = fileDir+File.separator;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

    SnowflakeUtil snowflakeUtil = new SnowflakeUtil();

    @Autowired
    FileInfoService fileInfoService;

    @RequestMapping("/upload")
    @ResponseBody
    public Result<Object> upload(@RequestParam("file") MultipartFile[] multipartFiles, Long userId, final HttpServletResponse response, final HttpServletRequest request){
        Result<Object> result = new Result<>();

        File fileDir = new File(rootPath);
        if (!fileDir.exists() && !(fileDir.isDirectory())){
            fileDir.mkdirs();
        }
        try{
            if (null!=multipartFiles && multipartFiles.length>0){
                for (MultipartFile multipartFile : multipartFiles) {
                    try {
                        FileInfo fileInfo = new FileInfo();
                        String newFileName = Long.toString(snowflakeUtil.getSnowflakeId());
                        String suffixName = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.'));
                        String storagePath = rootPath+ newFileName +suffixName;
                        fileInfo.setFileNameOld(multipartFile.getOriginalFilename());
                        fileInfo.setFileNameNew(newFileName);
                        fileInfo.setFilePath(storagePath);
                        fileInfo.setFileSize(multipartFile.getSize());
                        fileInfo.setFileType(multipartFile.getContentType());
                        fileInfo.setUploaderId(userId);
                        fileInfo.setIsDeleted(0);
                        fileInfo.setDownloads(0);
                        int isAdd = fileInfoService.addFile(fileInfo);
                        if (isAdd == 1) {
                            Streams.copy(multipartFile.getInputStream(), new FileOutputStream(storagePath), true);
                            result.setMessage("添加成功");
                            result.setResultCode(200);
                            result.setData(fileInfo);
                            return result;
                        }
                    } catch (IOException e) {
                        // 返回存储失败的结果到前端
                        result.setResultCode(500);
                        result.setData(ExceptionUtils.getFullStackTrace(e));
                        result.setMessage("服务异常，未能完成请求");
                        return result;
                    }
                }
            }
        }catch (Exception e){
            result.setMessage(e.getMessage());
            return result;
        }
        result.setMessage("上传成功！");
        return result;
    }


    @RequestMapping("/download")
    public Result<FileInfo> downloadFile(@RequestParam("fileId") Long fileId, final HttpServletResponse response, final HttpServletRequest request){
        Result<FileInfo> result = new Result<FileInfo>();
        OutputStream os = null;
        InputStream is = null;
        logger.info("["+fileId+"]");
        FileInfo fileInfo = fileInfoService.findFileInfoByFileId(fileId);
        if (fileInfo==null){
            result.setMessage("没有找到该文件");
            result.setResultCode(500);
            return result;
        }
        try {
            File f = new File(fileInfo.getFilePath());
            String fileNameOld = fileInfo.getFileNameOld();
            // 清空输出流
            response.reset();
            response.setContentType("application/"+fileInfo.getFileType());
            response.setCharacterEncoding("utf-8");
            response.setContentLength((int)f.length());
            response.setHeader("Content-Disposition", "attachment;");
            //读取流
            is = new FileInputStream(f);
            if (is == null) {
                result.setMessage("下载附件失败，请检查文件“" + fileNameOld + "”是否存在");
                result.setResultCode(500);
                return result;
            }
            //复制
            byte[] readBytes = FileCopyUtils.copyToByteArray(f);
            // 取得输出流
            os = response.getOutputStream();
            os.write(readBytes);
        } catch (IOException e) {
            result.setMessage("下载附件失败,error:"+e.getMessage());
            return result;
        }
        //文件的关闭放在finally中
        finally
        {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
//                logger.error(ExceptionUtils.getFullStackTrace(e));
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
//                logger.error(ExceptionUtils.getFullStackTrace(e));
            }
        }
        return null;
    }

    @RequestMapping("/postFileNameOldByFileInfoId")
    @ResponseBody
    public Result<String> postFileNameOldByFileInfoId(Long fileInfoId){
        Result<String> result = new Result<>();
        System.out.println(fileInfoId);
        result.setResultCode(200);
        result.setData(fileInfoService.findFileNameOldById(fileInfoId));
        return result;
    }

    @RequestMapping("/postDeletedFileLogic")
    @ResponseBody
    public Result postDeletedFileLogic(Long fileId){
        Result result = new Result();
        int isDeleted = fileInfoService.deletedFileLogic(fileId);
        if (isDeleted==1){
            result.setResultCode(200);
            result.setMessage("删除成功");
        }else{
            result.setResultCode(500);
            result.setMessage("删除失败");
        }
        return result;
    }

    @RequestMapping("/uploadImage")
    @ResponseBody
    public Result<String> uploadImage(MultipartFile file, Long userId, HttpServletRequest request){
        Result<String> result = new Result<String>();
        //1.后半段目录：  20200315
        String directory = sdf.format(new Date());
        /*
         *  2.文件保存目录  E:/images/2020/03/15/
         *  如果目录不存在，则创建
         */
        String saveDirectory= fileSavePath+directory;
        File dir = new File(saveDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //3.给文件重新设置一个名字
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName= directory+snowflakeUtil.getSnowflakeId()+suffix;
        //4.创建这个新文件
        String saveFilePath = saveDirectory +"/"+ newFileName;
        File newFile = new File(saveFilePath);
        //5.复制操作+文件存储信息存入数据库
        try {
            file.transferTo(newFile);
            //协议 :// ip地址 ：端口号 / 文件目录(/images/2020/03/15/xxx.jpg)
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + directory+"/"+ newFileName;
            logger.info("图片上传，访问URL：" + url);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileNameOld(file.getOriginalFilename());
            fileInfo.setFileNameNew(newFileName);
            fileInfo.setFileSize(file.getSize());
            fileInfo.setFilePath(saveFilePath);
            fileInfo.setFileType(file.getContentType());
            fileInfo.setUploaderId(userId);
            fileInfo.setUrl(url);
            fileInfo.setIsDeleted(0);
            fileInfo.setDownloads(0);
            fileInfoService.addFile(fileInfo);
            result.setResultCode(200);
            result.setData(url);
            result.setMessage("上传成功");
        } catch (IOException e) {
            result.setResultCode(500);
            result.setData(null);
            result.setMessage("IO异常");
        }
        return result;
    }
}
