package com.ilyaproject.notesapi.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReadNoteDTO(
   Long id,
   String title,
   String content,
   LocalDateTime createdAt,
   ReadAuthorDTO author
){}
