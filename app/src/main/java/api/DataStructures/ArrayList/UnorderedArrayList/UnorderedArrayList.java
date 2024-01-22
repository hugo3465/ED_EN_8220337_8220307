package api.dataStructures.ArrayList.UnorderedArrayList;

import api.dataStructures.ArrayList.ArrayList;
import api.dataStructures.Exceptions.ElementNotFoundException;

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

    /**
     * Remove e retorna o elemento na posição especificada.
     * 
     * @param index a posição do elemento a ser removido
     * @return o elemento removido
     * @throws ElementNotFoundException se a posição não contiver um elemento
     */
    public T removeIndex(int index) throws ElementNotFoundException {
        if (index < 0 || index >= rear) {
            throw new ElementNotFoundException("Index out of bounds: " + index);
        }

        T removedElement = items[index];

        // Desloca os elementos após a posição especificada uma posição para frente
        for (int i = index; i < rear - 1; i++) {
            items[i] = items[i + 1];
        }

        items[rear - 1] = null; // Remove a referência ao último elemento duplicado
        rear--;
        modCount++;

        return removedElement;
    }
}
