package ru.letsdigit.bookservice.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.letsdigit.bookservice.entity.Author;
import ru.letsdigit.bookservice.entity.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Author> authors = new ArrayList<>(Arrays.asList(
                new Author("Илья", "Ильф"),
                new Author("Евгений", "Петров"),
                new Author("Крис", "Ричардсон")
        ));
        authors.forEach(authorService::save);

        Book book = new Book();
        book.setTitle("Золотой телёнок");
        book.getAuthors().add(authorService.findFirstByFirstNameAndLastName("Илья", "Ильф").orElseThrow());
        book.getAuthors().add(authorService.findFirstByFirstNameAndLastName("Евгений", "Петров").orElseThrow());
        bookService.save(book);

        Book book2 = new Book();
        book2.setTitle("Микросервисы. Паттерны разработки и рефакторинга");
        book2.getAuthors().add(authorService.findFirstByFirstNameAndLastName("Крис", "Ричардсон").orElseThrow());
        bookService.save(book2);
    }
}
