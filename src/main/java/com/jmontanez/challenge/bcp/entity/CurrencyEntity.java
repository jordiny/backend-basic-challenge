package com.jmontanez.challenge.bcp.entity;

import com.jmontanez.challenge.bcp.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("tbl_currency")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CurrencyEntity extends BaseEntity {
    @Id
    private String id;
    private String description;
}
