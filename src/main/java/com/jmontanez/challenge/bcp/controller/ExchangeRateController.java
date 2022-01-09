package com.jmontanez.challenge.bcp.controller;

import com.jmontanez.challenge.bcp.dto.ExchangeRateBaseDto;
import com.jmontanez.challenge.bcp.dto.ExchangeRateCalculatedDto;
import com.jmontanez.challenge.bcp.dto.ExchangeRateDto;
import com.jmontanez.challenge.bcp.dto.request.ExchangeRateFilter;
import com.jmontanez.challenge.bcp.dto.response.Response;
import com.jmontanez.challenge.bcp.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequestMapping(value = "/exchange-rates")
public class ExchangeRateController {

    @Autowired
    ExchangeRateService exchangeRateService;

    @GetMapping(value = "")
    public String param() {
        return "Hello World";
    }

    @GetMapping("/getAll")
    public Mono<Response<List<ExchangeRateDto>>> getAllExchangeRates() {
        Response<List<ExchangeRateDto>> response = Response.ok();

        return Mono.just(response)
                .zipWith(exchangeRateService.getAll().collectList())
                .map(r -> r.getT1().setData(r.getT2()));
    }

    @PostMapping("/calculate")
    public Mono<Response<ExchangeRateCalculatedDto>> calculateAmount(@RequestBody ExchangeRateFilter filter) {
        Response<ExchangeRateCalculatedDto> response = Response.ok();
        return Mono.just(response)
                .zipWith(exchangeRateService.calculateAmount(filter))
                .map(r -> r.getT1().setData(r.getT2()));
    }

    @PostMapping("/updateValue")
    public Mono<ResponseEntity<Response<ExchangeRateDto>>> updateExchangeRate(@RequestBody ExchangeRateBaseDto filter) {
        Response<ExchangeRateDto> response = Response.ok();
        return Mono.just(response)
                .zipWith(exchangeRateService.updateExchangeRate(filter))
                .map(r -> ResponseEntity.status(404).body(r.getT1().setData(r.getT2())));
    }

}
