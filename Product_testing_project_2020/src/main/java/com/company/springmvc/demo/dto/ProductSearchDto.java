package com.company.springmvc.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductSearchDto {
    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
     private String name;

}

