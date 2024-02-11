package ru.letsdigit.issueservice.response;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class IssueResponse {
    private UUID uuid;
    private BookResponse bookResponse;
    private ReaderResponse readerResponse;
    private Date issuedAt;
    private Date returnedAt;
}
