package g54490.atlg4.stib.model;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class ResultData {

    private String nameStation;
    private String line;

    /**
     * constructor of resultData.
     *
     * @param station name of a station.
     * @param line set of lines that pass through a station.
     */
    public ResultData(String station, String line) {
        this.nameStation = station;
        this.line = line;
    }

    /**
     * getter.
     *
     * @return name of station.
     */
    public String getNameStation() {
        return nameStation;
    }

    /**
     * getter.
     *
     * @return line set of lines that pass through a station.
     */
    public String getLine() {
        return line;
    }

}
