package ru.letsdigit.readerservice.service;

import org.springframework.stereotype.Service;
import ru.letsdigit.readerservice.entity.Reader;
import ru.letsdigit.readerservice.repository.ReaderRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReaderService {
    private final ReaderRepository repository;

    public ReaderService(ReaderRepository repository) {
        this.repository = repository;
    }

    public Reader save(Reader reader) {
        return repository.save(reader);
    }

    public Optional<Reader> findById(UUID uuid) {
        return repository.findById(uuid);
    }

    public Iterable<Reader> findAll() {
        return repository.findAll();
    }
}
