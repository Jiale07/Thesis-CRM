package com.jiale.thesis.service;

import com.jiale.thesis.entity.FileInfo;

public interface FileInfoService {

    FileInfo findFileInfoByFileId(Long fileId);

    String findFileNameOldById(Long fileId);

    int addFile(FileInfo fileInfo);

    int updateFileInfo(FileInfo fileInfo);

    int deletedFileLogic(Long fileId);
}
