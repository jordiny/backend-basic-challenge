package com.jmontanez.challenge.bcp.repository;

import com.jmontanez.challenge.bcp.entity.ExchangeRateEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ExchangeRateRepository extends ReactiveCrudRepository<ExchangeRateEntity,Long> {
    @Query("select top 1 * from   tbl_exchange_rate where currency_id_from = :from and currency_id_to =:to")
    Mono<ExchangeRateEntity> findExchangeRateByCurrency(String from, String to);
}
