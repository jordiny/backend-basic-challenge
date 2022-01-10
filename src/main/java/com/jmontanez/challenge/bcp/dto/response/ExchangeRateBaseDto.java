package com.jmontanez.challenge.bcp.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class ExchangeRateBaseDto {
    private String currencyFrom;
    private String currencyTo;
    private double exchangeRate;
}
