package ru.letsdigit.readerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.letsdigit.readerservice.entity.Reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final ReaderService service;

    @Autowired
    public CommandLineRunnerImpl(ReaderService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Reader> readers = new ArrayList<>(Arrays.asList(
                new Reader("Иван", "Иванов"),
                new Reader("Петр", "Петров")
        ));

        readers.forEach(service::save);
    }
}
