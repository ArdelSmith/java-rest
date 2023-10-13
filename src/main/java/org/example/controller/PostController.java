package org.example.controller;


import org.example.model.PurchaseElement;
import org.example.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("list")
public class PostController {
    @Autowired
    private PurchaseRepository repository;

    @PostMapping
    public PurchaseElement createElement(@RequestParam String text){
        if (text == null || text.isBlank()) text = "Пустой элемент";
        var purch = new PurchaseElement(null ,text, false);
        repository.save(purch);
        return purch;
    }
}
