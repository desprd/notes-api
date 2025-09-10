package com.ilyaproject.notesapi.controller;

import com.ilyaproject.notesapi.constants.NoteConstants;
import com.ilyaproject.notesapi.dto.CreateNoteDTO;
import com.ilyaproject.notesapi.dto.ReadNoteDTO;
import com.ilyaproject.notesapi.dto.ResponseDTO;
import com.ilyaproject.notesapi.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@Validated
@RequiredArgsConstructor
@Slf4j
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/notes")
    public ResponseEntity<ResponseDTO> createNote(@Valid @RequestBody CreateNoteDTO createNoteDTO){
        log.info("POST /notes called with title={}, content={}, authorId={}", createNoteDTO.title(), createNoteDTO.content(), createNoteDTO.authorId());
        noteService.createNote(createNoteDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(NoteConstants.STATUS_201, NoteConstants.MESSAGE_201));
    }

    @GetMapping("/notes")
    public ResponseEntity<List<ReadNoteDTO>> fetchAllNotes(){
        log.info("GET /authors called");
        List<ReadNoteDTO> notes = noteService.fetchAllNotes();
        return ResponseEntity.status(HttpStatus.OK)
                .body(notes);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<ReadNoteDTO> fetchNoteById(@PathVariable long id){
        log.info("GET /notes/{id} called with id={}", id);
        ReadNoteDTO note = noteService.fetchNoteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(note);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<ResponseDTO> removeNoteById(@PathVariable long id){
        log.info("DELETE /notes/{id} called with id={}", id);
        noteService.removeNoteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(NoteConstants.STATUS_200, NoteConstants.MESSAGE_200_DELETE));
    }
}
