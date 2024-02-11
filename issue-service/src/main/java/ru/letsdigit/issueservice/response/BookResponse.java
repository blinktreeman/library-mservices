package ru.letsdigit.issueservice.response;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class BookResponse {
    private UUID uuid;
    private String title;
    private Set<AuthorResponse> authors = new HashSet<>();
}
