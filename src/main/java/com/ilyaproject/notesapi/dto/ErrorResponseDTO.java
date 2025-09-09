package com.ilyaproject.notesapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public record ErrorResponseDTO (
     @Schema(
             description = "API path invoked by client"
     )
     String apiPath,

     @Schema(
             description = "Error code represented the error happened"
     )
     HttpStatus errorCode,

     @Schema(
             description = "Error message represented the error happened"
     )
     String errorMessage,

     @Schema(
             description = "Time representing when the error happened"
     )
     LocalDateTime errorTime
){}
