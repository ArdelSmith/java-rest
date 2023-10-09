package org.example.controller;

import org.example.exceptions.NotFoundException;
import org.example.model.PurchaseElement;
import org.example.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private PurchaseRepository repository;

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

    @PutMapping("{id}")
    public void changeStatus(@PathVariable Long id, @RequestParam Boolean status){
        var element = repository.findById(id).orElseThrow(NotFoundException::new);
        element.setStatus(status);
        repository.save(element);
    }

    @PostMapping
    public void createElement(@RequestParam String text, @RequestParam Boolean status){
        repository.save(new PurchaseElement(null, text, status));
    }

    @DeleteMapping("{id}")
    public void deleteElement(@PathVariable Long id){
        repository.deleteById(id);
    }
}
