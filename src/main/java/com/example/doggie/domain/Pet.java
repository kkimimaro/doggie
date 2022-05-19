package com.example.doggie.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private Integer rost;
    private Integer ves;


    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="owner_id", nullable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="activity_id", nullable=false)
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="poroda_id", nullable=false)
    private Poroda poroda;

    public Pet() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getRost() {
        return rost;
    }

    public void setRost(Integer rost) {
        this.rost = rost;
    }

    public Integer getVes() {
        return ves;
    }

    public void setVes(Integer ves) {
        this.ves = ves;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Poroda getPoroda() {
        return poroda;
    }

    public void setPoroda(Poroda poroda) {
        this.poroda = poroda;
    }


    @Override
    public String toString() {
        return name;
    }
}
