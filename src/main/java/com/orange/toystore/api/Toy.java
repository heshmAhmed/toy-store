package com.orange.toystore.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Toy {
    private String name;
    private String desc;
    private double price;
    private String color;
    private String age;
}
