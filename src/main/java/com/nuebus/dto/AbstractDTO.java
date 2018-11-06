package com.nuebus.dto;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;


public class AbstractDTO {    
    
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
