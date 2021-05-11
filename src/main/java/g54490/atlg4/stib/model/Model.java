package g54490.atlg4.stib.model;

import g54490.atlg4.stib.dto.FavoritesDto;
import g54490.atlg4.stib.repository.FavoriteRepository;
import g54490.atlg4.stib.repository.StationRepository;
import g54490.atlg4.stib.repository.StopRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author vivac
 */
public class Model  extends Observable {

    private int data;
    private Random r;
    private StationRepository stations;
    private StopRepository stops;
    private FavoriteRepository favorites;
    private Search search;
    private ObservableList<String> searchData;
    private ObservableList<String> searchFavoryData;
    private List<ResultData> datas;
    private FavoritesDto dto ;
    private List<ResultData> dataFavoris ;
    

    public Model() throws IOException {
        r = new Random();
        this.stations = new StationRepository();
        this.stops=new StopRepository();
        this.favorites=new FavoriteRepository();
        this.searchData= FXCollections.observableArrayList();
        this.searchFavoryData= FXCollections.observableArrayList();
        this.datas=new ArrayList<>();
        this.dataFavoris=new ArrayList<>();
    }

    public void initialize() throws IOException {
        this.searchData=stations.getStationName();
        this.searchFavoryData=favorites.getFavoritesName();
    }

    public ObservableList<String> getSearchData() {
        return searchData;
    }

    public ObservableList<String> getSearchFavoryData() {
        return searchFavoryData;
    }

    public void compute() {
        System.out.println("DEBUG | MODEL      | Calcul commencé");

        System.out.println("DEBUG | MODEL      | Calcul terminé");
        notifyObservers();
    }
    
    public void computePath(String origin,String destination){
        this.search=new Search(origin, destination);
        this.datas=search.getResultData();
        notifyObservers();
    }
    
    public void computeAddFavory(FavoritesDto item){
        this.favorites.add(item);
        notifyObservers();
    }
    
    public void computeConsultFavory(String namestation) {
        dto =favorites.get(namestation);
        this.search=new Search(dto.getOrigin(), dto.getDestination());
        this.dataFavoris=search.getResultData();
        notifyObservers();
    }

    public int getData1() {
        System.out.println("DEBUG | MODEL      | Demande des données");
        return data;
    }

    public List<ResultData> getDatas() {
        return datas;
    }

    public List<ResultData> getDataFavoris() {
        return dataFavoris;
    }
    
    @Override
    public void notifyObservers() {
        super.notifyObservers(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer); //To change body of generated methods, choose Tools | Templates.
    }
    
}
