package com.ilyaproject.notesapi.controller;

import com.ilyaproject.notesapi.constants.NoteConstants;
import com.ilyaproject.notesapi.dto.CreateNoteDTO;
import com.ilyaproject.notesapi.dto.ErrorResponseDTO;
import com.ilyaproject.notesapi.dto.ReadNoteDTO;
import com.ilyaproject.notesapi.dto.ResponseDTO;
import com.ilyaproject.notesapi.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Notes",
        description = "CRUD endpoints for managing notes: create, fetch all, fetch by ID and remove by ID"
)
@RestController
@RequestMapping(path = "/api/v1")
@Validated
@RequiredArgsConstructor
@Slf4j
public class NoteController {

    private final NoteService noteService;
    @Operation(
            summary = "Create note REST API endpoint",
            description = "Endpoint to create new note inside Notes API"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status Created"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PostMapping("/notes")
    public ResponseEntity<ResponseDTO> createNote(@Valid @RequestBody CreateNoteDTO createNoteDTO){
        log.info("POST /notes called with title={}, content={}, authorId={}", createNoteDTO.title(), createNoteDTO.content(), createNoteDTO.authorId());
        noteService.createNote(createNoteDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(NoteConstants.STATUS_201, NoteConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch all notes REST API endpoint",
            description = "Endpoint to fetch all notes inside Notes API"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping("/notes")
    public ResponseEntity<List<ReadNoteDTO>> fetchAllNotes(){
        log.info("GET /notes called");
        List<ReadNoteDTO> notes = noteService.fetchAllNotes();
        return ResponseEntity.status(HttpStatus.OK)
                .body(notes);
    }

    @Operation(
            summary = "Fetch note by id REST API endpoint",
            description = "Endpoint to fetch note by id inside Notes API"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping("/notes/{id}")
    public ResponseEntity<ReadNoteDTO> fetchNoteById(@PathVariable long id){
        log.info("GET /notes/{id} called with id={}", id);
        ReadNoteDTO note = noteService.fetchNoteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(note);
    }

    @Operation(
            summary = "Remove note by id REST API endpoint",
            description = "Endpoint to delete note by id inside Notes API"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<ResponseDTO> removeNoteById(@PathVariable long id){
        log.info("DELETE /notes/{id} called with id={}", id);
        noteService.removeNoteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(NoteConstants.STATUS_200, NoteConstants.MESSAGE_200_DELETE));
    }
}
