package com.ilyaproject.notesapi.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public record ErrorResponseDTO (
     String apiPath,
     HttpStatus errorCode,
     String errorMessage,
     LocalDateTime errorTime
){}
