package com.ilyaproject.notesapi.controller;

import com.ilyaproject.notesapi.constants.AuthorConstants;
import com.ilyaproject.notesapi.dto.CreateAuthorDTO;
import com.ilyaproject.notesapi.dto.ErrorResponseDTO;
import com.ilyaproject.notesapi.dto.ReadAuthorDTO;
import com.ilyaproject.notesapi.dto.ResponseDTO;
import com.ilyaproject.notesapi.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Authors",
        description = "CRUD endpoints for managing authors: create, fetch all and fetch by ID"
)
@RestController
@RequestMapping(path = "/api/v1")
@Validated
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @Operation(
            summary = "Create author REST API endpoint",
            description = "Endpoint to create new author inside Notes API"
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
    @PostMapping("/authors")
    public ResponseEntity<ResponseDTO> createAuthor(@Valid @RequestBody CreateAuthorDTO createAuthorDTO){
        authorService.createAuthor(createAuthorDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(AuthorConstants.STATUS_201, AuthorConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch all authors REST API endpoint",
            description = "Endpoint to fetch all authors inside Notes API"
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
    @GetMapping("/authors")
    public ResponseEntity<List<ReadAuthorDTO>> fetchAllAuthors(){
        List<ReadAuthorDTO> authors = authorService.fetchAllAuthors();
        return ResponseEntity.status(HttpStatus.OK).body(authors);
    }

    @Operation(
            summary = "Fetch author by id REST API endpoint",
            description = "Endpoint to fetch author by id inside Notes API"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status Created"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping("/authors/{id}")
    public ResponseEntity<ReadAuthorDTO> fetchAuthorById(@PathVariable long id){
        ReadAuthorDTO author = authorService.fetchAuthorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(author);
    }
}
