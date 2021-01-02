package com.company.springmvc.demo.dto;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
public class TestUpdateDto {
    private int testValue;
    private int testId;
    private int bacteriaId;
    private String bacteriaName;
    private int categoryLimit;
//    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private String finishDate;

}
