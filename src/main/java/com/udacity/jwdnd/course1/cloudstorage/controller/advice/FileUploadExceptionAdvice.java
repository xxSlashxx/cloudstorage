package com.udacity.jwdnd.course1.cloudstorage.controller.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FileUploadExceptionAdvice {

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public String handleMaxUploadSizeExceededException(Model model) {
        model.addAttribute("error", "File size limit exceeded");
        return "result";
    }
}