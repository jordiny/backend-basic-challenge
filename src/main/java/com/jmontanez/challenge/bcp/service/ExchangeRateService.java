package com.jmontanez.challenge.bcp.service;

import com.jmontanez.challenge.bcp.dto.response.ExchangeRateBaseDto;
import com.jmontanez.challenge.bcp.dto.response.ExchangeRateCalculatedDto;
import com.jmontanez.challenge.bcp.dto.response.ExchangeRateDto;
import com.jmontanez.challenge.bcp.dto.request.ExchangeRateFilter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeRateService {
    Flux<ExchangeRateDto> getAll();
    Mono<ExchangeRateCalculatedDto> calculateAmount(ExchangeRateFilter filter);
    Mono<ExchangeRateDto> updateExchangeRate(ExchangeRateBaseDto filter);
}
