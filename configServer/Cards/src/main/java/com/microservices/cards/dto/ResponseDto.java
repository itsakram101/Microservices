package com.microservices.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Schema(
        description = "Schema tol hold successful response data"
)
public class ResponseDto {

    @Schema(
            description = "Status code for successful response"
    )
    public String statusCode;

    @Schema(
            description = "status message for successful response"
    )
    public String statusMessage;

}
