package ru.letsdigit.bookservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.letsdigit.bookservice.entity.Author;
import ru.letsdigit.bookservice.repository.AuthorRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Author save(Author author) {
        return repository.save(author);
    }

    public Optional<Author> findById(UUID uuid) {
        return repository.findById(uuid);
    }

    public Optional<Author> findFirstByFirstNameAndLastName(String firstName, String lastName) {
        return repository.findFirstByFirstNameAndLastName(firstName, lastName);
    }

    public Iterable<Author> findAll() {
        return repository.findAll();
    }

    public Optional<Author> update(Author author) {
        return repository.findById(author.getUuid()).isPresent() ?
                Optional.of(repository.save(author)) :
                Optional.empty();
    }

    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }
}
