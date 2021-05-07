package g54490.atlg4.stib.model;

import g54490.atlg4.stib.dto.StationDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author vivac
 */
public class DijkstraAlgorithm {
    
    private  List<StationDto> stations;
    private  List<Edge> Edges;
    private Set<StationDto> settledNodes;
    private Set<StationDto> unSettledNodes;
    private Map<StationDto, StationDto> predecessors;
    private Map<StationDto, Integer> distance;

    /**
     * constructor.
     * @param graph represents the stib metro network.
     */
    public DijkstraAlgorithm(Graphe graph) {
        //create a copy of the array so that we can operate on this array
        this.stations = new ArrayList<>(graph.getStation());
        this.Edges = new ArrayList<>(graph.getAret());
    }

    public void execute(StationDto source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            StationDto node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(StationDto node) {
        List<StationDto> adjacentNodes = getNeighbors(node);
        for (StationDto target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(StationDto node, StationDto target) {
        for (Edge edge : Edges) {
            if (edge.getOrigin().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<StationDto> getNeighbors(StationDto node) {
        List<StationDto> neighbors = new ArrayList<StationDto>();
        for (Edge aret : Edges) {
            if (aret.getOrigin().equals(node)
                    && !isSettled(aret.getDestination())) {
                neighbors.add(aret.getDestination());
            }
        }
        return neighbors;
    }

    private StationDto getMinimum(Set<StationDto> stations) {
       StationDto minimum = null;
        for (StationDto station : stations) {
            if (minimum == null) {
                minimum = station;
            } else {
                if (getShortestDistance(station) < getShortestDistance(minimum)) {
                    minimum = station;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(StationDto station) {
        return settledNodes.contains(station);
    }

    private int getShortestDistance(StationDto destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<StationDto> getPath(StationDto target) {
        LinkedList<StationDto> path = new LinkedList<StationDto>();
        StationDto step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
}
