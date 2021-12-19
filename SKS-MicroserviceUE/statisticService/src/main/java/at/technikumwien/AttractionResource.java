package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resources/attractions")
@Log
public class AttractionResource {

    @Autowired
    private AttractionRepository attractionRepository;

    private int counter;

    @GetMapping
    public List<Attraction> retrieveAll(){
        log.info("retrieveAll()");

        //simulate problem on every third request
        counter = (counter+1)%3;

        if (counter == 0){
            throw new RuntimeException("dummy");
        }

        return attractionRepository.findAll();
    }
}
