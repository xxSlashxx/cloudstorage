package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController extends BaseController {

    private final NoteService noteService;

    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping
    public String addNote(Authentication authentication, @ModelAttribute NoteForm noteForm, Model model) {
        String error = null;

        if (noteForm.getNoteid() == null) {
            User user = userService.getUser(authentication.getName());
            noteForm.setUserid(user.getUserid());
            int rowsAdded = noteService.addNote(noteForm);

            if (rowsAdded < 0) {
                error = "There was an error adding the note. Please try again.";
            }
        } else {
            int rowsUpdated = noteService.updateNote(noteForm);

            if (rowsUpdated < 0) {
                error = "There was an error updating the note. Please try again.";
            }
        }

        setResultModelAttribute(model, error);
        return "result";
    }

    @GetMapping("/delete/{noteid}")
    public String deleteNote(@PathVariable("noteid") Integer noteid, @ModelAttribute NoteForm noteForm, Model model) {
        String error = null;
        int rowsDeleted = noteService.deleteNoteById(noteid);

        if (rowsDeleted < 1) {
            error = "There was an error deleting the note. Please try again.";
        }

        setResultModelAttribute(model, error);
        return "result";
    }
}