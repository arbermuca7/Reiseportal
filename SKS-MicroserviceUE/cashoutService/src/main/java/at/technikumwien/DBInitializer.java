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
    private AuthorRepository authorRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void handleApplicationReady() {
        log.info("initialize database ...");
        if (authorRepository.count() == 0) {
            authorRepository.saveAll(List.of(
                    new Author(Sex.FEMALE,"Aida","Omic"),
                    new Author(Sex.MALE,"Arber","Muca"),
                    new Author(Sex.FEMALE,"Sabrine","Nirgendwer"),
                    new Author(Sex.MALE,"Norbert","Niemand")
            ));
        }
    }
}
