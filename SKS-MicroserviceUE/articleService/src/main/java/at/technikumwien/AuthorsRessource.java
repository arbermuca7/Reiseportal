package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resources/authors")
@Log
public class AuthorsRessource {
    @Autowired
    private AuthorRepository authorRepository;
    private List<Author> authors;
    private List<Author> returnAuthors;
    private List returnAuthorsNames = new ArrayList();

    @GetMapping
    public List<Author> fetchAllAuthors(){
       /* returnAuthorsNames.clear();
        authors = authorRepository.findAll();
        for(int i = 0; i < authors.size(); i++){
            returnAuthorsNames.add(authors.get(i).getFirstName() + " " + authors.get(i).getLastName());
        }
        return returnAuthorsNames;*/
        return authorRepository.findAll();
    }

    @GetMapping("/specificAuthors")
    public List<Author> fetchSpecificAuthors(@RequestBody List<Author> specificAuthorsNames){
        returnAuthors.clear();
        authors = authorRepository.findAll();
        for (int i = 0; i < authors.size(); i++){
            for(int a = 0; a < specificAuthorsNames.size(); a++) {
                if ((authors.get(i).getFirstName() + " " + authors.get(i).getLastName()).equals(specificAuthorsNames.get(a).getFirstName()+" "+specificAuthorsNames.get(a).getLastName()))
                    returnAuthors.add(authors.get(i));
            }
        }
        return returnAuthors;
    }

}