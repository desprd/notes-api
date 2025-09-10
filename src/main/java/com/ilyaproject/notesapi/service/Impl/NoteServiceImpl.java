package com.ilyaproject.notesapi.service.Impl;

import com.ilyaproject.notesapi.dto.CreateNoteDTO;
import com.ilyaproject.notesapi.dto.ReadNoteDTO;
import com.ilyaproject.notesapi.entity.Author;
import com.ilyaproject.notesapi.entity.Note;
import com.ilyaproject.notesapi.exception.AuthorNotFoundException;
import com.ilyaproject.notesapi.exception.NoteNotFoundException;
import com.ilyaproject.notesapi.mapper.NoteMapper;
import com.ilyaproject.notesapi.repository.AuthorRepository;
import com.ilyaproject.notesapi.repository.NoteRepository;
import com.ilyaproject.notesapi.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void createNote(CreateNoteDTO createNoteDTO) {
        Author author =  authorRepository
                .findById(createNoteDTO
                        .authorId())
                .orElseThrow(() -> new AuthorNotFoundException("Author with id " + createNoteDTO.authorId() + " was not found"));
        Note note = NoteMapper.mapToNote(createNoteDTO, author);
        noteRepository.save(note);
        log.info("Note with title={}, content={}, authorId={} was created", createNoteDTO.title(), createNoteDTO.content(), createNoteDTO.authorId());
    }

    @Override
    public List<ReadNoteDTO> fetchAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return notes.stream().map(NoteMapper::mapToReadNoteDTO).toList();
    }

    @Override
    public ReadNoteDTO fetchNoteById(long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note with id " + id + " was not found"));
        return NoteMapper.mapToReadNoteDTO(note);
    }

    @Override
    public void removeNoteById(long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note with id " + id + " was not found"));
        noteRepository.delete(note);
        log.info("Note with id={} was removed", id);
    }
}
