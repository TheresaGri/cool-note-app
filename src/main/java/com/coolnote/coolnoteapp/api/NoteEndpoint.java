package com.coolnote.coolnoteapp.api;

import com.coolnote.coolnoteapp.data.Note;
import com.coolnote.coolnoteapp.data.NoteRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PutMapping("/{id}")
    ResponseEntity<Note> updateNote(@PathVariable long id, @RequestBody Note noteDetails) throws NoteNotFoundException {
        Note updateNote = noteRepository.findById(id)
                .orElseThrow(NoteNotFoundException::new);

        updateNote.setDescription(noteDetails.getDescription());
        noteRepository.save(updateNote);
        return ResponseEntity.ok(updateNote);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> partialUpdateGeneric(
            @RequestBody Map<String, Object> updates,
            @PathVariable("id") long id) throws NoteNotFoundException {

        // Fetch the existing Note entity by ID
        Note existingNote = noteRepository.findById(id)
                .orElseThrow(NoteNotFoundException::new);

        // Apply updates from the map
        if (updates.containsKey("description")) {
            existingNote.setDescription((String) updates.get("description"));
        }

        // Save the updated Note entity
        noteRepository.save(existingNote);

        return ResponseEntity.ok("Resource updated");
    }
}
