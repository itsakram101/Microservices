package com.microservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data@AllArgsConstructor
@Schema(
        name = "Error response",
        description = "Schema to hold Error response information"
)
public class ErrorResponseDto {

    @Schema(
            description = "Schema to hold Api path"
    )
    private String apiPath;

    @Schema(
            description = "Schema to hold error code"
    )
    private HttpStatus errorCode;

    private String errorMessage;

    private LocalDateTime errorTime;
}
