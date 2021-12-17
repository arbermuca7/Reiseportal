package at.technikumwien;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_blog_articles")
@NamedEntityGraph(
        name = "Articles.fetchAttractionAuthors",
        attributeNodes = {
                @NamedAttributeNode("attraction"),
                @NamedAttributeNode("authors")
        }
)
public class Articles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 1000)
    private String text;

    @Column(nullable = false)
    private LocalDate publicationDate;

    /*
    @Column(nullable = false)
    private int timesRead;
    */

    @ManyToOne(cascade = CascadeType.MERGE)   // EAGER
    @JoinColumn(name = "attractionid")
    private Attraction attraction;

    @ManyToMany(cascade = CascadeType.MERGE)   // LAZY
    @JoinTable(
            name = "t_articles_author",
            joinColumns = @JoinColumn(name = "articlesid"),
            inverseJoinColumns = @JoinColumn(name = "authorid")
    )
    private List<Author> authors;

    public Articles(String title, String text, LocalDate publicationDate, Attraction attraction, List<Author> authors) {
        this(null, title, text, publicationDate, attraction, authors);
    }
}
