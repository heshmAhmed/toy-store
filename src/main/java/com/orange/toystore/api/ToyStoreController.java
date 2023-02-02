package com.orange.toystore.api;

import com.orange.toystore.service.ToyStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/toys")
public class ToyStoreController {
    @Autowired
    private ToyStoreService toyStoreService;
    @GetMapping
    public ResponseEntity<?> listToys() {
        return new ResponseEntity<>(toyStoreService.ListAllToys(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNewToy(@RequestBody Toy toy) {
        return new ResponseEntity<>(toyStoreService.addNewToy(toy), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteToy(@PathVariable Long id) {
        toyStoreService.deleteToy(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<?> updateToy(@RequestBody ToyPutRequest toy) {
        return new ResponseEntity<>(toyStoreService.updateToy(toy), HttpStatus.ACCEPTED);
    }
}
