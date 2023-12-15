package hong.everything.repository;


import hong.everything.domain.Paper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@SpringBootTest
public class RepositoryTest {

    @Autowired
    PaperRepository repository;

    @Test
    void save(){
        Paper paper = new Paper("abc");
        Paper save = repository.save(paper);

        Optional<Paper> byId = repository.findById(save.getId());
        byId.ifPresent(value -> Assertions.assertThat(value).isEqualTo(save));
    }
}
