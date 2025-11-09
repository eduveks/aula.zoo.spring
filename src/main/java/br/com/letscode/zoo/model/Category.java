package br.com.letscode.zoo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String uid;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Collection<Animal> animals = new ArrayList<>();
}
