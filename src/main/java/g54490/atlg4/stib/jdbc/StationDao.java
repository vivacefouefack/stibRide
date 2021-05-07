package g54490.atlg4.stib.jdbc;

import g54490.atlg4.stib.dto.StationDto;
import g54490.atlg4.stib.repository.Dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class StationDao implements Dao<Integer, StationDto> {

    private Connection connexion;

    /**
     * default private constructor which will try the connection to the
     * database.
     *
     * @throws IOException if he cannot access the database.
     */
    public StationDao() throws IOException {
        connexion = DatabaseManager.getInstance().getConnection();
    }

    /**
     * allows to get an instance of the class since the constructor is private.
     *
     * @return the class instance;
     * @throws IOException if he cannot access the database.
     */
    public static StationDao getInstance() throws IOException {
        return StationDaoHolder.getInstance();
    }

    @Override
    public ObservableList<StationDto> selectAll() {
        String query = "SELECT * FROM STATIONS";
        ObservableList<StationDto> dtos = FXCollections.observableArrayList();
        try {
            Statement statement = connexion.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                StationDto dto = new StationDto(result.getInt(1), result.getString(2));
                dtos.add(dto);
            }
        } catch (SQLException e) {
           Logger.getLogger(StationDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return dtos;
    }

    @Override
    public StationDto select(Integer key) {
        if (key == null) {
            throw new IllegalArgumentException("Aucune clé donnée en paramètre");
        }
        String sql = "SELECT id,name FROM STATIONS WHERE  id = ?";
        StationDto dto = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet result = pstmt.executeQuery();
            int i = 0;
            while (result.next()) {
                dto = new StationDto(result.getInt(1), result.getString(2));
            }
           
        } catch (SQLException e) {
            Logger.getLogger(StationDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return dto;
    }

    @Override
    public Integer insert(StationDto item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(StationDto item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static class StationDaoHolder {

        private static StationDao getInstance() throws IOException {
            return new StationDao();
        }
    }
}
