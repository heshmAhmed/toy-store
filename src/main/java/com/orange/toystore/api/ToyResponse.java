package com.orange.toystore.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToyResponse {
    private Long id;
    private String name;
    private String desc;
    private double price;
    private String color;
    private String age;
}
