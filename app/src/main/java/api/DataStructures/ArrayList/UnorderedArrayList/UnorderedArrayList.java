package api.DataStructures.ArrayList.UnorderedArrayList;

import api.DataStructures.ArrayList.ArrayList;
import api.DataStructures.Exceptions.ElementNotFoundException;

public class UnorderedArrayList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    public UnorderedArrayList() {
        super();
    }

    @Override
    public void addToFront(T element) {
        if (rear == items.length) {
            expandCapacity();
        }

        // passar todos os elementos uma casa para a frente, para haver espaço para
        // adicionar a trás
        for(int i = rear; i > 0; i--) {
            items[i] = items[i - 1];
        }

        items[0] = element;

        rear++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        if (rear == items.length) {
            expandCapacity();
        }

        items[rear] = element;

        rear++;
        modCount++;
    }

    @Override
    public void addAfter(T element, T target) {
        if (rear == items.length) {
            expandCapacity();
        }

        int targetIndex = find(target);
        if(targetIndex == ELEMENT_NOT_FOUND) {
            throw new ElementNotFoundException();
        }

        // passar todos os elementos uma casa para a frente, para haver espaço para
        // adicionar a trás do target
        for(int i = rear; i > targetIndex; i--) {
            items[i] = items[i - 1];
        }

        items[targetIndex + 1] = element;

        rear++;
        modCount++;
    }
}
