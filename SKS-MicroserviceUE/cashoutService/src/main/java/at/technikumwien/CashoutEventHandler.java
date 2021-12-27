package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Log
@Service
public class CashoutEventHandler {

    @Autowired
    CashOutRessource cashOutRessource;

    @StreamListener(Sink.INPUT)
    public void handleEvent(CashOutEvent event){
        switch (event.getEventType()){
            case ACCESSED:
                log.info("Event for Payment-Update triggered");
                for (int i = 0; i < event.getAuthorIds().size(); i++) {
                    cashOutRessource.updatePayment((int) event.getAuthorIds().get(i));
                }
        }
    }
}
