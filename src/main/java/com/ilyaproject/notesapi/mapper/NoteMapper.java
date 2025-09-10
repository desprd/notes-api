package com.ilyaproject.notesapi.mapper;

import com.ilyaproject.notesapi.dto.CreateNoteDTO;
import com.ilyaproject.notesapi.dto.ReadAuthorDTO;
import com.ilyaproject.notesapi.dto.ReadNoteDTO;
import com.ilyaproject.notesapi.entity.Author;
import com.ilyaproject.notesapi.entity.Note;

public class NoteMapper {
    public static Note mapToNote(CreateNoteDTO createNoteDTO, Author author){
        return Note
                .builder()
                .title(createNoteDTO.title())
                .content(createNoteDTO.content())
                .author(author)
                .build();
    }

    public static ReadNoteDTO mapToReadNoteDTO(Note note){
        return ReadNoteDTO
                .builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .author(new ReadAuthorDTO(note.getAuthor().getId(), note.getAuthor().getName()))
                .build();
    }
}
