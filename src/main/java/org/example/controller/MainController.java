package org.example.controller;

import org.example.exceptions.NotFoundException;
import org.example.model.PurchaseElement;
import org.example.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    PurchaseRepository repository;

    @GetMapping
    public List<PurchaseElement> list(){
        var items = repository.findAll().iterator();
        List<PurchaseElement> purchases = new ArrayList<>();
        while (items.hasNext()){
            purchases.add(items.next());
        }
        return purchases;
    }

    @GetMapping("{id}")
    public PurchaseElement findElement(@PathVariable Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }
}
