package at.technikumwien;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticlesRepository extends JpaRepository<Articles, Long> {

    @Override
    @EntityGraph("Articles.fetchAttractionAuthors")
    Optional<Articles> findById(Long id);

    @Override
    @EntityGraph("Articles.fetchAttractionAuthors")
    List<Articles> findAll();
}
