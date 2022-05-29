package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.FileInfo;
import com.jiale.thesis.mapper.FileInfoMapper;
import com.jiale.thesis.service.FileInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Resource
    FileInfoMapper fileInfoMapper;

    @Override
    public FileInfo findFileInfoByFileId(Long fileId) {
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",fileId);
        queryWrapper.eq("is_deleted",0);
        return fileInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public String findFileNameOldById(Long fileId) {
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",fileId);
        queryWrapper.eq("is_deleted",0);
        return fileInfoMapper.selectOne(queryWrapper).getFileNameOld();
    }

    @Override
    public int addFile(FileInfo fileInfo) {
        return fileInfoMapper.insert(fileInfo);
    }

    @Override
    public int updateFileInfo(FileInfo fileInfo) {
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",fileInfo.getId());
        queryWrapper.eq("is_deleted",0);
        return fileInfoMapper.update(fileInfo,queryWrapper);
    }

    @Override
    public int deletedFileLogic(Long fileId) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(fileId);
        fileInfo.setIsDeleted(1);
        return fileInfoMapper.updateById(fileInfo);
    }
}
