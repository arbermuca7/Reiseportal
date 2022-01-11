package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resources/articles")
@Log
public class ArticlesResource {
    @Autowired
    private ArticlesRepository articleRepository;
    @Autowired
    private Source source;

    private Articles article;
    private ArrayList authorIds = new ArrayList();
    private List<Articles> articles;
    private ArrayList articleTitles = new ArrayList();

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Articles article) {
        log.info("create() >> article=" + article);

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
            authorIds.add(article.getAuthors().get(i).getId());
        }

        sendEvent(ArticlesEvent.forAccessed(authorIds, article.getAttraction().getId()));

        return article;
    }

    @GetMapping
    public ArrayList retrieveAll() {
        log.info("retrieveAll()");
        articles = articleRepository.findAll();
        articleTitles.clear();
        for(int i = 0; i < articles.size(); i++){
            articleTitles.add(articles.get(i).getTitle());
        }
        return articleTitles;
    }

    public void sendEvent(ArticlesEvent event){
        Message<ArticlesEvent> message = MessageBuilder
                .withPayload(event).build();
        source
                .output()
                .send(message);
    }
}
