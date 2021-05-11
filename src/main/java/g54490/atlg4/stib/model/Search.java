package g54490.atlg4.stib.model;

import g54490.atlg4.stib.dto.StationDto;
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
            DijkstraAlgorithm search = new DijkstraAlgorithm(graph);
            StationDto origin = station.selectGetName(this.origin);
            search.execute(origin);
            StationDto destination = station.selectGetName(this.destination);
            List<StationDto> allResultStation = search.getPath(destination);

            for (StationDto list : allResultStation) {
                this.resultData.add(new ResultData(list.getStationName(), stop.selectAllLineInSTation(list.getKey().toString())));
            }
        } catch (IOException o) {
            System.out.println(o);
        }
        return resultData;
    }
}
