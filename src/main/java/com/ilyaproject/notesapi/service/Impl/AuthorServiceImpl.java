package com.ilyaproject.notesapi.service.Impl;

import com.ilyaproject.notesapi.dto.CreateAuthorDTO;
import com.ilyaproject.notesapi.dto.ReadAuthorDTO;
import com.ilyaproject.notesapi.entity.Author;
import com.ilyaproject.notesapi.exception.AuthorNotFoundException;
import com.ilyaproject.notesapi.repository.AuthorRepository;
import com.ilyaproject.notesapi.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    public void createAuthor(CreateAuthorDTO createAuthorDTO) {
        Author author = Author.builder()
                .name(createAuthorDTO.name())
                .build();
        repository.save(author);
    }

    @Override
    public List<ReadAuthorDTO> fetchAllAuthors() {
        List<Author> authors = repository.findAll();
        return authors
                .stream()
                .map(author -> new ReadAuthorDTO(author.getId(), author.getName())).toList();
    }

    @Override
    public ReadAuthorDTO fetchAuthorById(long id) {
        Author author = repository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author with id " + id + " was not found"));
        return new ReadAuthorDTO(author.getId(), author.getName());
    }
}
