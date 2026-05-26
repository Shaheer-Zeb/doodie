package datastructures;

/**
 *
 * @author Shaheer
 */
public class Stack<T>
{
    private int top = 0;
    private int size = 100;
    private static final int DEFAULTSIZE = 100;
    private T[] arr;
    
    public Stack (int size)
    {
        if (size > 0)
            this.size = size;
        else
        {
            this.size = DEFAULTSIZE;
            System.out.println("The size has to be positive. The default size of " + DEFAULTSIZE + " is assigned to the Stack.");
        }
        arr = (T[]) new Object[size];
    }
    public Stack ()
    {
        this(DEFAULTSIZE);
    }
    /**
     * Pushes the object at the top of the Stack
     * @param object 
     */
    public void push(T object)
    {
        if (top < size)
            arr[top++] = object;
        else
            System.out.println("Stack Overflow.");
    }
    /**
     * Returns and effectively removes the Object at the top of the Stack
     * @return Stack type
     */
    public T pop()
    {
        return arr[--top];
    }
    /**
     * Only returns the Object at the top of the Stack
     * @return 
     */
    public T peek()
    {
        return arr[top];
    }
    /**
     * Returns the name of the Class, objects of which the Stack stores
     * @return String
     */
    public String getStackType()
    {
        return arr[0].getClass().getName();
    }
}
