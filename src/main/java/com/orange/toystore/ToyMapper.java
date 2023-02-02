package com.orange.toystore;

import org.springframework.stereotype.Component;

@Component
public class ToyMapper {
    ToyEntity toyToToyEntity(Toy toy) {
        ToyEntity toyEntity = new ToyEntity();
        toyEntity.setName(toy.getName());
        toyEntity.setAge(toy.getAge());
        toyEntity.setDescription(toy.getDesc());
        toyEntity.setColor(toy.getColor());
        toyEntity.setPrice(toy.getPrice());
        return toyEntity;
    }
    ToyResponse toyEntityToToyResponse(ToyEntity toyEntity) {
        ToyResponse toyResponse = new ToyResponse();
        toyResponse.setId(toyEntity.getId());
        toyResponse.setAge(toyEntity.getAge());
        toyResponse.setPrice(toyEntity.getPrice());
        toyResponse.setName(toyEntity.getName());
        toyResponse.setDesc(toyEntity.getDescription());
        toyResponse.setColor(toyEntity.getColor());
        return toyResponse;
    }
}
