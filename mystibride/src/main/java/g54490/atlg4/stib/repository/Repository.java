package g54490.atlg4.stib.repository;

import g54490.atlg4.stib.dto.Dto;
import java.util.List;

/**
 *
 * @author 54490@etu.he2b.be
 */
public interface Repository<K, T extends Dto<K>> {

    /**
     * Add an element to the repository.If the element exists, the repository
     * updates this element.
     *
     * @param item the element to add.
     * @return the element's key, usefull when the key is auto-generated.
     */
    K add(T item); 

    /**
     * Removes the element of the specific key.
     *
     * @param key key of the element to removes.
     */
    void remove(K key);

    /**
     * Returns all the elements of the repository.
     *
     * @return all the elements of the repository.
     *
     */
    List<T> getAll();

    /**
     * Return the element of the repository with the specific key.
     *
     * @param key key of the element.
     *
     * @return the element of the repository with the specific key.
     */
    T get(K key);

    /**
     * Returns true if the element exist in the repository and false otherwise.
     * An element is found by this key.
     *
     * @param key key of the element.
     * @return true if the element exist in the repository and false otherwise.
     */
    boolean contains(K key);

}
