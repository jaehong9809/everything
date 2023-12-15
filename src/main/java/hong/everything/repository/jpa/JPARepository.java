package hong.everything.repository.jpa;

import hong.everything.domain.Paper;
import hong.everything.repository.PaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JPARepository implements PaperRepository {
    private final SpringDataJPAPaperRepository repository;
    @Override
    public Paper save(Paper paper) {
        return repository.save(paper);
    }

    @Override
    public void update(Long id, String words) {
        Paper paper = repository.findById(id).orElseThrow();
        paper.setWords(words);

    }

    @Override
    public Optional<Paper> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Paper> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        Optional<Paper> paper = repository.findById(id);
        Paper delete = new Paper();
        if(paper.isPresent()) {
            delete=paper.get();
        }
        repository.delete(delete);
    }
}
