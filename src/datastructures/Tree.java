package datastructures;

/**
 * @author ShaheerZK
 */
public class Tree<T>
{
    private class Node<T>
    {
        T value;
        ArrayList<Node> children;
        Node(T value)
        {
            this.value = value;
            children = new ArrayList<>();
        }
    }
}