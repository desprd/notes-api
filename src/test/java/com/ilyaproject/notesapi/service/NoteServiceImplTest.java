package com.ilyaproject.notesapi.service;

import com.ilyaproject.notesapi.dto.CreateNoteDTO;
import com.ilyaproject.notesapi.dto.ReadNoteDTO;
import com.ilyaproject.notesapi.entity.Author;
import com.ilyaproject.notesapi.entity.Note;
import com.ilyaproject.notesapi.exception.AuthorNotFoundException;
import com.ilyaproject.notesapi.exception.NoteNotFoundException;
import com.ilyaproject.notesapi.repository.AuthorRepository;
import com.ilyaproject.notesapi.repository.NoteRepository;
import com.ilyaproject.notesapi.service.Impl.NoteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks NoteServiceImpl service;

    private static ArgumentMatcher<Note> noteMatches(CreateNoteDTO dto, Author author) {
        return n -> n != null
                && dto.title().equals(n.getTitle())
                && dto.content().equals(n.getContent())
                && n.getAuthor() != null
                && n.getAuthor().getId().equals(author.getId());
    }

    @Test
    void createNote_savesWithResolvedAuthor() {
        var dto = new CreateNoteDTO("Title", "Body", 1L);
        var author = Author.builder().id(1L).name("John").build();

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(noteRepository.save(any(Note.class))).thenAnswer(inv -> inv.getArgument(0));

        service.createNote(dto);

        verify(authorRepository).findById(1L);
        verify(noteRepository).save(argThat(noteMatches(dto, author)));
    }

    @Test
    void createNote_authorNotFound_throws() {
        var dto = new CreateNoteDTO("Title", "Body", 99L);
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.createNote(dto))
                .isInstanceOf(AuthorNotFoundException.class);

        verify(noteRepository, never()).save(any());
    }

    @Test
    void fetchAllNotes_mapsEntitiesToDTO() {
        var author = Author.builder().id(1L).name("John").build();
        var n1 = Note.builder().id(10L).title("T1").content("C1").author(author)
                .createdAt(LocalDateTime.now()).build();
        var n2 = Note.builder().id(11L).title("T2").content("C2").author(author)
                .createdAt(LocalDateTime.now()).build();

        when(noteRepository.findAll()).thenReturn(List.of(n1, n2));

        List<ReadNoteDTO> dto = service.fetchAllNotes();

        assertThat(dto).hasSize(2);
        assertThat(dto).extracting(ReadNoteDTO::title).containsExactly("T1", "T2");
    }

    @Test
    void fetchNoteById_returnsDTO() {
        var author = Author.builder().id(1L).name("John").build();
        var note = Note.builder().id(10L).title("T1").content("C1").author(author)
                .createdAt(LocalDateTime.now()).build();

        when(noteRepository.findById(10L)).thenReturn(Optional.of(note));

        ReadNoteDTO dto = service.fetchNoteById(10L);

        assertThat(dto.id()).isEqualTo(10L);
        assertThat(dto.title()).isEqualTo("T1");
    }

    @Test
    void fetchNoteById_notFound_throws() {
        when(noteRepository.findById(123L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.fetchNoteById(123L))
                .isInstanceOf(NoteNotFoundException.class);
    }

    @Test
    void removeNoteById_deletesEntity() {
        var author = Author.builder().id(1L).name("John").build();
        var note = Note.builder().id(7L).title("T").author(author).build();

        when(noteRepository.findById(7L)).thenReturn(Optional.of(note));

        service.removeNoteById(7L);

        verify(noteRepository).delete(note);
    }

    @Test
    void removeNoteById_notFound_throws() {
        when(noteRepository.findById(7L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.removeNoteById(7L))
                .isInstanceOf(NoteNotFoundException.class);

        verify(noteRepository, never()).delete(any());
    }

}