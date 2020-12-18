package com.company.springmvc.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.List;

@Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table
    public class Category {
        @Id
        @GeneratedValue
        @Column(name = "id")
        private int id;
        @Column(name = "name")
        private String name;




    }


