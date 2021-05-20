package g54490.atl4.sortingRace.model;

/**
 *
 * @author g54490@etu.he2b.be
 */
public class Data {
    
    private String tri;
    private int taille;
    private int operation;
    private long duree;

    /**
     * 
     * @param typeTri
     * @param taille
     * @param operation
     * @param duree 
     */
    public Data(String typeTri,int taille,int operation,long duree){
        this.tri=typeTri;
        this.taille=taille;
        this.operation=operation;
        this.duree=duree;
    }

    /**
     * 
     * @return 
     */
    public String getTri() {
        return tri;
    }

    /**
     * 
     * @return 
     */
    public int getTaille() {
        return taille;
    }

    /**
     * 
     * @return 
     */
    public int getOperation() {
        return operation;
    }

    /**
     * 
     * @return 
     */
    public long getDuree() {
        return duree;
    }

    
    
}
