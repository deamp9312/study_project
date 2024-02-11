package com.deamp.restudyjpa.jpaDefault.helloJpa;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends Item{
    private String director;
    private String actor;
}
