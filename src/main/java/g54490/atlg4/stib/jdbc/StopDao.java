package g54490.atlg4.stib.jdbc;

import g54490.atlg4.stib.dto.StopDto;
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
public class StopDao implements Dao<Integer, StopDto> {

    private Connection connexion;

    /**
     * default private constructor which will try the connection to the
     * database.
     *
     * @throws IOException if he cannot access the database.
     */
    private StopDao() throws IOException {
        connexion = DatabaseManager.getInstance().getConnection();
    }

    /**
     * allows to get an instance of the class since the constructor is private.
     *
     * @return the class instance;
     * @throws IOException if he cannot access the database.
     */
    public static StopDao getInstance() throws IOException {
        return StopDaoHolder.getInstance();
    }

    @Override
    public List<StopDto> selectAll() {
        String query = "SELECT * FROM STOPS";
        List<StopDto> dtos = new ArrayList<StopDto>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                StopDto dto = new StopDto(result.getInt(1), result.getInt(2), result.getInt(3));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
        }
        return dtos;
    }

    @Override
    public StopDto select(Integer key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer insert(StopDto item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(StopDto item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static class StopDaoHolder {

        private static StopDao getInstance() throws IOException {
            return new StopDao();
        }
    }

}
