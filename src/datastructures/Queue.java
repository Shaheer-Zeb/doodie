package datastructures;

/**
 * @author ShaheerZK
 */
public class Queue<T>
{
    private static final int DEFAULTSIZE = 100;
    private final int TOP = 0;
    private int capacity;
    private ArrayList<T> arrayList;
    
    /**
     * Takes the initial capacity for the Queue.
     * @param capacity 
     */
    public Queue(int capacity)
    {
        if (capacity > 0)
            this.capacity = capacity;
        else
        {
            System.out.println("The capacity has to be positive. Default capacity of " + DEFAULTSIZE + " is assigned.");
            this.capacity = DEFAULTSIZE;
        }
        arrayList = new ArrayList<>(this.capacity);
    }
    /**
     * Assigns the default initial capacity of DEFAULTCAPACITY for the Queue.
     */
    public Queue()
    {
        this(DEFAULTSIZE);
    }
    /**
     * Add the object to the end of the queue.
     * @param obj 
     */
    public void enqueue(T obj)
    {
        arrayList.add(obj);
    }
    /**
     * Removes and returns the element at the start of the queue
     * @return 
     */
    public T dequeue()
    {
        return arrayList.remove(TOP);
    }
    /**
     * Merely returns the element at the start of the queue.
     * @return 
     */
    public T peek()
    {
        return arrayList.get(TOP);
    }
    /**
     * Returns the size (current number of elements) of the queue.
     * @return 
     */
    public int getSize()
    {
        return arrayList.size();
    }
    /**
     * Returns whether the queue is empty.
     * @return 
     */
    public boolean isEmpty()
    {
        return arrayList.isEmpty();
    }
    /**
     * Returns whether the queue contains the specified object.
     * @param obj
     * @return 
     */
    public boolean contains(T obj)
    {
        return arrayList.contains(obj);
    }
    /**
     * Clears the queue.
     */
    public void clear()
    {
        arrayList.clear();
    }
}