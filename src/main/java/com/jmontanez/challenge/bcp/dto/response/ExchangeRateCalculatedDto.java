package com.jmontanez.challenge.bcp.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ExchangeRateCalculatedDto extends ExchangeRateBaseDto {
    private double amount;
    private double calculatedAmount;
}
