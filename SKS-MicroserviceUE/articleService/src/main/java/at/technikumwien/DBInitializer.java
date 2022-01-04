package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@Log
public class DBInitializer {
    @Autowired
    private ArticlesRepository articleRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void handleApplicationReady() {
        log.info("initialize database ...");

        Articles articles = articleRepository.save(
                new Articles(
                        "Hello World!",
                        "Herzlich willkommen am Stephansdom.",
                        LocalDate.of(2021, 1, 1),
                        new Attraction("Stephansdom"),
                        List.of(
                                new Author(Sex.MALE, "Markus", "Mustermann")
                        )
                )
        );

        Attraction attraction = articles.getAttraction();

        articleRepository.save(
                new Articles(
                        "News-Portal online!",
                        "Unser neues Article-Portal für Stephansdom ist online.",
                        LocalDate.of(2021, 1, 2),
                        new Attraction(attraction.getId(), attraction.getName()),
                        List.of(
                                new Author(Sex.FEMALE, "Martina", "Musterfrau")
                        )
                )
        );
    }
}
