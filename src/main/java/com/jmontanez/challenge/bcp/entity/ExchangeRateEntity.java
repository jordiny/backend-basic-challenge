package com.jmontanez.challenge.bcp.entity;

import com.jmontanez.challenge.bcp.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Table("tbl_exchange_rate")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ExchangeRateEntity extends BaseEntity {
    @Id
    private Long id;
    private String currencyIdFrom;
    private String currencyIdTo;
    @Transient
    private CurrencyEntity currencyFrom;
    @Transient
    private CurrencyEntity currencyTo;
    private double exchangeRate;
    private boolean division;

}
