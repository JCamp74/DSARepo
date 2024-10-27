/*
Data Structures Lecture 9


    Open up IDE (Hey this is done!)
    Write a new program named "MyNameDaisyChainConnector"


        MNDCC will have some global variables
            Front/head, it'll be of type Box.
        Box class, and boxes hold data
            Has a String, int, etc.
            Has a global variable next.
            Next is a box.
            Front/head, it'll be of type Box.

        Your DCC Will need methods
            AddFront
            AddEnd
            AddatIndex

            RemoveFront
            RemoveEnd
            RemoveAtIndex

            Replace
            Clear
            Size
            toString()

            get(index) (typically a big NOOOOO)
            contains(element)
 */

/** Implementation of a Circular, Doubly-Linked List.
 *  This list is the mainframe that will allow the game, DungeonFest
 *  to run properly and ensure room travel is consistent across the entire game.
 *  This list will handle the room elements, the entities will be handled in ArrayList
 * @param <E> Generic Element for the linked list.
 * @author Jackson Campbell
 * @version 1.0.0
 */
public class JacksonDaisyChainConnector<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Nested Node class needed for the list.
     * This class is used only inside the list methods,
     * and is not accessed anywhere else.
     * @param <E> Data type.
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E dataItem){
            data = dataItem;
            next = null;
            prev = null;
        }

        public Node(E dataItem, Node<E> nodeRef, Node<E> prevNodeRef){
            data = dataItem;
            next = nodeRef;
            prev = prevNodeRef;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public E getData() {
            return data;
        }

        public void setData(E userInp) {
            data = userInp;
        }
    }

    /**
     * Method to add to the very front
     * of the current list.
     * @param item The given data to add.
     */
    public void addFirst(E item) {
        Node<E> temp = new Node<>(item, head, null);
        if (head != null) {
            head.prev = temp;
        }
        head = temp;
        if(tail == null) {
            tail = temp;
        }
        size++;
    }

    /**
     * Generic add method that references a node.
     * (Unused, written from the textbook and
     * contained elements added to it once doubly-linked.)
     * @param node The given node to add.
     * @param item The given data to add.
     */
    public void add(Node<E> node, E item) {
        if(node == null) {
            throw new IllegalArgumentException("Node cannot be null!");
        }
        Node<E> temp = new Node<>(item, node.next, node);
        if (node.next != null) {
            node.next.prev = temp;
        }
        node.next = temp;
        size++;
    }

    /**
     * Boolean add method that adds onto the end of the list.
     * Uses addAtIndex to add to the list, and will always take size as the index.
     * Returns true, as the data has now been added to the list.
     * @param item The data to add into the new node.
     * @return The truth value as the node was successfully added.
     */
    public boolean add(E item) {
        addAtIndex(size, item);
        return true;
    }

    /**
     * Method to add to the very end of the current list.
     * Iterates through the list to get the ending node,
     * and creates a new node to replace the current tail.
     * @param item The given data to add.
     */
    public void addLast(E item) {
        Node<E> temp = new Node<>(item, null, tail);
        if (head == null) {
            head = temp;
        }
        if(tail != null) {
            tail.next = temp;
        }
        tail = temp;
        size++;
    }

    /**
     * Method to add a node at the specified index. Uses both addFirst and addLast
     * in the event that the node is added at index 0, or at the index of the list's size.
     * Throws an Out-Of-Bounds Exception when the given indexing is not in the list index.
     * @param index The specified index to add at.
     * @param item The data added in the new node.
     */
    public void addAtIndex(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0) {
            addFirst(item);
        } else if(index == size) {
            addLast(item);
        } else {
            Node<E> node = getNode(index - 1);
            Node<E> temp = new Node<>(item, node.next, node);
            node.next.prev = temp;
            node.next = temp;
            size++;
        }
    }
    /**
     * Method to remove a node after the specified one.
     * Helper method for removeAtIndex, and calls the
     * previous node for deletion once the temporary link is set.
     * @param node The given node for deletion.
     * @return The data inside the node.
     */
    public E removeAfter(Node<E> node) {
        Node<E> temp = node.next;
        if (temp != null) {
            node.next = temp.next;
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    /**
     * Method to remove the very first node in the list.
     * Creates a temporary node to become the new head,
     * and uses the data of the current head's next value.
     * @return The temporary node's data.
     */
    public E removeFirst() {
        Node<E> temp = head;
        if (head != null) {
            head = head.next;
            head.prev = null;
        } else {
            tail = null;
        }
        if (temp != null) {
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    /**
     * Method to remove a specified node at the given index.
     * Uses helper methods removeFirst and removeAfter when given
     * the specified index, which in turn is used to grab the specified node.
     * @param index The index of the node for deletion.
     * @return The method used for deletion.
     */
    public E removeAtIndex(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if(index == 0) {
            return removeFirst();
        }
        Node<E> prevNode = getNode(index - 1);
        return removeAfter(prevNode);
    }

    /**
     * Method to remove the last node in the list.
     * Uses a temporary node to create a new reference point with
     * the current tail's previous data. Sets the head to null if
     * the tail is also null.
     * @return The data within the temporary node.
     */
    public E removeLast() {
        Node<E> temp = tail;
        if(tail == null) {
            throw new IllegalStateException("The list is empty!");
        }
        tail = tail.prev;
        if(tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return temp.data;
    }

    /**
     * Node method to return a specified node.
     * Throws an Out-Of-Bounds exception if the given index
     * is currently out of bounds. Creates a new node, and loops
     * through the list to find the node to return.
     * @param index The given node index to return.
     * @return The specified node at the index.
     */
    private Node<E> getNode(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * Getter method to return data inside a given node.
     * Uses the getter method inside the node class to retrieve the node.
     * Throws an Out-Of-Bounds Exception when the given indexing is not in the list index.
     * @param index The specified index to return the data.
     * @return The data inside the given node.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        return node.data;
    }

    /**
     * Setter method to set a specific node's data to the new value.
     * Uses the getNode method in the node class to retrieve the given node to alter.
     * Throws an Out-Of-Bounds Exception when the given indexing is not in the list index.
     * @param index The given index of the node.
     * @param newValue The new data to replace in the node.
     * @return The new data inside the node.
     */
    public E set(int index, E newValue) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        E result = node.data;
        node.data = newValue;
        return result;
    }


    /**
     * Method to clear the entire list.
     * Sets both head and tail to null and size to 0, creating a break in the list.
     * Allows for garbage collection to reclaim the memory from the other nodes.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Integer method to return the size of the list.
     * @return The size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Boolean method to check if the list contains a certain data item.
     * Loops through the list to check for a true equals value,
     * otherwise will return false.
     * @param item The data to be checked.
     * @return The truth value based on if the data was found.
     */
    public boolean contains(E item) {
        Node<E> curr = head;
        while(curr != null) {
            if(curr.getData().equals(item)) {
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }


    /**
     * Method to replace a node's data entirely, instead of simply setting the data.
     * First checks if the list contains the element, and then loops through to find the
     * required data for replacement.
     * @param oldItem The data looking to be replaced.
     * @param newItem The new data implemented in the node.
     * @return The truth value based on if the node was changed.
     */
    public boolean replace(E oldItem, E newItem) {
        if(!contains(oldItem)) {
            return false;
        }
        Node<E> temp = head;
        while(temp != null) {
            if(temp.getData().equals(oldItem)) {
                temp.setData(newItem);
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    /**
     * Method to make the list circular.
     * Links the head's previous node to the tail, and the tail's next node to the head.
     * Will create this link so long as tail is not null.
     */
    public void circulate() {
        if(tail != null) {
            tail.next = head;
            head.prev = tail;
        }
    }

    /**
     * Override of the java.lang.toString method.
     * Will return each element inside the list, so long as
     * the next value is not equal to the head node.
     * @return The concatenated string.
     */
    @Override
    public String toString() {
        Node<E> next = this.head;
        String result = "";

        do {
            result += next.data;
            next = next.next;
            if (next != null) {
                result += " ";
            }
        }while(next != head);

        return result;
    }
}
