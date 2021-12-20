package at.technikumwien;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CashOutRepository extends JpaRepository<CashOutAuthor, Long> {
}
