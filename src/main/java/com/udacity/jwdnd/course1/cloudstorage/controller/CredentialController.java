package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController extends BaseController {

    private final CredentialService credentialService;

    private final UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping
    public String addCredential(Authentication authentication, @ModelAttribute CredentialForm credentialForm, Model model) {
        String error = null;

        if (credentialForm.getCredentialid() == null) {
            User user = userService.getUser(authentication.getName());
            credentialForm.setUserid(user.getUserid());
            int rowsAdded = credentialService.addCredential(credentialForm);

            if (rowsAdded < 0) {
                error = "There was an error adding the credential. Please try again.";
            }
        } else {
            int rowsUpdated = credentialService.updateCredential(credentialForm);

            if (rowsUpdated < 0) {
                error = "There was an error updating the credential. Please try again.";
            }
        }

        setResultModelAttribute(model, error);
        return "result";
    }

    @GetMapping("/delete/{credentialid}")
    public String deleteCredential(@PathVariable("credentialid") Integer credentialid, @ModelAttribute CredentialForm credentialForm, Model model) {
        String error = null;
        int rowsDeleted = credentialService.deleteCredentialById(credentialid);

        if (rowsDeleted < 1) {
            error = "There was an error deleting the credential. Please try again.";
        }

        setResultModelAttribute(model, error);
        return "result";
    }
}
