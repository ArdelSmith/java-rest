package org.example.controller;


import org.example.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("list")
public class DeleteController {
    @Autowired
    private PurchaseRepository repository;

    @DeleteMapping("{id}")
    public void deleteElement(@PathVariable Long id){
        repository.deleteById(id);
    }
}
