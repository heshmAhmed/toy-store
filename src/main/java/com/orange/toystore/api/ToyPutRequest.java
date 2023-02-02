package com.orange.toystore.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToyPutRequest {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String color;
    private int age;
}
