package hong.everything.repository.jpa;

import hong.everything.domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJPAPaperRepository extends JpaRepository<Paper, Long> {
}
