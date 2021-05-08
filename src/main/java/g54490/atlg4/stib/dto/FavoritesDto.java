package g54490.atlg4.stib.dto;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class FavoritesDto extends Dto<String>{
    private String origin;
    private String destination;
    
    /**
     * constructor of favoritesDto.
     * @param name favorite name.
     * @param origine favorite origin.
     * @param destination favorite destination.
     */
    public FavoritesDto(String name,String origine,String destination) {
        super(name);
        this.origin=origine;
        this.destination=destination;
    }

    /**
     * getter
     * @return origin name.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * getter.
     * @return destination name.
     */
    public String getDestination() {
        return destination;
    } 
}
