package ru.letsdigit.bookservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.letsdigit.bookservice.entity.Book;
import ru.letsdigit.bookservice.repository.BookRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public Optional<Book> findById(UUID uuid) {
        return repository.findById(uuid);
    }

    public Iterable<Book> findAll() {
        return repository.findAll();
    }

    public Optional<Book> update(Book book) {
        return repository.findById(book.getUuid()).isPresent() ?
                Optional.of(repository.save(book)) :
                Optional.empty();
    }

    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }
}
