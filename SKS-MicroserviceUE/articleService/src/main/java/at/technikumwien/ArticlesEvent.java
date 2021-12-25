package at.technikumwien;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.Instant;

@Value@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticlesEvent {
    public enum EventType{
        ACCESSED, DELETED
    }

    long timestamp;
    EventType eventType;
    Articles article;

    public static ArticlesEvent forAccessed(Articles article){
        return new ArticlesEvent(
                Instant.now().toEpochMilli(),
                EventType.ACCESSED,
                article
        );
    }

    public static ArticlesEvent forDeleted(Articles article){
        return new ArticlesEvent(
                Instant.now().toEpochMilli(),
                EventType.DELETED,
                article
        );
    }
}
