package com.ilyaproject.notesapi.controller;

import com.ilyaproject.notesapi.dto.ReadAuthorDTO;
import com.ilyaproject.notesapi.dto.ReadNoteDTO;
import com.ilyaproject.notesapi.service.NoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private NoteService noteService;

    @Test
    void createNote_returns201_andResponseDTO() throws Exception {
        mvc.perform(post("/api/v1/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    { "title": "T", "content": "C", "authorId": 1 }
                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value("201"))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void fetchAllNotes_returns200_andArray() throws Exception {
        var author = new ReadAuthorDTO(1L, "John");
        var n1 = new ReadNoteDTO(10L, "T1", "C1", LocalDateTime.now(), author);
        var n2 = new ReadNoteDTO(11L, "T2", "C2", LocalDateTime.now(), author);

        Mockito.when(noteService.fetchAllNotes()).thenReturn(List.of(n1, n2));

        mvc.perform(get("/api/v1/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(10))
                .andExpect(jsonPath("$[0].title").value("T1"))
                .andExpect(jsonPath("$[0].author.id").value(1))
                .andExpect(jsonPath("$[1].title").value("T2"));
    }

    @Test
    void fetchNoteById_returns200_andBody() throws Exception {
        var author = new ReadAuthorDTO(1L, "John");
        var note = new ReadNoteDTO(10L, "T", "C", LocalDateTime.now(), author);

        Mockito.when(noteService.fetchNoteById(10L)).thenReturn(note);

        mvc.perform(get("/api/v1/notes/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.title").value("T"))
                .andExpect(jsonPath("$.author.name").value("John"));
    }

    @Test
    void fetchNoteById_notFound_returns404() throws Exception {
        Mockito.when(noteService.fetchNoteById(anyLong()))
                .thenThrow(new com.ilyaproject.notesapi.exception.NoteNotFoundException("not found"));

        mvc.perform(get("/api/v1/authors/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.apiPath").value("uri=/api/v1/authors/99"))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.errorMessage").exists())
                .andExpect(jsonPath("$.errorTime").exists());
    }

    @Test
    void deleteNote_returns200_andResponseDto() throws Exception {
        mvc.perform(delete("/api/v1/notes/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.message").exists());
    }
}
