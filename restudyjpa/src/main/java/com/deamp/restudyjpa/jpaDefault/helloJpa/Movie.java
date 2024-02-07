package com.deamp.restudyjpa.jpaDefault.helloJpa;

import jakarta.persistence.Entity;

@Entity
public class Movie extends Item{
    private String director;
    private String actor;
}
