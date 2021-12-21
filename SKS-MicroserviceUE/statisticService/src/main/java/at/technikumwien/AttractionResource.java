package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resources/attractions")
@Log
public class AttractionResource {

    @Autowired
    private AttractionRepository attractionRepository;
    private Attraction attraction;

    private int counter;

    //should actually be called by an Event
    //@GetMapping("/stats/{id}")
    public void updatePayment(@PathVariable long id){
        log.info("Statistics +1 >> id=" +id);
        attraction = attractionRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
        attraction.setId(id);
        attraction.setTimesVisited(attraction.getTimesVisited()+1);
        attractionRepository.save(attraction);
    }

    @GetMapping("/statistics")
    public List<Attraction> retrieveAll(){
        log.info("retrieveAll()");
        return attractionRepository.findAll();
    }

}
