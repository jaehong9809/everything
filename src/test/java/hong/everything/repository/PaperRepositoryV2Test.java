package hong.everything.repository;

import com.zaxxer.hikari.HikariDataSource;
import hong.everything.domain.Paper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import java.sql.DriverManager;

import static hong.everything.repository.ConnectionConst.*;
import static org.junit.jupiter.api.Assertions.*;

class PaperRepositoryV2Test {
    PaperRepositoryV2 repository;

    @BeforeEach
    void beforeEach() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);


       // DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        repository = new PaperRepositoryV2(dataSource);
    }

    @Test
    void create() {
        Paper paper=new Paper("qwer");
        paper.setId(1L);
    }

    @Test
    void findbyid() throws NoSuchFieldException {
        Long id=1L;
        Paper byId = repository.findById(id);
        System.out.println("byId.getWords() = " + byId.getWords());
        System.out.println("byId.getId() = " + byId.getId());
    }

    @Test
    void update() throws NoSuchFieldException {
        String string="1a2a3a4a";
        Long id=1L;
        String string1 = string;
        repository.update(id, string1);
        System.out.println(repository.findById(id));
    }

    @Test
    void delete(){
        Long id=1L;
        repository.delete(id);

    }
}