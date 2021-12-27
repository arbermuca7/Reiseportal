package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Log
@Service
public class CashoutEventHandler {

    @StreamListener(Sink.INPUT)
    public void handleEvent(CashOutEvent event){
        switch (event.getEventType()){
            case ACCESSED:
                log.info("Event for Payment-Update triggered");
                System.out.println("LÄNGE "+event.getAuthorIds().size());
                    System.out.println("ID"+(int) event.getAuthorIds().get(0));
                    new CashOutRessource().updatePayment((int)event.getAuthorIds().get(0));
                //}
                break;
        }
    }
}
