package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Log
public class DBInitializer {
    @Autowired
   private AttractionRepository attractionRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void handleApplicationReady() {
        log.info("initialize database ...");
        if (attractionRepository.count() == 0) {
            attractionRepository.saveAll(List.of(
                    new Attraction("Stephansdom"),
                    new Attraction("Schloss Schoenbrunn"),
                    new Attraction("Wiener Prater"),
                    new Attraction("Schloss Belvedere"),
                    new Attraction("Hofburg"),
                    new Attraction("Haus des Meeres"),
                    new Attraction("Wiener Rathaus"),
                    new Attraction("Museumsquartier")
            ));
        }
    }
}
