package g54490.atl4.sortingRace.model;

/**
 *
 * @author g54490@etu.he2b.be
 */
public class Choice {
    private int nbThreads;
    private String sortType;
    private String configuration;

    public Choice(int nbThreads, String sortType, String configuration) {
        this.nbThreads = nbThreads;
        this.sortType = sortType;
        this.configuration = configuration;
    }

    public int getNbThreads() {
        return nbThreads;
    }

    public String getSortType() {
        return sortType;
    }

    public String getConfiguration() {
        return configuration;
    }
    
    
}
