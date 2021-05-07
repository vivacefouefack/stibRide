package g54490.atlg4.stib.repository;

import g54490.atlg4.stib.dto.StationDto;
import g54490.atlg4.stib.jdbc.StationDao;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class StationRepository implements Repository<Integer, StationDto> {

    private final StationDao dao;

    /**
     * constructor.
     * @throws IOException if the instantiation fails.
     */
    public StationRepository() throws IOException {
        dao = StationDao.getInstance();
    }

    StationRepository(StationDao dao) {
        this.dao = dao;
    }
    
    @Override
    public List<StationDto> getAll() {
       return dao.selectAll();
    }
    
    @Override
    public Integer add(StationDto item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public StationDto get(Integer key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
