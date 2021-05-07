package g54490.atlg4.stib.repository;

import g54490.atlg4.stib.dto.LineDto;
import g54490.atlg4.stib.jdbc.LineDao;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class LineRepository implements Repository<Integer, LineDto> {

    private final LineDao dao;

    /**
     * constructor.
     * @throws IOException if the instantiation fails.
     */
    public LineRepository() throws IOException {
        dao = LineDao.getInstance();
    }

    LineRepository(LineDao dao) {
        this.dao = dao;
    }
    
    @Override
    public List<LineDto> getAll() {
       return dao.selectAll();
    }
    
    
    @Override
    public Integer add(LineDto item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LineDto get(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
