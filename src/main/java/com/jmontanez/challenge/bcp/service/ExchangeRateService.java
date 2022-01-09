package com.jmontanez.challenge.bcp.service;

import com.jmontanez.challenge.bcp.dto.ExchangeRateBaseDto;
import com.jmontanez.challenge.bcp.dto.ExchangeRateCalculatedDto;
import com.jmontanez.challenge.bcp.dto.ExchangeRateDto;
import com.jmontanez.challenge.bcp.dto.request.ExchangeRateFilter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeRateService {
    Flux<ExchangeRateDto> getAll();
    Mono<ExchangeRateCalculatedDto> calculateAmount(ExchangeRateFilter filter);
    Mono<ExchangeRateDto> updateExchangeRate(ExchangeRateBaseDto filter);
}
