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
public class AttractionRessource {

    @Autowired
    private AttractionRepository attractionRepository;

    @GetMapping
    public List<Attraction> fetchAllAttractions(){
        return attractionRepository.findAll();
    }
}
