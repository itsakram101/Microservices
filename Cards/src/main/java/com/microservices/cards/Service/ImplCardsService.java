package com.microservices.cards.Service;

import com.microservices.cards.Constants.CardsConstants;
import com.microservices.cards.Exception.CardAlreadyExistException;
import com.microservices.cards.Exception.ResourceNotFoundException;
import com.microservices.cards.dto.CardsDto;
import com.microservices.cards.entity.CardsEntity;
import com.microservices.cards.repository.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

import static com.microservices.cards.Mapper.CardsMapper.mapToCards;
import static com.microservices.cards.Mapper.CardsMapper.mapToCardsDto;

@Service
@AllArgsConstructor
public class ImplCardsService implements ICardsService{

    CardsRepository cardsRepository;

    @Override
    @Transactional
    public void createCard(String mobileNumber) {

        cardsRepository.findByMobileNumber(mobileNumber)
                .ifPresent( cardsEntity -> {
                    throw new CardAlreadyExistException
                            ("An account with this mobile number already exist: "+ mobileNumber);
                });
        cardsRepository.save(createNewCard(mobileNumber));

    }

    private CardsEntity createNewCard(String mobileNumber) {
        CardsEntity newCard = new CardsEntity();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    @Transactional(readOnly = true)
    public CardsDto fetchCard(String mobileNumber) {

        CardsEntity getCard = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Card", "mobile number", mobileNumber));
        
        return mapToCardsDto(getCard, new CardsDto());
    }

    @Override
    @Transactional
    public boolean updateCard(CardsDto cardsDto) {

        CardsEntity resultCard = cardsRepository.findByMobileNumber(cardsDto.getMobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Card", "mobile number", cardsDto.getMobileNumber()));

        resultCard.setAvailableAmount(cardsDto.getAvailableAmount());
        resultCard.setAmountUsed(cardsDto.getAmountUsed());
        resultCard.setTotalLimit(cardsDto.getTotalLimit());

        cardsRepository.save(resultCard);

        return true;
    }

    @Override
    @Transactional
    public boolean deleteCard(String mobileNumber) {

        CardsEntity resultCard = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Card", "mobile number", mobileNumber));

        cardsRepository.delete(resultCard);

        return true;
    }
}
