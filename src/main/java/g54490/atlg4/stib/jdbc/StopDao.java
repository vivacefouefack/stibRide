package g54490.atlg4.stib.jdbc;

import g54490.atlg4.stib.dto.StationDto;
import g54490.atlg4.stib.dto.StopDto;
import g54490.atlg4.stib.model.Edge;
import g54490.atlg4.stib.repository.Dao;
import java.io.IOException;
import java.lang.module.ResolutionException;
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

    /**
     * allows to build the edges in the network of the stib.
     *
     * @return all edges of the stib network.
     */
    public List<Edge> selectAllEdge() {
        List<Edge> outDto = new ArrayList<>();
        String query = "SELECT * \n"
                + "FROM (STOPS stop1 JOIN STATIONS stat1 on stop1.id_station=stat1.id)Solution1 \n"
                + "JOIN(STOPS stop2 JOIN STATIONS stat2 on stop2.id_station=stat2.id)Solution2 \n"
                + "on \n"
                + "Solution1.id_line=Solution2.id_line \n"
                + "WHERE \n"
                + "(Solution1.id_order - 1 >0 AND \n"
                + "Solution2.id_order=Solution1.id_order - 1) \n"
                + "OR (Solution1.id_order + 1<(SELECT max(SS1.id_order)FROM \n"
                + "STOPS SS1 JOIN STOPS SS2 \n"
                + "on SS1.id_line=SS2.id_line \n"
                + "WHERE SS1.id_line=SS2.id_line) \n"
                + "AND \n"
                + "Solution2.id_order=Solution1.id_order+1);\n"
                + "";

        try {
            Statement stmt = connexion.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {

                String name = result.getString(5) + result.getString(10);
                StationDto origin = new StationDto(result.getInt(2), result.getString(5));
                StationDto destination = new StationDto(result.getInt(7), result.getString(10));
                outDto.add(new Edge(name, origin, destination));
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return outDto;
    }
    
    /**
     * 
     * @param key
     * @return 
     */
    public String selectAllLineInSTation(String key){
        if (key == null) {
            throw new IllegalArgumentException("error ");
        }
        String lines="[";
        String sql = "SELECT id_line FROM STOPS WHERE id_station=?";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setString(1, key);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                lines+=result.getString(1)+" ";
            }
            lines+="]";
        } catch (ResolutionException | SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return lines;
    }


}
