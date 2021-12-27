package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Log
@Service
public class StatisticsEventHandler {

    @Autowired
    AttractionResource attractionResource;

    @StreamListener(Sink.INPUT)
    public void handleEvent(StatisticsEvent event){
        switch(event.getEventType()){
            case ACCESSED:
                attractionResource.updateStatistics(event.getAttractionId());
        }
    }
}
