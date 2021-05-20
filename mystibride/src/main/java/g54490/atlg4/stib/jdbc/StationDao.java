package g54490.atlg4.stib.jdbc;

import g54490.atlg4.stib.dto.StationDto;
import g54490.atlg4.stib.repository.Dao;
import java.io.IOException;
import java.lang.module.ResolutionException;
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

    /**
     * creates an instance of the class.
     */
    private static class StationDaoHolder {

        private static StationDao getInstance() throws IOException {
            return new StationDao();
        }
    }

    /**
     * searches for a station in the database through the name of the station
     * received in parameter.
     *
     * @param key name of station.
     * @return the object station whose name is key.
     */
    public StationDto selectGetName(String key) {
        if (key == null) {
            throw new IllegalArgumentException("error ");
        }
        String sql = "SELECT id,name FROM STATIONS WHERE name=?";
        StationDto dto = null;
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setString(1, key);
            ResultSet result = pstmt.executeQuery();
            int count = 0;
            while (result.next()) {
                dto = new StationDto(result.getInt(1), result.getString(2));
                count++;
            }
            if (count > 1) {
                throw new ResolutionException("too many key " + key);
            }
        } catch (ResolutionException | SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return dto;
    }
}
