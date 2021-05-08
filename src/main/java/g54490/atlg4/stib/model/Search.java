package g54490.atlg4.stib.model;

import g54490.atlg4.stib.dto.StationDto;
import g54490.atlg4.stib.jdbc.StationDao;
import g54490.atlg4.stib.repository.StationRepository;
import g54490.atlg4.stib.repository.StopRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class Search {

    private List<ResultData> resultData;
    private String origin;
    private String destination;
    private int nbtation;

    /**
     * constructor of search. takes the origin and the destination and finds the
     * most common route in the metro network of the stib
     *
     * @param origine
     * @param destination
     */
    public Search(String origine, String destination) {
        this.resultData = new ArrayList<>();
        this.origin = origine;
        this.destination = destination;
        this.nbtation = 0;
    }

    /**
     *
     * @return
     */
    public int getNbtation() {
        return nbtation;
    }

    /**
     * takes the origin and the destination and finds the most common route in
     * the metro network of the stib.
     *
     * @return list of stations found during the search.
     */
    public List<ResultData> getResultData() {
        try {
            StationRepository station = new StationRepository();
            StopRepository stop = new StopRepository();
            List<Edge> edges = stop.selectAllEdge();
            Graphe graph = new Graphe(station.getAll(), edges);
            DijkstraAlgorithm algo = new DijkstraAlgorithm(graph);

            StationDto origin = station.selectGetName(this.origin);
            algo.execute(origin);
            StationDto destination = station.selectGetName(this.destination);
            List<StationDto> allResultStation = algo.getPath(destination);

            for (StationDto list : allResultStation) {
                this.nbtation++;
                this.resultData.add(new ResultData(list.getStationName(), stop.selectAllLineInSTation(list.getKey().toString())));
            }
        } catch (IOException o) {
            System.out.println(o);
        }
        return resultData;
    }

    public static void main(String[] args) {
        Search top = new Search("PARC", "HANKAR");
        List<ResultData> resultData = top.getResultData();

        for (ResultData list : resultData) {
            System.out.println(list.getNameStation() + "**********" + list.getLines());
        }

        System.out.println("nombre de d'arret totale :" + top.getNbtation());

    }

}
