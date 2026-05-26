package datastructures;

/**
 * @author ShaheerZK
 */
public class ArrayDeque<T>
{
    private int size;
    private final int TOP = 0;
    
    private final ArrayList<T> deque;
    
    ArrayDeque()
    {
        deque = new ArrayList<>();
    }
    /**
     * Inserts the specified object at the beginning of the deque.
     * @param obj 
     */
    public void insertStart(T obj)
    {
        deque.addStart(obj);
        size++;
    }
    /**
     * Inserts at the end.
     * @param obj 
     */
    public void insertEnd(T obj)
    {
        deque.add(obj);
        size++;
    }
    /**
     * Returns but don't remove the starting object.
     * @return 
     */
    public T getStart()
    {
        return deque.get(TOP);
    }
    /**
     * Returns but doesn't remove the last object.
     * @return 
     */
    public T getEnd()
    {
        return deque.get(size - 1);
    }
    /**
     * Removes and returns the first object on the deque.
     * @return 
     */
    public T removeStart()
    {
        size--;
        return deque.remove(TOP);
    }
    /**
     * Removes and returns the last element on the deque.
     * @return 
     */
    public T removeEnd()
    {
        size--;
        return deque.remove(size - 1);
    }
    /**
     * Removes the first occurrence of the specified object.
     * @param obj 
     */
    public void remove(T obj)
    {
        deque.remove(obj);
        size--;
    }
    public void removeLast(T obj)
    {
        deque.removeLast(obj);
    }
    /**
     * Clears the whole deque.
     */
    public void clear()
    {
        deque.clear();
        size = 0;
    }
    /**
     * Returns whether the deque contains the specified object.
     * @param obj
     * @return 
     */
    public boolean contains(T obj)
    {
        return deque.contains(obj);
    }
    /**
     * Returns whether the deque is empty.
     * @return 
     */
    public boolean isEmpty()
    {
        return deque.isEmpty();
    }
    /**
     * Returns the number of elements currently in the list.
     * @return 
     */
    public int size()
    {
        return size;
    }
    /**
     * Returns the underlying array of the underlying ArrayList of the deque.
     * @return 
     */
    public T[] toArray()
    {
        return deque.toArray();
    }
}