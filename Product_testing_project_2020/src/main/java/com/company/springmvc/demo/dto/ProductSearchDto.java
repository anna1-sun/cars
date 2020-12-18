package com.company.springmvc.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductSearchDto {
    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
     private String name;

    @JsonProperty("shelf_life")
     private int shelfLife;

    @JsonProperty("category")
     private int category;

}

//sis pagaidam nekur nav izmantots - izmanto, ja taisam produktu meklesanu
