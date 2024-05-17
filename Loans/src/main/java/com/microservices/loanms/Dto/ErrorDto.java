package com.microservices.loanms.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Schema(
        description = "schema to hold error response information"
)
public class ErrorDto {

    @Schema(
            description = "Schema to hold Api path"
    )
    public String apiPath;

    public HttpStatus errorCode;

    public String errorMessage;

    public LocalDateTime errorTime;
}