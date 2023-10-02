package com.coolnote.coolnoteapp.api;

import com.coolnote.coolnoteapp.data.Note;
import com.coolnote.coolnoteapp.data.NoteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("notes")
public class NoteEndpoint {
    private final NoteRepository noteRepository;
    public NoteEndpoint(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @RequestMapping
    List<Note> findAll() {
        return noteRepository.findAll();
    }

    @RequestMapping("id/{id}")
    Note findNoteById(@PathVariable long id) throws NoteNotFoundException {
        return noteRepository.findById(id).
                orElseThrow(NoteNotFoundException::new);
    }

    @PostMapping
    Note save(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable long id) {
        noteRepository.deleteById(id);
    }
}
