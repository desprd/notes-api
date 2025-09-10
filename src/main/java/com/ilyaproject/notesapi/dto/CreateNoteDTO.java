package com.ilyaproject.notesapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Schema(
        name = "CreateNote",
        description = "Schema that holds information to create Note"
)
@Builder
public record CreateNoteDTO(
        @NotBlank(message = "Title cannot be empty")
        @Size(max = 100, message = "Name max size is 100")
        @Schema(description = "Title of the note", example = "Groceries")
        String title,

        @Schema(description = "Content of the note", example = "Buy something for dinner")
        String content,

        @Schema(description = "ID of the author who owns this note", example = "3")
        @NotNull(message = "Author ID is required")
        Long authorId
) {}
