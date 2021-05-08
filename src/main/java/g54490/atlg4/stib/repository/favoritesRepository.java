package g54490.atlg4.stib.repository;

import g54490.atlg4.stib.dto.FavoritesDto;
import g54490.atlg4.stib.jdbc.FavoriteDao;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class favoritesRepository implements Repository<String, FavoritesDto> {

    private FavoriteDao dao;

    /**
     *
     * @throws IOException
     */
    public favoritesRepository() throws IOException {
        dao = FavoriteDao.getInstance();
    }

    favoritesRepository(FavoriteDao dao) {
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
        return this.dao.selectAll();
    }

    @Override
    public FavoritesDto get(String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ObservableList<String> getStationName() throws IOException {
        ObservableList<String> favoritesName = FXCollections.observableArrayList();
        List<FavoritesDto> favorites;
        favorites = getAll();
        for (FavoritesDto dto : favorites) {
            favoritesName.add(dto.getKey());
        }
        return favoritesName;
    }

}
