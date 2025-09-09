package com.ilyaproject.notesapi.repository;

import com.ilyaproject.notesapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
