package g54490.atlg4.stib.model;

import g54490.atlg4.stib.dto.StationDto;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class Edge {
    private final String id;
    private final StationDto origin;
    private final StationDto destination;
    private final int weight=1;

    /**
     * constructor of edge.
     * @param id identifiers.
     * @param origin departure station.
     * @param destination represents the destination station.
     */
    public Edge(String id, StationDto origin,StationDto destination) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * getter.
     * @return id of edge.
     */
    public String getId() {
        return id;
    }
    
    /**
     * getter.
     * @return destination.
     */
    public StationDto getDestination() {
        return destination;
    }

    /**
     * getter.
     * @return origin.
     */
    public StationDto getOrigin() {
        return origin;
    }
    
    /**
     * getter.
     * allows to obtain the distance between two stations.
     * @return distance between two stations.
     */
    public int getWeight() {
        return weight;
    }

}
