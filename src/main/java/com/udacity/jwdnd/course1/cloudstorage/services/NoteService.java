package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int addNote(NoteForm noteForm) {
        try {
            Note note = new Note(null, noteForm.getNotetitle(), noteForm.getNotedescription(), noteForm.getUserid());
            return noteMapper.insert(note);
        } catch (Exception ex) {
            return 0;
        }
    }

    public int updateNote(NoteForm noteForm) {
        try {
            Note note = new Note(noteForm.getNoteid(), noteForm.getNotetitle(), noteForm.getNotedescription(), noteForm.getUserid());
            return noteMapper.update(note);
        } catch (Exception ex) {
            return 0;
        }
    }

    public List<Note> getUsersNotes(Integer userid) {
        return noteMapper.getUsersNotes(userid);
    }

    public int deleteNoteById(Integer noteid) {
        return noteMapper.deleteById(noteid);
    }
}
