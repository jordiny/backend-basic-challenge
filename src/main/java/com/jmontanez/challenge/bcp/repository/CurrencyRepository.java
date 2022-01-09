package com.jmontanez.challenge.bcp.repository;

import com.jmontanez.challenge.bcp.entity.CurrencyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CurrencyRepository extends ReactiveCrudRepository<CurrencyEntity,String> {

}
