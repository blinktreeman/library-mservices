package ru.letsdigit.issueservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Issue implements Serializable {
    @Id
    @GeneratedValue
    private UUID uuid;

    @Column(name = "book_uuid", nullable = false)
    private UUID bookUuid;

    @Column(name = "reader_uuid", nullable = false)
    private UUID readerUuid;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private Date issuedAt;

    private Date returnedAt;
}
