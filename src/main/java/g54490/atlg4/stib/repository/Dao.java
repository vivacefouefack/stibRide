package g54490.atlg4.stib.repository;

import g54490.atlg4.stib.dto.Dto;
import java.util.List;

/**
 *
 * @author 54490@etu.he2b.be
 */
public interface Dao<K, T extends Dto<K>> {

    /**
     * Inserts an element into the resource.
     *
     * @param item item to insert.
     * @return the element's key, usefull when the key is auto-generated.
     */
    K insert(T item);

    /**
     * Deletes the item of the specific key from the resource.
     *
     * @param key key of the element to delete.
     */
    void delete(K key);

    /**
     * Update an element of the resource. The search of the element is based on
     * its key.
     *
     * @param item item to update.
     */
    void update(T item);

    /**
     * Returns all the elements of the resource. This method can be long.
     *
     * @return all the elements of the resource.
     */
    List<T> selectAll();

    /**
     * Returns an element based on its key.
     *
     * @param key key of the element to select.
     * @return an element based on its key.
     */
    T select(K key);
}
