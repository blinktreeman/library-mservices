package ru.letsdigit.readerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.letsdigit.readerservice.entity.Reader;

import java.util.UUID;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, UUID> {
}
