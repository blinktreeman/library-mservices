package ru.letsdigit.bookservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue
    private UUID uuid;

    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_uuid"),
            inverseJoinColumns = @JoinColumn(name = "author_uuid"))
    private Set<Author> authors = new HashSet<>();
}
