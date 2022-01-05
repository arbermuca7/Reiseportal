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
    private CashOutRepository cashOutRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void handleApplicationReady(){
        log.info("starting");
        if (cashOutRepository.count() == 0) {
            cashOutRepository.saveAll(List.of(
                    new CashOutAuthor(Sex.MALE,"Norbert","Niemand"),
                    new CashOutAuthor(Sex.FEMALE,"Aida","Omic"),
                    new CashOutAuthor(Sex.MALE,"Arber","Muca"),
                    new CashOutAuthor(Sex.FEMALE,"Sabrine","Nirgendwer")

            ));
        }
    }
}
