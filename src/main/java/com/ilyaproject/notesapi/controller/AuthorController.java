package com.ilyaproject.notesapi.controller;

import com.ilyaproject.notesapi.constants.AuthorConstants;
import com.ilyaproject.notesapi.dto.CreateAuthorDTO;
import com.ilyaproject.notesapi.dto.ReadAuthorDTO;
import com.ilyaproject.notesapi.dto.ResponseDTO;
import com.ilyaproject.notesapi.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
@Validated
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/authors")
    public ResponseEntity<ResponseDTO> createAuthor(@Valid @RequestBody CreateAuthorDTO createAuthorDTO){
        authorService.createAuthor(createAuthorDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(AuthorConstants.STATUS_201, AuthorConstants.MESSAGE_201));
    }

    @GetMapping("/authors")
    public ResponseEntity<List<ReadAuthorDTO>> fetchAllAuthors(){
        List<ReadAuthorDTO> authors = authorService.fetchAllAuthors();
        return ResponseEntity.status(HttpStatus.OK).body(authors);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<ReadAuthorDTO> fetchAuthorById(@PathVariable long id){
        ReadAuthorDTO author = authorService.fetchAuthorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(author);
    }
}
