package com.example.fitmatch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Service{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String className;
    private String classDesc;

    public Long getId(){
        return id;
    }

public String getName(String className){
    return className;
}

public void setName(String className){
    this.className = className;
}

public String getDescription(){
    return classDesc;
}

public void setDescription(String classDesc){
    this.classDesc =  classDesc;
}

}