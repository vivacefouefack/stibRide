package g54490.atlg4.stib.jdbc;

import g54490.atlg4.stib.dto.FavoritesDto;
import g54490.atlg4.stib.exception.ExceptionsClasse;
import g54490.atlg4.stib.repository.Dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this class allows us to access data from the favorites table of the database.
 *
 * @author 54490@etu.he2b.be
 */
public class FavoriteDao implements Dao<Integer, FavoritesDto> {

    private Connection connexion;

    /**
     * constructor of favoriteDao.
     *
     * @throws IOException if the connection to the database fails.
     */
    public FavoriteDao() throws IOException {
        connexion = DatabaseManager.getInstance().getConnection();

    }

    /**
     * allows to get an instance of the class for security reasons.
     *
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
    public void delete(Integer key) {
        if (key == null) {
            throw new IllegalArgumentException("Aucune clé donnée en paramètre");
        }
        String sql = "DELETE FROM FAVORITES WHERE id = ?";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public FavoritesDto select(Integer key) {
        String query = "SELECT id,name,origine,destination FROM FAVORITES WHERE id=?";
        FavoritesDto dto = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(query)) {
            pstmt.setInt(1, key);
            ResultSet result = pstmt.executeQuery();
            dto = new FavoritesDto(result.getInt(1), result.getString(2), result.getString(3),result.getString(4));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return dto;
    }

    @Override
    public Integer insert(FavoritesDto item) {
        if (item == null) {
            try {
                throw new ExceptionsClasse("Aucune élément donné en paramètre");
            } catch (ExceptionsClasse ex) {
                Logger.getLogger(FavoriteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Integer id = 0;
        String query = "INSERT INTO FAVORITES(name,origine,destination) values(?, ? ,?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(query)) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getOrigin());
            pstmt.setString(3, item.getDestination());
            pstmt.executeUpdate();

            ResultSet result = pstmt.getGeneratedKeys();
            while (result.next()) {
                id = result.getInt(1);
            }
        } catch (SQLException e) {
            try {
                throw new ExceptionsClasse(e);
            } catch (ExceptionsClasse ex) {
                Logger.getLogger(FavoriteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    public FavoritesDto selectName(String key) {
        String query = "SELECT id,name,origine,destination FROM FAVORITES WHERE name=?";
        FavoritesDto dto = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(query)) {
            pstmt.setString(1, key);
            ResultSet result = pstmt.executeQuery();
            dto = new FavoritesDto(result.getInt(1), result.getString(2), result.getString(3),result.getString(4));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return dto;
    }

    @Override
    public void update(FavoritesDto item) {
        if (item == null) {
            try {
                throw new ExceptionsClasse("Aucune élément donné en paramètre");
            } catch (ExceptionsClasse ex) {
                Logger.getLogger(FavoriteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String query = "UPDATE FAVORITES SET name=?,origine=? ,destination=? where id=? ";
        try (PreparedStatement pstmt = connexion.prepareStatement(query)) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getOrigin());
            pstmt.setString(3, item.getDestination());
            pstmt.setInt(4, item.getKey());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            try {
                throw new ExceptionsClasse(e.getMessage());
            } catch (ExceptionsClasse ex) {
                Logger.getLogger(FavoriteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                FavoritesDto oneFavoti =new FavoritesDto(result.getInt(1), result.getString(2), result.getString(3),result.getString(4));
                dtos.add(oneFavoti);
            }
        } catch (SQLException e) {
            try {
                throw new ExceptionsClasse(e.getMessage());
            } catch (ExceptionsClasse ex) {
                Logger.getLogger(FavoriteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dtos;
    }

}
