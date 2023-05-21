package com.udacity.jwdnd.course1.cloudstorage.model;

import org.springframework.web.multipart.MultipartFile;

public class FileForm {

    private MultipartFile filedata;

    private Integer userid;

    public MultipartFile getFiledata() {
        return filedata;
    }

    public void setFiledata(MultipartFile filedata) {
        this.filedata = filedata;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}