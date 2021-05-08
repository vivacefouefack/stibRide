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
public class FavoriteRepository implements Repository<String, FavoritesDto>{
    private FavoriteDao dao;
    
    
    public  FavoriteRepository () throws IOException {
        dao = FavoriteDao.getInstance();
    }

    FavoriteRepository (FavoriteDao dao) {
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
        return this.dao.select(key);
    }

    @Override
    public boolean contains(String key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public ObservableList<String> getFavoritesName() throws IOException {
        ObservableList<String> favoriteName = FXCollections.observableArrayList();
        List<FavoritesDto> stations;
        stations = getAll();
        for (FavoritesDto dto : stations) {
            favoriteName.add(dto.getKey());
        }
        return favoriteName;
    }

    
    
}
