package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PurchaseElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean isPurchaded;
    private String text;

    public PurchaseElement(){

    }
    public PurchaseElement(Long id, String text, Boolean status){
        this.id = id;
        this.text = text;
        this.isPurchaded = status;
    }

    public void ChangeStatus(){
        this.isPurchaded = (!isPurchaded);
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public void setStatus(Boolean status){
        this.isPurchaded = status;
    }

    public boolean getStatus(){ return this.isPurchaded;}

    public Long getId(){return this.id;}
}
