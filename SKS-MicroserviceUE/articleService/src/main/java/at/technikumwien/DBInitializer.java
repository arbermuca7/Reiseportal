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
                                new Author(Sex.MALE,"Norbert","Niemand"),
                                new Author(Sex.FEMALE, "Aida", "Omic")
                        )
                )
        );

        articleRepository.save(
                new Articles(
                        "News-Portal online!",
                        "Unser neues Article-Portal für Schloss Schoenbrunn ist online.",
                        LocalDate.of(2021, 1, 2),
                        new Attraction("Schloss Schoenbrunn"),
                        List.of(
                                new Author(Sex.MALE,"Arber","Muca"),
                                new Author(Sex.FEMALE,"Sabrine","Nirgendwer")
                        )
                )
        );

        articleRepository.save(
                new Articles(
                        "Der Wiener Prater",
                        "Unser neues Article-Portal für Stephansdom ist online.",
                        LocalDate.of(2021, 6, 23),
                        new Attraction("Wiener Prater"),
                        List.of(
                                articles.getAuthors().get(0)
                        )
                )
        );

        articleRepository.save(
                new Articles(
                        "Ein Artikel zum Schloss Berlvedere",
                        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.",
                        LocalDate.of(2021, 12, 2),
                        new Attraction("Schloss Belvedere"),
                        List.of(
                                articles.getAuthors().get(1)
                        )
                )
        );

    }
}
