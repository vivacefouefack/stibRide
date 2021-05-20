package g54490.atlg4.stib.repository;

import g54490.atlg4.stib.dto.FavoritesDto;
import g54490.atlg4.stib.jdbc.FavoriteDao;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class FavoriteRepository implements Repository<Integer, FavoritesDto> {

    private FavoriteDao dao;

    /**
     * constructor.
     *
     * @throws IOException if the connection attempt fails.
     */
    public FavoriteRepository() throws IOException {
        dao = FavoriteDao.getInstance();
    }

    FavoriteRepository(FavoriteDao dao) {
        this.dao = dao;
    }

    @Override
    public Integer add(FavoritesDto item) {
        return dao.insert(item);
    }

    @Override
    public void remove(Integer key) {
        this.dao.delete(key);
    }

    @Override
    public List<FavoritesDto> getAll() {
        return this.dao.selectAll();
    }

    @Override
    public FavoritesDto get(Integer key) {
        return this.dao.select(key);
    }

    @Override
    public boolean contains(Integer key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public FavoritesDto getName(String key) {
        return this.dao.selectName(key);
    }

    /**
     * allows you to update a favorite.
     * @param item faforite to update.
     */
    public void update(FavoritesDto item) {
        this.dao.update(item);
    }

}
