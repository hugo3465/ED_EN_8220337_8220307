package api.dataStructures.Tree.PriorityQueue;
import java.util.Iterator;

import api.dataStructures.ArrayList.UnorderedArrayList.UnorderedArrayList;
import api.dataStructures.Exceptions.ElementNotFoundException;
import api.dataStructures.Queue.LinkedQueue.LinkedQueue;
import api.dataStructures.Tree.BinaryTreeADT;

public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    protected int count;
    protected T[] tree;
    private final int CAPACITY = 50;

    /**
     * Creates an empty binary tree.
     */
    public ArrayBinaryTree() {
        count = 0;
        tree = (T[]) new Object[CAPACITY];
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element which will become the root
     *                of the new tree
     */
    public ArrayBinaryTree(T element) {
        count = 1;
        tree = (T[]) new Object[CAPACITY];
        tree[0] = element;
    }

    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree. Throws a NoSuchElementException if
     * the specified target element is not found in the binary tree.
     *
     * @param targetElement the element being sought in the tree
     * @return true if the element is in the tree
     * @throws ElementNotFoundException if an element not found
     *                                  exception occurs
     */
    public T find(T targetElement) throws ElementNotFoundException {
        T temp = null;
        boolean found = false;

        for (int ct = 0; ct < count && !found; ct++)
            if (targetElement.equals(tree[ct])) {
                found = true;
                temp = tree[ct];
            }

        if (!found)
            throw new ElementNotFoundException("binary tree");

        return temp;
    }

    //TODO: DUVIDO QUE ESTEJA BEM
    @Override
    public T getRoot() {
        if (isEmpty()) {
            return null;
        }

        return tree[0];
    }

    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    @Override
    public int size() {
        return count;
    }

    //TODO: duvido que esteja bem
    @Override
    public boolean contains(T targetElement) {
        try {
            find(targetElement);
            return true;
        } catch (ElementNotFoundException e) {
            return false;
        }
    }

    /**
     * Performs an inorder traversal on this binary tree by
     * calling an overloaded, recursive inorder method
     * that starts with the root.
     *
     * @return an iterator over the binary tree
     */
    public Iterator<T> iteratorInOrder() {
        UnorderedArrayList<T> templist = new UnorderedArrayList<>();
        inorder(0, templist);

        return templist.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node     the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void inorder(int node, UnorderedArrayList<T> templist) {
        if (node < tree.length)
            if (tree[node] != null) {
                inorder(node * 2 + 1, templist);
                templist.addToRear(tree[node]);
                inorder((node + 1) * 2, templist);
            }
    }

    /**
     * Performs an preorder traversal on this binary tree by
     * calling an overloaded, recursive preorder method
     * that starts with the root.
     *
     * @return an iterator over the binary tree
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        UnorderedArrayList<T> templist = new UnorderedArrayList<>();
        preorder(0, templist);

        return templist.iterator();
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node     the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void preorder(int node, UnorderedArrayList<T> templist) {
        if (node < tree.length)
            if (tree[node] != null) {
                preorder(node * 2 + 1, templist); // right
                templist.addToRear(tree[node]);
                preorder((node + 1) * 2, templist); // left
            }
    }

    /**
     * Performs an postorder traversal on this binary tree by
     * calling an overloaded, recursive postorder method
     * that starts with the root.
     *
     * @return an iterator over the binary tree
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        UnorderedArrayList<T> templist = new UnorderedArrayList<>();
        postorder(0, templist);

        return templist.iterator();
    }

    /**
     * Performs a recursive postorder traversal.
     *
     * @param node     the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void postorder(int node, UnorderedArrayList<T> templist) {
        if (node < tree.length)
            if (tree[node] != null) {
                postorder(node * 2 + 1, templist); // right
                templist.addToRear(tree[node]);
                postorder((node + 1) * 2, templist); // left
            }
    }

    /**
     * Performs an levelOrder traversal on this binary tree by
     * calling an overloaded.
     *
     * @return an iterator over the binary tree
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {
        UnorderedArrayList<T> tempList = new UnorderedArrayList<>();
        levelorder(0, tempList);

        return tempList.iterator();
    }

    protected void levelorder(int node, UnorderedArrayList<T> tempList) {
        LinkedQueue<Integer> nodes = new LinkedQueue<>(); // guarda nodes
    
        if (node >= tree.length || tree[node] == null) {
            return; // Don't process null nodes or nodes outside the array bounds
        }
    
        nodes.enqueue(node);
    
        while (!nodes.isEmpty()) {
            Integer current = nodes.dequeue();
            tempList.addToRear(tree[current]); // Process the current node
    
            int leftChild = current * 2 + 1;
            int rightChild = (current + 1) * 2;
    
            // Check if left child is within bounds and not null
            if (leftChild < tree.length && tree[leftChild] != null) {
                nodes.enqueue(leftChild);
            }
    
            // Check if right child is within bounds and not null
            if (rightChild < tree.length && tree[rightChild] != null) {
                nodes.enqueue(rightChild);
            }
        }
    }
}