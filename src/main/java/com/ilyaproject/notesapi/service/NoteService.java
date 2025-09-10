package com.ilyaproject.notesapi.service;

import com.ilyaproject.notesapi.dto.CreateNoteDTO;
import com.ilyaproject.notesapi.dto.ReadNoteDTO;

import java.util.List;

public interface NoteService {
    void createNote(CreateNoteDTO createNoteDTO);
    List<ReadNoteDTO> fetchAllNotes();
    ReadNoteDTO fetchNoteById(long id);
    void removeNoteById(long id);
}
