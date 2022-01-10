package com.jmontanez.challenge.bcp.service.implementations;

import com.jmontanez.challenge.bcp.common.Constants;
import com.jmontanez.challenge.bcp.dto.response.ExchangeRateBaseDto;
import com.jmontanez.challenge.bcp.dto.response.ExchangeRateCalculatedDto;
import com.jmontanez.challenge.bcp.dto.response.ExchangeRateDto;
import com.jmontanez.challenge.bcp.dto.mapper.ExchangeRateMapper;
import com.jmontanez.challenge.bcp.dto.request.ExchangeRateFilter;
import com.jmontanez.challenge.bcp.entity.ExchangeRateEntity;
import com.jmontanez.challenge.bcp.repository.CurrencyRepository;
import com.jmontanez.challenge.bcp.repository.ExchangeRateRepository;
import com.jmontanez.challenge.bcp.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    public final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public final CurrencyRepository currencyRepository;

    @Override
    public Flux<ExchangeRateDto> getAll() {
        return exchangeRateRepository.findAll()
                .flatMap(this::loadRelations)
                .map(ExchangeRateMapper::toExchangeRateDto);
    }

    @Override
    public Mono<ExchangeRateCalculatedDto> calculateAmount(ExchangeRateFilter filter) {
        return exchangeRateRepository.findExchangeRateByCurrency(filter.getCurrencyFrom().toUpperCase(), filter.getCurrencyTo().toUpperCase())
                .flatMap(x -> {
                            String amount = Constants.decimalFormatD2.format(x.isDivision() ?
                                    filter.getAmount() / x.getExchangeRate() :
                                    filter.getAmount() * x.getExchangeRate());

                            var calculatedDto = ExchangeRateCalculatedDto.builder()
                                    .amount(filter.getAmount())
                                    .exchangeRate(x.getExchangeRate())
                                    .currencyFrom(x.getCurrencyIdFrom())
                                    .currencyTo(x.getCurrencyIdTo())
                                    .build();
                            try {
                                calculatedDto.setCalculatedAmount(Constants.decimalFormatD2.parse(amount).doubleValue());

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return Mono.just(calculatedDto);
                        }

                );
    }

    @Override
    public Mono<ExchangeRateDto> updateExchangeRate(ExchangeRateBaseDto filter) {
        return exchangeRateRepository.findExchangeRateByCurrency(filter.getCurrencyFrom().toUpperCase(), filter.getCurrencyTo().toUpperCase())
                .flatMap(x -> {

                    x.setExchangeRate(filter.getExchangeRate());
                    return exchangeRateRepository.save(x).flatMap(this::loadRelations).map(ExchangeRateMapper::toExchangeRateDto);
                });
    }

    private Mono<ExchangeRateEntity> loadRelations(final ExchangeRateEntity item) {
        Mono<ExchangeRateEntity> mono = Mono.just(item)
                .zipWith(currencyRepository.findById(item.getCurrencyIdFrom()))
                .map(result -> result.getT1().setCurrencyFrom(result.getT2()));

        mono = mono.zipWith(currencyRepository.findById(item.getCurrencyIdTo()))
                .map(result -> result.getT1().setCurrencyTo(result.getT2()));

        return mono;
    }
}
