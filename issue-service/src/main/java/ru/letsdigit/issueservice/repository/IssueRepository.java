package ru.letsdigit.issueservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.letsdigit.issueservice.entity.Issue;

import java.util.UUID;

@Repository
public interface IssueRepository extends JpaRepository<Issue, UUID> {
}
