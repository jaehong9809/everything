package hong.everything.repository;

import hong.everything.domain.Paper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static hong.everything.repository.DBConnectionutil.getConnection;

public class PaperRepositoryV3 {
    private Long id=0L;
    private final JdbcTemplate template;

    public PaperRepositoryV3(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    public List<Paper> findAll(){
        String sql="select * from paper";
        return template.query(sql, new RowMapper<Paper>() {
            @Override
            public Paper mapRow(ResultSet rs, int rowNum) throws SQLException {
                Paper paper = new Paper(rs.getString("words"));
                paper.setId(rs.getLong("id"));
                return paper;
            }
        });
    }
    public Paper save(String words) {
        String sql="insert into paper(id, words) values(?, ?)";
        Paper paper = new Paper(words);
        paper.setId(id++);
        template.update(sql, paper.getId(), paper.getWords());
        return paper;
    }

    public Paper findById(Long id) throws NoSuchFieldException {
        String sql="select * from paper where id=?";
        return template.queryForObject(sql, new RowMapper<Paper>() {
            @Override
            public Paper mapRow(ResultSet rs, int rowNum) throws SQLException {
                Paper paper = new Paper(rs.getString("words"));
                paper.setId(rs.getLong("id"));
                return paper;
            }
        }, id);
    }
    public void update(Long id, String words) {
        String sql = "update paper set words=? where id=?";
        template.update(sql, words, id);
    }

    public void delete(Long id){
        String sql = "delete from paper where id=?";
        template.update(sql, id);
    }
}
