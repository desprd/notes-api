package com.ilyaproject.notesapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Response",
        description = "Standard response schema containing status code and message"
)
public record ResponseDTO (
    @Schema(description = "Status code", example = "201")
    String statusCode,
    @Schema(description = "Response message", example = "Author was created successfully")
    String message
){}