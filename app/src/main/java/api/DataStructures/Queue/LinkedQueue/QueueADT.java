package api.dataStructures.Queue.LinkedQueue;
import api.dataStructures.Exceptions.EmptyCollectionException;

public interface QueueADT<T> {
    /**
     * Adds one element to the rear of this queue.
     * 
     * @param element the element to be added to
     *                the rear of this queue
     */
    void enqueue(T element);

    /**
     * Removes and returns the element at the front of
     * this queue.
     * 
     * @return the element at the front of this queue
     */
    T dequeue() throws EmptyCollectionException;

    /**
     * Returns without removing the element at the front of
     * this queue.
     * 
     * @return the first element in this queue
     */
    T first();

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue is empty
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this queue.
     *
     * @return the integer representation of the size
     *         of this queue
     */
    int size();

    /**
     * Returns a string representation of this queue.
     * 
     * @return the string representation of this queue
     */
    String toString();
}