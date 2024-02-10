package ru.letsdigit.bookservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.letsdigit.bookservice.entity.Author;
import ru.letsdigit.bookservice.service.AuthorService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/author")
public class AuthorController {
    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Author> save(@RequestBody Author author) {
        return new ResponseEntity<>(service.save(author), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Author> findById(@RequestParam(name = "uuid") UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Iterable<Author>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
