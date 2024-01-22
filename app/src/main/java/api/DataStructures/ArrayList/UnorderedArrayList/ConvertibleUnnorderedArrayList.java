package api.dataStructures.ArrayList.UnorderedArrayList;

public class ConvertibleUnnorderedArrayList<T> extends UnorderedArrayList<T> {
    public ConvertibleUnnorderedArrayList() {
        super();
    }

    /**
     * Converte a UnnorderedArryList para um array normal
     * @return array normal com os items da arrayList
     */
    public T[] toArray(Class<T> elementType) {
        // Cria um novo array do mesmo tipo que os elementos da lista
        T[] array = (T[]) java.lang.reflect.Array.newInstance(elementType, size());

        // Copia os elementos da lista para o array
        for (int i = 0; i < size(); i++) {
            array[i] = this.items[i];
        }

        return array;
    }

}
