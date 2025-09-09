package com.ilyaproject.notesapi.service;

import com.ilyaproject.notesapi.dto.CreateAuthorDTO;
import com.ilyaproject.notesapi.dto.ReadAuthorDTO;
import com.ilyaproject.notesapi.entity.Author;
import com.ilyaproject.notesapi.exception.AuthorNotFoundException;
import com.ilyaproject.notesapi.repository.AuthorRepository;
import com.ilyaproject.notesapi.service.Impl.AuthorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository repository;

    @InjectMocks
    private AuthorServiceImpl service;

    @Test
    void createAuthor_saves() {
        var dto = new CreateAuthorDTO("John");
        service.createAuthor(dto);
        verify(repository).save(argThat(a -> a.getName().equals("John")));
    }

    @Test
    void fetchAllAuthors_mapsToDto() {
        when(repository.findAll()).thenReturn(List.of(
                Author.builder().id(1L).name("John").build(),
                Author.builder().id(2L).name("James").build()
        ));

        List<ReadAuthorDTO> result = service.fetchAllAuthors();
        assertThat(result).extracting(ReadAuthorDTO::name).containsExactly("John", "James");
    }

    @Test
    void fetchAuthorById_throws_notFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.fetchAuthorById(99L))
                .isInstanceOf(AuthorNotFoundException.class);
    }
}
