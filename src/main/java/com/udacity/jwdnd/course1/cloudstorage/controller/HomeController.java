package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;

    private final FileService fileService;

    private final NoteService noteService;

    private final CredentialService credentialService;

    public HomeController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String homeView(Authentication authentication, @ModelAttribute FileForm fileForm, @ModelAttribute NoteForm noteForm, @ModelAttribute CredentialForm credentialForm, Model model) {
        User user = userService.getUser(authentication.getName());
        List<File> files = fileService.getUsersFiles(user.getUserid());
        List<Note> notes = noteService.getUsersNotes(user.getUserid());
        List<Credential> credentials = credentialService.getUsersCredentials(user.getUserid());
        model.addAttribute("files", files);
        model.addAttribute("notes", notes);
        model.addAttribute("credentials", credentials);
        return "home";
    }
}