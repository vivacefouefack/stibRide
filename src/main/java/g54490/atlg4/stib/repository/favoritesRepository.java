package g54490.atlg4.stib.repository;

import g54490.atlg4.stib.dto.FavoritesDto;
import g54490.atlg4.stib.jdbc.FavoriteDao;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class favoritesRepository implements Repository<String, FavoritesDto>{
    private FavoriteDao dao;
    
    /**
     * 
     * @throws IOException 
     */
    public  favoritesRepository () throws IOException {
        dao = FavoriteDao.getInstance();
    }

    favoritesRepository (FavoriteDao dao) {
        this.dao = dao;
    }
    
    @Override
    public String add(FavoritesDto item) {
       return dao.insert(item);
    }

    @Override
    public void remove(String key) {
        this.dao.delete(key);      
    }

    @Override
    public List<FavoritesDto> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FavoritesDto get(String key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean contains(String key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
    
}
