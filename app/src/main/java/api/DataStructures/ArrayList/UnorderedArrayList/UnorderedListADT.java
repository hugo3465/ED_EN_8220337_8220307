package api.dataStructures.ArrayList.UnorderedArrayList;

import api.dataStructures.ArrayList.ListADT;
import api.dataStructures.Exceptions.ElementNotFoundException;

public interface UnorderedListADT<T> extends ListADT<T> {
    /**
     * Adds the specified element to the front of the list.
     *
     * @param element the element to be added to the front of the list
     */
    public void addToFront(T element);

    /**
     * Adds the specified element to the rear of the list.
     *
     * @param element the element to be added to the rear of the list
     */
    public void addToRear(T element);

    /**
     * Adds the specified element after the specified target element.
     *
     * @param element the element to be added
     * @param target  the target element after which the new element is added
     * @throws ElementNotFoundException if the target element is not found in the
     *                                  list
     */
    public void addAfter(T element, T target);
}
