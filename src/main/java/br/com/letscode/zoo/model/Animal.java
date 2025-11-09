package br.com.letscode.zoo.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name="animal")
@Data
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String uid;

    private String name;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
