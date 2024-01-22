package api.dataStructures.Queue.LinkedQueue;
public class LinearNode<T> {
    private T element;
    private LinearNode<T> next;
    
    public LinearNode(T element, LinearNode<T> next) {
        this.element = element;
        this.next = next;
    }

    public LinearNode(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public LinearNode<T> getNext() {
        return next;
    }

    public void setNext(LinearNode<T> next) {
        this.next = next;
    }

}