package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resources/articles")
@CrossOrigin
@Log
public class ArticlesResource {
    @Autowired
    private ArticlesRepository articleRepository;
    @Autowired
    private Source source;

    private Articles article;
    private ArrayList authorIds = new ArrayList();

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Articles article) {
        log.info("create() >> news=" + article);

        article.setId(null);   // better safe than sorry
        article = articleRepository.save(article);

        URI location = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(getClass()).retrieve(article.getId())
        ).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public Articles retrieve(@PathVariable long id) {
        log.info("retrieve() >> id=" + id);
        authorIds = new ArrayList();

        article = articleRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );

        //Kafka Event
        for (int i = 0; i < article.getAuthors().size(); i++){
            authorIds.add(1);
        }

        sendEvent(ArticlesEvent.forAccessed(authorIds, article.getAttraction().getId()));

        return article;
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody Articles article) {
        log.info("update() >> id=" + id + ", article=" + article);

        article.setId(id);   // better safe than sorry
        articleRepository.save(article);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        log.info("delete() >> id=" + id);
        articleRepository.deleteById(id);   // throw EmptyResultDataAccessException if article could not be found
    }

    @GetMapping
    public List<Articles> retrieveAll() {
        log.info("retrieveAll()");
        return articleRepository.findAll();
    }

    public void sendEvent(ArticlesEvent event){
        Message<ArticlesEvent> message = MessageBuilder
                .withPayload(event).build();
        source
                .output()
                .send(message);
    }
}
