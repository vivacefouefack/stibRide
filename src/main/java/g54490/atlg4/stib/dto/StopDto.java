package g54490.atlg4.stib.dto;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class StopDto extends Dto<Integer>{
    
    private int line;
    private int ordre;
    
    /**
     * constructor.
     * @param key uniquely represents a station.
     */
    public StopDto(Integer key) {
        super(key);
    }

    /**
     * constructor of stop.
     * @param key uniquely represents a station.
     * @param line line number of subway.
     * @param ordre serial number in the stib network.
     */
    public StopDto( Integer key,int line, int ordre) {
        super(key);
        this.line = line;
        this.ordre = ordre;
    }

    /**
     * simple getter.
     * @return line number.
     */
    public int getLine() {
        return line;
    }

    /**
     * simple getter.
     * @return order number.
     */
    public int getOrdre() {
        return ordre;
    }
    
    
    
}
