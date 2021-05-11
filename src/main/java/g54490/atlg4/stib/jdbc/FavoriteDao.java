package g54490.atlg4.stib.jdbc;

import g54490.atlg4.stib.dto.FavoritesDto;
import g54490.atlg4.stib.repository.Dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * this class allows us to access data from the favorites table of the database.
 *
 * @author 54490@etu.he2b.be
 */
public class FavoriteDao implements Dao<String, FavoritesDto> {

    private Connection connexion;

    /**
     * constructor of favoriteDao.
     * @throws IOException if the connection to the database fails.
     */
    public FavoriteDao() throws IOException {
        connexion = DatabaseManager.getInstance().getConnection();

    }

  
    /**
     * allows to get an instance of the class for security reasons.
     * @return the class instance;
     * @throws IOException if he cannot access the database.
     */
    public static FavoriteDao getInstance() throws IOException {
        return FavoriteDaoHolder.getInstance();
    }

   /**
    * creates a instance of the FavoriteDao for security reasons.
    */
    private static class FavoriteDaoHolder {

        private static FavoriteDao getInstance() throws IOException {
            return new FavoriteDao();
        }
    }

    @Override
    public String insert(FavoritesDto item) {
        if (item == null) {
            throw new IllegalArgumentException("Aucune élément donné en paramètre");
        }
        String name = "";
        String sql = "INSERT INTO FAVORITES(name,origine,destination) values(?, ? ,?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, item.getKey());
            pstmt.setString(2, item.getOrigin());
            pstmt.setString(3, item.getDestination());
            pstmt.executeUpdate();
            ResultSet result = pstmt.getGeneratedKeys();
            while (result.next()) {
                name = result.getString(2);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return name;
    }

    @Override
    public void delete(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Aucune clé donnée en paramètre");
        }
        String sql = "DELETE FROM FAVORITES WHERE name = ?";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void update(FavoritesDto item) {
        if (item == null) {
            throw new IllegalArgumentException("Aucune élément donné en paramètre");
        }
        String sql = "UPDATE FAVORITES SET name=? ,origine=? destination=? where name=? ";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, item.getKey());
            pstmt.setString(2, item.getOrigin());
            pstmt.setString(3, item.getDestination());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public List<FavoritesDto> selectAll() {
        String query = "SELECT * FROM FAVORITES";
        List<FavoritesDto> dtos = new ArrayList<>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                FavoritesDto oneFavoti = new FavoritesDto(result.getString(1), result.getString(2), result.getString(3));
                dtos.add(oneFavoti);
            }
        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());
        }
        return dtos;
    }

    @Override
    public FavoritesDto select(String key) {
        String query = "SELECT * FROM FAVORITES WHERE name=?";
        FavoritesDto dto = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(query)) {
            pstmt.setString(1, key);
            ResultSet result = pstmt.executeQuery();
            dto = new FavoritesDto(result.getString(1), result.getString(2), result.getString(3));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return dto;
    }
}
