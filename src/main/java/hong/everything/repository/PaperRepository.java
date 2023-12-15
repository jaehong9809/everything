package hong.everything.repository;

import hong.everything.domain.Paper;

import java.util.List;
import java.util.Optional;

public interface PaperRepository {

    Paper save(Paper paper);

    void update(Long id, String words);

    Optional<Paper> findById(long id);

    List<Paper> findAll();

    void delete(Long id);
}
