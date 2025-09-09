package com.ilyaproject.notesapi.repository;

import com.ilyaproject.notesapi.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
