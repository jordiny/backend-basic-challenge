package com.jmontanez.challenge.bcp.dto.mapper;

import com.jmontanez.challenge.bcp.dto.ExchangeRateDto;
import com.jmontanez.challenge.bcp.entity.ExchangeRateEntity;

public class ExchangeRateMapper {
    private ExchangeRateMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static ExchangeRateDto toExchangeRateDto(ExchangeRateEntity entity) {
        return ExchangeRateDto.builder()
                .currencyFrom(entity.getCurrencyIdFrom())
                .currencyNameFrom(entity.getCurrencyFrom().getDescription())
                .currencyTo(entity.getCurrencyIdTo())
                .currencyNameTo(entity.getCurrencyTo().getDescription())
                .exchangeRate(entity.getExchangeRate()).build();
    }
}