package org.example.controller;


import org.example.exceptions.NotFoundException;
import org.example.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("list")
public class PutController {
    @Autowired
    private PurchaseRepository repository;

    @PutMapping("{id}")
    public void changeStatus(@PathVariable Long id){
        var element = repository.findById(id).orElseThrow(NotFoundException::new);
        element.setStatus(!element.getStatus());
        repository.save(element);
    }
}
