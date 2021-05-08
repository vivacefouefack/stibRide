package g54490.atlg4.stib.repository;

import g54490.atlg4.stib.dto.StationDto;
import g54490.atlg4.stib.jdbc.StationDao;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class StationRepository implements Repository<Integer, StationDto> {

    private final StationDao dao;

    /**
     * constructor.
     *
     * @throws IOException if the instantiation fails.
     */
    public StationRepository() throws IOException {
        dao = StationDao.getInstance();
    }

    StationRepository(StationDao dao) {
        this.dao = dao;
    }

    /**
     * allows you to build the list of stations to be displayed in the search
     * area.
     *
     * @return all station name.
     * @throws IOException if the connection to the database fails.
     */
    public ObservableList<String> getStationName() throws IOException {
        ObservableList<String> stationName = FXCollections.observableArrayList();
        List<StationDto> stations;
        stations = getAll();
        for (StationDto dto : stations) {
            stationName.add(dto.getStationName());
        }
        return stationName;
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

    public StationDto selectGetName(String key) {
        return this.dao.selectGetName(key);
    }

}
