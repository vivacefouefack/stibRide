package g54490.atlg4.stib.repository;

import g54490.atlg4.stib.dto.StopDto;
import g54490.atlg4.stib.jdbc.StopDao;
import g54490.atlg4.stib.model.Edge;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class StopRepository implements Repository<Integer, StopDto> {

    private final StopDao dao;

    /**
     * constructor.
     *
     * @throws IOException if the instantiation fails.
     */
    public StopRepository() throws IOException {
        dao = StopDao.getInstance();
    }

    StopRepository(StopDao dao) {
        this.dao = dao;
    }

    @Override
    public List<StopDto> getAll() {
        return dao.selectAll();
    }

   
    /**
     * allows to build the edges in the network of the stib.
     *
     * @return all edges of the stib network.
     */
    public List<Edge> selectAllEdge() {
        return this.dao.selectAllEdge();
    }
    
    /**
     * allows you to select all the metro lines passing through a station.
     *
     * @param key station key.
     * @return a string representing the line passing by this station.
     */
    public String selectAllLineInSTation(String key){
        return this.dao.selectAllLineInSTation(key);
    }
    
    @Override
    public Integer add(StopDto item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Integer key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StopDto get(Integer key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(Integer key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
