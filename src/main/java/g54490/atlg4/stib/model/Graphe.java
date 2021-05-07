package g54490.atlg4.stib.model;

import g54490.atlg4.stib.dto.StationDto;
import java.util.List;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class Graphe {

    private List<StationDto> stations;
    private List<Edge> edges;

    /**
     * constructor. builds the stib network in the form of a graph.
     *
     * @param stations represents all the vertices of the graph.
     * @param arets represents all the edges of the graph.
     */
    public Graphe(List<StationDto> stations, List<Edge> arets) {
        this.stations = stations;
        this.edges = arets;
    }

    /**
     * getter.
     *
     * @return all vertices of graph.
     */
    public List<StationDto> getStation() {
        return stations;
    }

    /**
     * getter.
     *
     * @return all edge of graph.
     */
    public List<Edge> getAret() {
        return edges;
    }
}
