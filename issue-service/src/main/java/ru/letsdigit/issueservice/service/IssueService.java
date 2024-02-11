package ru.letsdigit.issueservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.letsdigit.issueservice.entity.Issue;
import ru.letsdigit.issueservice.repository.IssueRepository;
import ru.letsdigit.issueservice.response.BookResponse;
import ru.letsdigit.issueservice.response.IssueResponse;
import ru.letsdigit.issueservice.response.ReaderResponse;

import java.util.Optional;
import java.util.UUID;

@Service
public class IssueService {
    private final IssueRepository repository;
    private final WebClient webClient;
    private final ModelMapper modelMapper;

    @Autowired
    public IssueService(IssueRepository repository,
                        WebClient webClient,
                        ModelMapper modelMapper) {
        this.repository = repository;
        this.webClient = webClient;
        this.modelMapper = modelMapper;
    }

    public Issue save(Issue issue) {
        return repository.save(issue);
    }

    public IssueResponse findById(UUID uuid) {
        Optional<Issue> issue = repository.findById(uuid);
        IssueResponse issueResponse = modelMapper.map(issue, IssueResponse.class);
        BookResponse bookResponse = webClient
                .get()
                .uri("http://book-service/api/v1/book?uuid=" + issue.get().getBookUuid())
                .retrieve()
                .bodyToMono(BookResponse.class).block();
        issueResponse.setBookResponse(bookResponse);
        ReaderResponse readerResponse = webClient
                .get()
                .uri("http://reader-service/api/v1/reader?uuid=" + issue.get().getReaderUuid())
                .retrieve()
                .bodyToMono(ReaderResponse.class)
                .block();
        issueResponse.setReaderResponse(readerResponse);
        return issueResponse;
    }
}
