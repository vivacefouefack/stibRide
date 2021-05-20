package g54490.atlg4.stib.model;

import g54490.atlg4.stib.obersers.Observer;
import g54490.atlg4.stib.obersers.Observable;
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
        this.searchFavoryData = FXCollections.observableArrayList(getallFavoriteName());
    }

    /**
     * allows you to have the name of all the favorites.
     *
     * @return all favorites name.
     */
    public List<String> getallFavoriteName() {
        List<String> favoriteName = new ArrayList();
        List<FavoritesDto> stations;
        stations = favorites.getAll();
        for (FavoritesDto dto : stations) {
            favoriteName.add(dto.getName());
        }
        return favoriteName;
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
     * notify the view of a favorite update.
     *
     * @param favorite object to update.
     */
    public void updateFavorites(FavoritesDto favorite) {
        this.favorites.update(favorite);
        notifyObservers(favorites);
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
     * notify the view if the my favorites tab is requested in order to display
     * a new window.
     */
    public void computeNewFavoriteStage() {
        notifyObservers(1);
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
        notifyObservers(search);
    }

    /**
     * allows you to add a favorite received as a parameter in the database and
     * notify presenter when finished.
     *
     * @param item favorite favorite to add.
     * @throws IOException en cas d"erreur d'actualisation de la base de donnée.
     */
    public void computeAddFavory(FavoritesDto item) throws IOException {
        this.favorites.add(item);
        this.searchFavoryData = FXCollections.observableArrayList(getallFavoriteName());
        notifyObservers(item.getName());
    }

    /**
     * used to recalculate the shortest path of a favorite received as a
     * parameter and notify presenter when finished.
     *
     * @param favorite favorite name.
     */
    public void computeConsultFavory(String favorite) {
        dto = favorites.get(favorites.getName(favorite).getKey());
        this.search = new Search(dto.getOrigin(), dto.getDestination());
        this.dataFavoris = search.getResultData();
        notifyObservers(this);
    }

    /**
     * allows you to have the key of a favorite through its name.
     *
     * @param name favorite name.
     * @return key of favorite name.
     */
    public int computeKey(String name) {
        return favorites.getName(name).getKey();
    }

    /**
     * allows you to delete a favorite received as a parameter and notifies the
     * view once the deletion is complete.
     *
     * @param favorite argument to enter.
     */
    public void computeDeletefavorite(String favorite) {
        favorites.remove(favorites.getName(favorite).getKey());
        notifyObservers(dto);
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
