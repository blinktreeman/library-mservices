package ru.letsdigit.issueservice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.letsdigit.issueservice.entity.Issue;
import ru.letsdigit.issueservice.response.IssueResponse;
import ru.letsdigit.issueservice.service.IssueService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/issue")
public class IssueController {
    private final IssueService service;

    public IssueController(IssueService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Issue> save(@RequestBody Issue issue) {
        return new ResponseEntity<>(service.save(issue), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<IssueResponse> findById(@RequestParam UUID uuid) {
        return new ResponseEntity<>(service.findById(uuid), HttpStatus.OK);
    }
}
