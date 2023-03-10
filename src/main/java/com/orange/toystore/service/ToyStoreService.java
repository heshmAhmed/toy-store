package com.orange.toystore.service;

import com.orange.toystore.api.Toy;
import com.orange.toystore.api.ToyPutRequest;
import com.orange.toystore.api.ToyResponse;
import com.orange.toystore.persistance.ToyEntity;
import com.orange.toystore.persistance.ToyRepository;
import com.orange.toystore.util.ToyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToyStoreService {
    @Autowired
    private ToyRepository toyRepository;
    @Autowired
    private ToyMapper mapper;

    public List<ToyResponse> ListAllToys() {
        return toyRepository.findAll().stream()
                .map(toyEntity -> mapper.toyEntityToToyResponse(toyEntity))
                .collect(Collectors.toList());
    }

    public ToyResponse addNewToy(Toy toy){
        return mapper.toyEntityToToyResponse(toyRepository.save(mapper.toyToToyEntity(toy)));
    }

    public ToyResponse updateToy(ToyPutRequest toyPutRequest) {
       ToyEntity toyEntity = toyRepository.findById(toyPutRequest.getId())
               .orElseThrow(()->new RuntimeException("can not find toy with id: " + toyPutRequest.getId()));
       toyEntity.setPrice(toyPutRequest.getPrice());
       toyEntity.setColor(toyPutRequest.getColor());
       toyEntity.setAge(toyPutRequest.getAge());
       toyEntity.setName(toyPutRequest.getName());
       toyEntity.setDescription(toyPutRequest.getDescription());

       toyRepository.save(toyEntity);
       return mapper.toyEntityToToyResponse(toyEntity);
    }

    public void deleteToy(Long id) {
        ToyEntity toyEntity = new ToyEntity();
        toyEntity.setId(id);
        toyRepository.delete(toyEntity);
    }
}
