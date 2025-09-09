package com.ilyaproject.notesapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
        name = "CreateAuthor",
        description = "Schema that holds information to create Author"
)
public record CreateAuthorDTO (
    @Schema(description = "Name of the author", example = "John")
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name max size is 100")
    String name
){}
