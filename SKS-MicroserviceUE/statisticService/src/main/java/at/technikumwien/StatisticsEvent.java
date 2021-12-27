package at.technikumwien;

import lombok.Getter;

import java.util.List;

@Getter
public class StatisticsEvent {
    public enum EventType{
        ACCESSED
    }

    long timestamp;
    EventType eventType;
    List authorIds;
    long attractionId;

}
