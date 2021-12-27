package at.technikumwien;

import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
public class CashOutEvent {
    public enum EventType{
        ACCESSED
    }

    long timestamp;
    EventType eventType;
    List authorIds;
    long attractionId;

}
