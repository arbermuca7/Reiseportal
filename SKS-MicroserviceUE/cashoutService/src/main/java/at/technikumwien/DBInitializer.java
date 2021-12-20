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
    private AuthorCashOutRepository authorCashOutRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void handleApplicationReady() {
        log.info("initialize database ...");
        if (authorCashOutRepository.count() == 0) {
            authorCashOutRepository.saveAll(List.of(
                    new Author(Sex.FEMALE,"Aida","Omic"),
                    new Author(Sex.MALE,"Arber","Muca"),
                    new Author(Sex.FEMALE,"Sabrine","Nirgendwer"),
                    new Author(Sex.MALE,"Norbert","Niemand")
            ));
        }
    }
}
