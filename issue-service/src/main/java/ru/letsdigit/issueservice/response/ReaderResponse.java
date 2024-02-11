package ru.letsdigit.issueservice.response;

import lombok.Data;

import java.util.UUID;

@Data
public class ReaderResponse {
    private UUID uuid;
    private String firstName;
    private String lastName;
}
