package g54490.atl4.sortingRace.observers;

import g54490.atl4.sortingRace.model.Threade;

/**
 *
 * @author g54490@etu.he2b.be
 */
public interface Observer {
    
    /**
     * allows the update according to the modifications of the observable.
     */
    public void update(Threade arg);//Observable observable
}
