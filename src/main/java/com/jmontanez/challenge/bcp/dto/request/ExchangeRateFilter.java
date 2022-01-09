package com.jmontanez.challenge.bcp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateFilter {
    private String currencyFrom;
    private String currencyTo;
    private double amount;
}
