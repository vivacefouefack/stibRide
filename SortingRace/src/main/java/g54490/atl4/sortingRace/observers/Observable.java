package g54490.atl4.sortingRace.observers;


/**
 *
 * @author g54490@etu.he2b.be
 */
public interface Observable {

    /**
     * allows you to add new observer to the list of observers.
     *
     * @param observer represent an observer.
     */
    public void addObserver(Observer observer);

    /**
     * allows you to remove an observer from the list of observers
     * @param observer represent an observer.
     */
    public void removeObserver(Observer observer);

    /**
     * allows to notify all observers if a change of state occurs.
     */
    public void notifyObservers();
}
