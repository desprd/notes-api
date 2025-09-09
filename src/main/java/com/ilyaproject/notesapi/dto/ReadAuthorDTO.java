package com.ilyaproject.notesapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "ReadAuthor",
        description = "Schema that holds information to read Author"
)
public record ReadAuthorDTO (
    @Schema(description = "Id of the author", example = "28")
    Long id,
    @Schema(description = "Name of the author", example = "John")
    String name

){}
