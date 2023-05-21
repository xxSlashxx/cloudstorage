package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.ui.Model;

public abstract class BaseController {

    protected void setResultModelAttribute(Model model, String error) {
        if (error == null) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", error);
        }
    }
}