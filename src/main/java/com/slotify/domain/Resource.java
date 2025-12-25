package com.slotify.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    protected Resource() {}

    public Resource(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName(){
        return name;
    }
}
