package com.example.doggie.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "poroda")
public class Poroda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    @OneToMany(mappedBy="poroda")
    private Set<Pet> pets;

    public Poroda() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return title;
    }

}