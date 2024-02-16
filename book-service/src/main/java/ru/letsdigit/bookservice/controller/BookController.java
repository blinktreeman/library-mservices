package ru.letsdigit.bookservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.letsdigit.bookservice.entity.Book;
import ru.letsdigit.bookservice.service.BookService;
import ru.letsdigit.timemeterspringbootstarter.aspect.Timer;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/book")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book) {
        return new ResponseEntity<>(service.save(book), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Book> findById(@RequestParam(name = "uuid") UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Timer
    @GetMapping(value = "/all")
    public ResponseEntity<Iterable<Book>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
