package ru.letsdigit.readerservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.letsdigit.readerservice.entity.Reader;
import ru.letsdigit.readerservice.service.ReaderService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/reader")
public class ReaderController {
    private final ReaderService service;

    public ReaderController(ReaderService service) {
        this.service = service;
    }

    @PutMapping
    public ResponseEntity<Reader> save(@RequestBody Reader reader) {
        return new ResponseEntity<>(service.save(reader), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Reader> findById(@RequestParam UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Iterable<Reader>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
