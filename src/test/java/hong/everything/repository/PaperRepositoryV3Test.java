package hong.everything.repository;

import com.zaxxer.hikari.HikariDataSource;
import hong.everything.domain.Paper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

import static hong.everything.repository.ConnectionConst.*;

@SpringBootTest
class PaperRepositoryV3Test {
    static private PaperRepositoryV3 repository;
    @TestConfiguration
    static class TestConfig{
        private final DataSource dataSource;
        public TestConfig(DataSource dataSource){
            this.dataSource=dataSource;
            repository = new PaperRepositoryV3(dataSource);
        }
    }
    @Test
    void create() {
        Paper paper=new Paper("qwer");
        paper.setId(1L);

    }

    @Test
    void findbyid() throws NoSuchFieldException {

    }

    @Test
    void update() throws NoSuchFieldException {

    }

    @Test
    void delete(){
        Long id=1L;
        repository.delete(id);

    }
}