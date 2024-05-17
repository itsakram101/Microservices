package com.microservices.cards.Controller;

import ch.qos.logback.classic.Logger;
import com.microservices.cards.Constants.CardsConstants;
import com.microservices.cards.Service.ICardsService;
import com.microservices.cards.dto.CardsDto;
import com.microservices.cards.dto.ErrorDto;
import com.microservices.cards.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
        name = "Crud Operations for Card Details",
        description = "Use this REST Api for add/update/fetch/delete operations on the Cards")
public class CardsController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(CardsController.class);

    ICardsService iCardsService;

    @Operation(
            description = "creating a Card with a unique mobile number"
    )
    @ApiResponse(
            description = "Http status created",
            responseCode = "201"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam String mobileNumber){

        logger.info("Creating card details for mobile number: {}", mobileNumber);

        iCardsService.createCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201)) ;
    }

    @Operation(
            description = "fetching Card details with a unique mobile number"
    )
    @ApiResponse(
            description = "Http status Ok",
            responseCode = "200"
    )
    @GetMapping("/get")
    public ResponseEntity<CardsDto> fetchCard
            (@RequestParam
             @Pattern(regexp="^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
                     String mobileNumber){

        logger.info("fetching card details for mobile numbre: {}", mobileNumber);

        CardsDto fetchedCard = iCardsService.fetchCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fetchedCard);
    }

    @Operation(
            description = "updating Card's details"
    )
    @ApiResponse(
            description = "Http status Ok",
            responseCode = "200"
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardsDto cardsDto){

        logger.info("Updating card details for card number: {}", cardsDto.getCardNumber());

        iCardsService.updateCard(cardsDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    }

    @Operation(
            description = "deleting a card with unique mobile number"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Http status No Content - Card deleted successfully"
    )
    @DeleteMapping("/delete")
    public  ResponseEntity<Void> deleteCard (@RequestParam String mobileNumber){

        logger.info("Deleting card details for mobile number: {}", mobileNumber);

        iCardsService.deleteCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
