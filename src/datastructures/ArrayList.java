package datastructures;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author ShaheerZK
 */
public class ArrayList<T> implements Iterable<T>, Serializable
{
    private int size;
    private int capacity;
    private int iteratorCurrentIndex = 0;
    private final int growingFactor = 2;
    private static final int DEFAULTSIZE = 100;
    private T[] arr;
    
    private class ArrayListIterator implements Iterator<T>
    {
        @Override
        public boolean hasNext() 
        {
            return iteratorCurrentIndex < size;
        }
        @Override
        public T next() 
        {
            if (!hasNext())
                throw new NoSuchElementException();
            return arr[iteratorCurrentIndex++];
        }
    }
    
    public ArrayList(int capacity)
    {
        if (capacity > 0)
            this.capacity = capacity;
        else
        {
            System.out.println("The capacity should be positive. Default capacity of " + DEFAULTSIZE + " assigned to the ArrayList.");
            this.capacity = DEFAULTSIZE;
        }
        size = 0;
        arr = (T[]) new Object[this.capacity];
    }
    public ArrayList()
    {
        this(DEFAULTSIZE);
    }
    /**
     * Appends the object at the end of the list
     * @param obj 
     */
    public void add(T obj)
    {
        if (size < capacity)
            arr[size++] = obj;
        else
        {
            ensureCapacity(capacity * growingFactor);
            arr[size++] = obj;
        }
    }
    /**
     * Adds the element to the start of the ArrayList.
     * @param obj 
     */
    public void addStart(T obj)
    {
        if (size > capacity)
            ensureCapacity(capacity * growingFactor);
        shiftArrayRightByOne();
        arr[0] = obj;
        size++;
    }
    /**
     * Replaces the object at the provided index.
     * @param index
     * @param obj 
     */
    public void replace(int index, T obj)
    {
        if (index >= 0 && index <= size)
            arr[index] = obj;
        else
            System.out.println("Invalid index.");
    }
    /**
     * Effectively clears the ArrayList.
     */
    public void clear()
    {
        for (int i = 0; i < size; i++)
        {
            arr[i] = null;
        }
        size = 0;
    }
    /**
     * Returns whether the ArrayList contains the specified element.
     * @param obj
     * @return 
     */
    public boolean contains(T obj)
    {
        
        boolean isFound = false;
        for (int i = 0; i < size; i++)
        {
            if (obj != null && arr[i].equals(obj))
            {
                isFound = true;
                break;
            }
            else if (obj == null && arr[i] == null)
            {
                isFound = true;
                break;
            }
        }
        return isFound;
    }
    /**
     * Ensures that the capacity is at least minimumCapacity, else, it'll increase capacity to the provided minimumCapacity.
     * @param minimumCapacity 
     */
    public void ensureCapacity(int minimumCapacity)
    {
        if (minimumCapacity > capacity)
        {
            capacity = minimumCapacity;
            arr = copyArrayList();
        }
    }
    private T[] copyArrayList()
    {
        T[] resizedArr = (T[]) new Object[capacity];
        System.arraycopy(arr, 0, resizedArr, 0, size);
        return resizedArr;
    }
    /**
     * Returns the object at the provided index of the ArrayList, returns null if the index is invalid.
     * @param index
     * @return 
     */
    public T get(int index)
    {
        if (index >= 0 && index < size)
            return arr[index];
        return null;
    }
    /**
     * Returns the index of the first matching object, if none is found, it'll return -1.
     * @param obj
     * @return 
     */
    public int indexOf(T obj)
    {
        for (int i = 0; i < size; i++)
        {
            if (obj != null && arr[i].equals(obj))
                return i;
            else if (obj == null && arr[i] == null)
                return i;
        }
        return -1;
    }
    /**
     * Returns whether the ArrayList is empty.
     * @return 
     */
    public boolean isEmpty()
    {
        return size == 0;
    }
    /**
     * Returns the index of the last matching object, if none is found, it'll return -1.
     * @param obj
     * @return 
     */
    public int lastIndexOf(T obj)
    {
        for (int i = size - 1; i >= 0; i--)
        {
            if (obj != null && arr[i].equals(obj))
                return i;
            else if (obj == null && arr[i] == null)
                return i;
        }
        return -1;
    }
    /**
     * Effectively removes and returns the object from the specified index, returns null if the index isn't valid.
     * @param index
     * @return 
     */
    public T remove(int index)
    {
        if (index < 0 || index > size - 1)
            return null;
        
        T obj = arr[index];
        
        int numMoved = size - index - 1;
        if (numMoved > 0)
            shiftArray(index, numMoved);
        arr[--size] = null;
        
        return obj;
    }
    /**
     * Removes the specified object from the first found index.
     * @param obj 
     */
    public void remove(T obj)
    {
        remove(indexOf(obj));
    }
    /**
     * Removes the last occurrence of the specified object.
     * @param obj 
     */
    public void removeLast(T obj)
    {
        remove(lastIndexOf(obj));
    }
    private void shiftArray(int index, int numMoved)
    {
        System.arraycopy(arr, index + 1, arr, index, numMoved);
    }
    private void shiftArrayRightByOne()
    {
        ensureCapacity(capacity + 1);
        System.arraycopy(arr, 0, arr, 1, size);
    }
    /**
     * Returns the size of the ArrayList.
     * @return 
     */
    public int size()
    {
        return size;
    }
    /**
     * Returns a copy of the underlying array
     * @return 
     */
    public T[] toArray()
    {
        return Arrays.copyOf(arr, size);
    }
    /**
     * Makes the capacity of the ArrayList equal the size.
     */
    public void trimToSize()
    {
        capacity = size;
        arr = copyArrayList();
    }
    /**
     * Returns the name of the current Class, objects of which the ArrayList stores
     * @return 
     */
    public String getArrayListType()
    {
        return arr[0].getClass().getName();
    }

    @Override
    public Iterator<T> iterator() 
    {
        return new ArrayListIterator();
    }
}