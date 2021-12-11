package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/resources/articles")
@CrossOrigin
@Log
public class ArticlesResource {
    @Autowired
    private ArticlesRepository articleRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Articles news) {
        log.info("create() >> news=" + news);

        news.setId(null);   // better safe than sorry
        news = articleRepository.save(news);

        URI location = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(getClass()).retrieve(news.getId())
        ).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public Articles retrieve(@PathVariable long id) {
        log.info("retrieve() >> id=" + id);

        return articleRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody Articles news) {
        log.info("update() >> id=" + id + ", news=" + news);

        news.setId(id);   // better safe than sorry
        articleRepository.save(news);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        log.info("delete() >> id=" + id);

        articleRepository.deleteById(id);   // throw EmptyResultDataAccessException if news could not be found
    }

    @GetMapping
    public List<Articles> retrieveAll() {
        log.info("retrieveAll()");
        return articleRepository.findAll();
    }
}
