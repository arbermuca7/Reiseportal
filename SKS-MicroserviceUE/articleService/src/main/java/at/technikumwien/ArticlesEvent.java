package at.technikumwien;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.w3c.dom.Attr;

import java.time.Instant;
import java.util.List;

@Value
public class ArticlesEvent {
    public enum EventType{
        ACCESSED
    }

    long timestamp;
    EventType eventType;
    List authorIds;
    long attractionId;

    public static ArticlesEvent forAccessed(List authorIds, long attractionId){
        return new ArticlesEvent(
                Instant.now().toEpochMilli(),
                EventType.ACCESSED,
                authorIds,
                attractionId
        );
    }
}
