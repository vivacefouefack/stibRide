package g54490.atlg4.stib.jdbc;

import g54490.atlg4.stib.dto.LineDto;
import g54490.atlg4.stib.repository.Dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author 54490@etu.he2b.be
 */
public class LineDao implements Dao<Integer, LineDto> {
    
    private Connection connexion;

    /**
     * default private constructor which will try the connection to the database.
     * @throws IOException if he cannot access the database.
     */
    private LineDao() throws IOException {
        connexion = DatabaseManager.getInstance().getConnection();
    }

    /**
     * allows to get an instance of the class since the constructor is private.
     * @return the class instance;
     * @throws IOException if he cannot access the database.
     */
    public static LineDao getInstance() throws IOException {
        return LineDao.LineDaoHolder.getInstance();
    }
    
    
    @Override
    public List<LineDto> selectAll() {
        String query="SELECT * FROM LINES";
        List<LineDto> dtos = new ArrayList<LineDto>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet result=statement.executeQuery(query);
            while (result.next()) {
                LineDto dto = new LineDto(result.getInt(1));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            System.out.println("erreur "+ e.getMessage());
        }
        return dtos;
    }

    @Override
    public LineDto select(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Integer insert(LineDto item) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(LineDto item) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    private static class LineDaoHolder {

        private static LineDao getInstance() throws IOException {
            return new LineDao();
        }
    }
      
}
