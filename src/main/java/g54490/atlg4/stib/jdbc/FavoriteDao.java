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
 *
 * @author 54490@etu.he2b.be
 */
public class FavoriteDao  implements Dao<String, FavoritesDto> {
    
    private Connection connexion;

    /**
     * 
     * @throws IOException 
     */
    public FavoriteDao () throws IOException {
       connexion = DatabaseManager.getInstance().getConnection();

    }

    /**
     * 
     * @return
     * @throws IOException 
     */
    public static FavoriteDao  getInstance() throws IOException {
        return FavoriteDaoHolder.getInstance();
    }
    
    /**
     * 
     */
    private static class FavoriteDaoHolder {

        private static FavoriteDao  getInstance() throws IOException{
            return new FavoriteDao();
        }
    }
    
    /**
     * 
     * @param item
     * @return 
     */
    @Override
    public String insert(FavoritesDto item) {
        if (item == null) {
            throw new IllegalArgumentException("Aucune élément donné en paramètre");
        }
        String name="";
        String sql = "INSERT INTO FAVORITES(name,origine,destination) values(?, ? ,?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, item.getKey());
            pstmt.setString(2, item.getOrigin());
            pstmt.setString(3, item.getDestination());
            pstmt.executeUpdate();

            ResultSet result = pstmt.getGeneratedKeys();
            while (result.next()) {
                name= result.getString(2);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return name;
    }

    /**
     * 
     * @param name 
     */
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

    /**
     * 
     * @param item 
     */
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

    /**
     * 
     * @return 
     */
    @Override
    public List<FavoritesDto> selectAll() {
        String query="SELECT * FROM FAVORITES";
        List<FavoritesDto> dtos=new ArrayList<>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet result=statement.executeQuery(query);
            while (result.next()) {
                FavoritesDto oneFavoti = new FavoritesDto(result.getString(1), result.getString(2),result.getString(3));
                dtos.add(oneFavoti);
            }
        } catch (SQLException e) {
            System.out.println("erreur "+ e.getMessage());
        }
        return dtos;
    }

    @Override
    public FavoritesDto select(String key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public static void main(String[] args) throws IOException {
        FavoriteDao f=new FavoriteDao();
        f.delete("travail-sport");
       // String r=f.insert(new FavoritesDto("maison-tra", "HANKAR","PARC"));
       // System.out.println("nom du favori : "+r);
    }
    
}
