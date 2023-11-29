package hong.everything.repository;

import hong.everything.domain.Paper;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.unit.DataUnit;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaperRepositoryV2{
    private final DataSource dataSource;
    private Long id=0L;
    public PaperRepositoryV2(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Paper> findAll(){
        String sql="select * from paper";
        Connection con=null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        List<Paper> papers = new ArrayList<>();
        con = getConnection();
        try {
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Paper paper = new Paper();
                paper.setId(rs.getLong("id"));
                paper.setWords(rs.getString("words"));
                papers.add(paper);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return papers;
    }
    public Paper save(String words) {
        String sql="insert into paper(id, words) values(?, ?)";
        Connection con=null;
        PreparedStatement preparedStatement=null;
        Paper paper = new Paper(words);
        paper.setId(id++);
        con=getConnection();
        try {
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(2, paper.getWords());
            preparedStatement.setLong(1, paper.getId());
            preparedStatement.executeUpdate();
            return paper;
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }finally {
            close(con, preparedStatement, null);
        }
    }

    public Paper findById(Long id) throws NoSuchFieldException {
        String sql="select * from paper where id=?";
        Connection con=null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;

        con=getConnection();
        try {
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            rs=preparedStatement.executeQuery();
            if(rs.next()){
                Paper paper=new Paper();
                paper.setId(id);
                paper.setWords(rs.getString("words"));
                return paper;
            }else{
                throw new NoSuchFieldException("Error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con, preparedStatement,rs);
        }
    }
    public void update(Long id, String words) {
        String sql = "update paper set words=? where id=?";
        Connection con=null;
        PreparedStatement preparedStatement=null;

        con=getConnection();
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(2, id);
            preparedStatement.setString(1, words);
            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con, preparedStatement, null);
        }
    }
    private void close(Connection con, Statement st, ResultSet rs){
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(st);
        DataSourceUtils.releaseConnection(con, dataSource);
    }
    public void delete(Long id){
        String sql = "delete from paper where id=?";
        Connection con=null;
        PreparedStatement preparedStatement=null;
        con=getConnection();
        try {
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con, preparedStatement, null);
        }

    }

    private Connection getConnection(){
        Connection connection = DataSourceUtils.getConnection(dataSource);
        return connection;
    }

}
