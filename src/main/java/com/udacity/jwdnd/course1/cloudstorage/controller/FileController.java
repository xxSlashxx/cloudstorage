package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

    private final FileService fileService;

    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public String addFile(Authentication authentication, @ModelAttribute FileForm fileForm, Model model) {
        System.out.println("@postFile");
        String error = null;
        User user = userService.getUser(authentication.getName());

        if (fileService.filenameExistsForUser(fileForm.getFiledata().getOriginalFilename(), user.getUserid())) {
            error = "The filename already exists.";
        }

        if (error == null) {
            fileForm.setUserid(user.getUserid());
            int rowsAdded = fileService.addFile(fileForm);

            if (rowsAdded < 0) {
                error = "There was an error adding the file. Please try again.";
            }
        }

        setResultModelAttribute(model, error);
        return "result";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable("fileId") Integer fileId, @ModelAttribute FileForm fileForm, Model model) {
        System.out.println("@deleteFile");
        String error = null;
        int rowsDeleted = fileService.deleteFileById(fileId);

        if (rowsDeleted < 1) {
            error = "There was an error deleting the file. Please try again.";
        }

        setResultModelAttribute(model, error);
        return "result";
    }

    @GetMapping(value = "/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(Authentication authentication, @PathVariable("fileId") Integer fileId) {
        User user = userService.getUser(authentication.getName());
        File file = fileService.getUserFile(fileId, user.getUserid());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .contentLength(Long.parseLong(file.getFilesize()))
                .contentType(MediaType.valueOf(file.getContenttype()))
                .body(new ByteArrayResource(file.getFiledata()));

    }
}