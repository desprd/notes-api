package com.ilyaproject.notesapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAuthorDTO (
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name max size is 100")
    String name
){}
