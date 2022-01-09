package com.jmontanez.challenge.bcp.entity.base;

import lombok.Data;
import java.time.LocalDateTime;

 @Data
public class BaseEntity {
    private Boolean status;
    private String creationUser;
    private LocalDateTime creationDate;
    private String modificationUser;
    private LocalDateTime modificationDate;
}
