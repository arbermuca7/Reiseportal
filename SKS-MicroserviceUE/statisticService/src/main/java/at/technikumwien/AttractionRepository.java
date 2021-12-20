package at.technikumwien;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    //public List<Attraction> findAllOrderByTimesVisited();
}
