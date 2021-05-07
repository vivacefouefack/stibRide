package g54490.atlg4.stib.dto;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class StationDto extends Dto<Integer>{
    
    private String stationName;
    
    /**
     * constructor of stationDto.
     * @param key uniquely represents a station.
     */
    public StationDto(Integer key) {
        super(key);
    }

    /**
     * constructor of stationDto.
     * @param key  uniquely represents a station.
     * @param stationName is the name of the station.
     */
    public StationDto(Integer key, String stationName) {
        super(key);
        this.stationName = stationName;
    }

    /**
     * getter of stationName.
     * @return name of station
     */
    public String getStationName() {
        return stationName;
    }
    
    
}
