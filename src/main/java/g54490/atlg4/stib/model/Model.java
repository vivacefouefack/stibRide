package g54490.atlg4.stib.model;

import g54490.atlg4.stib.dto.FavoritesDto;
import g54490.atlg4.stib.repository.FavoriteRepository;
import g54490.atlg4.stib.repository.StationRepository;
import g54490.atlg4.stib.repository.StopRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class Model extends Observable {

    private StationRepository stations;
    private StopRepository stops;
    private FavoriteRepository favorites;
    private Search search;
    private ObservableList<String> searchData;
    private ObservableList<String> searchFavoryData;
    private List<ResultData> datas;
    private FavoritesDto dto;
    private List<ResultData> dataFavoris;

    /**
     * constructor of model.
     *
     * @throws IOException if the connection to the database fails.
     */
    public Model() throws IOException {
        this.stations = new StationRepository();
        this.stops = new StopRepository();
        this.favorites = new FavoriteRepository();
        this.searchData = FXCollections.observableArrayList();
        this.searchFavoryData = FXCollections.observableArrayList();
        this.datas = new ArrayList<>();
        this.dataFavoris = new ArrayList<>();
    }

    /**
     * initializes the model.
     *
     * @throws IOException if the connection to the database fails.
     */
    public void initialize() throws IOException {
        this.searchData = stations.getStationName();
        this.searchFavoryData = favorites.getFavoritesName();
    }

    /**
     * gettter.
     *
     * @return a list containing the name of all the stations.
     */
    public ObservableList<String> getSearchData() {
        return searchData;
    }

    /**
     * getter.
     *
     * @return a list containing the name of all the favorites.
     */
    public ObservableList<String> getSearchFavoryData() {
        return searchFavoryData;
    }

    /**
     * 
     * @return 
     */
    public FavoriteRepository getFavorites() {
        return favorites;
    }
    

    /**
     * getter.
     *
     * @return a list of data to be displayed in the results table.
     */
    public List<ResultData> getDatas() {
        return datas;
    }

    /**
     * getter.
     *
     * @return a list of data to be displayed in the favorite results table.
     */
    public List<ResultData> getDataFavoris() {
        return dataFavoris;
    }

    /**
     *
     */
    public void compute() {
        notifyObservers();
    }

    /**
     * calculate the shortest path between origin and destination and notify
     * presenter when finished
     *
     * @param origin departure station.
     * @param destination destination station.
     */
    public void computePath(String origin, String destination) {
        this.search = new Search(origin, destination);
        this.datas = search.getResultData();
        notifyObservers();
    }

    /**
     * allows you to add a favorite received as a parameter in the database and
     * notify presenter when finished.
     *
     * @param item favorite favorite to add.
     */
    public void computeAddFavory(FavoritesDto item) {
        this.favorites.add(item);
        notifyObservers();
    }

    /**
     * used to recalculate the shortest path of a favorite received as a
     * parameter and notify presenter when finished.
     *
     * @param favorite favorite name.
     */
    public void computeConsultFavory(String favorite) {
        dto = favorites.get(favorite);
        this.search = new Search(dto.getOrigin(), dto.getDestination());
        this.dataFavoris = search.getResultData();
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
    }

}
