package com.ilyaproject.notesapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateNoteDTO(
        @NotBlank(message = "Title cannot be empty")
        @Size(max = 100, message = "Name max size is 100")
        String title,

        String content,

        @NotNull(message = "Author ID is required")
        Long authorId
) {}
