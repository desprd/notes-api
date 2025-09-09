package com.ilyaproject.notesapi.service;

import com.ilyaproject.notesapi.dto.CreateAuthorDTO;
import com.ilyaproject.notesapi.dto.ReadAuthorDTO;

import java.util.List;

public interface AuthorService {
    void createAuthor(CreateAuthorDTO createAuthorDTO);
    List<ReadAuthorDTO> fetchAllAuthors();
    ReadAuthorDTO fetchAuthorById(long id);
}
