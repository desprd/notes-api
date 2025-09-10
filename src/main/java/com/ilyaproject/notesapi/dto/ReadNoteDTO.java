package com.ilyaproject.notesapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Schema(
        name = "ReadNote",
        description = "Schema that holds information to read Note"
)
@Builder
public record ReadNoteDTO(
   @Schema(description = "Id of the note", example = "8")
   Long id,

   @Schema(description = "Title of the note", example = "Groceries")
   String title,

   @Schema(description = "Content of the note", example = "Buy something for dinner")
   String content,

   @Schema(description = "Date and time when the note was created", example = "2025-09-10T09:54:39.5047621")
   LocalDateTime createdAt,

   @Schema(
           description = "Author of the note",
           implementation = ReadAuthorDTO.class
   )
   ReadAuthorDTO author
){}
