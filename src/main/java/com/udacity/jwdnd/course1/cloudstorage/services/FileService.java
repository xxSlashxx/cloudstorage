package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int addFile(FileForm fileForm) {
        try {
            MultipartFile filedata = fileForm.getFiledata();
            File file = new File(null, filedata.getOriginalFilename(), filedata.getContentType(), String.valueOf(filedata.getSize()), fileForm.getUserid(), filedata.getBytes());
            return fileMapper.insert(file);
        } catch (Exception ex) {
            return 0;
        }
    }

    public List<File> getUsersFiles(Integer userid) {
        return fileMapper.getUsersFiles(userid);
    }

    public File getUserFile(Integer fileId, Integer userid) {
        return fileMapper.getUserFile(fileId, userid);
    }

    public int deleteFileById(Integer fileId) {
        return fileMapper.deleteById(fileId);
    }

    public boolean filenameExistsForUser(String filename, Integer userid) {
        return fileMapper.filenameExistsForUser(filename, userid);
    }
}