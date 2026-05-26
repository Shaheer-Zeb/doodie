package entity;

import datastructures.ArrayList;
import java.io.Serializable;

/**
 *
 * @author ShaheerZK
 */
public class Cart implements Serializable
{
    private ArrayList<Item> items;
    private static final long serialVersionUID = 1L;
    Cart()
    {
        items = new ArrayList<>();
    }
    public ArrayList<Item> getItems()
    {
        return items;
    }
    public void addItem(Item item)
    {
        items.add(item);
    }
    public double getGrandTotal()
    {
        double grandTotal = 0.0;
        for (int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            grandTotal += item.getPrice();
        }
        return grandTotal;
    }
    public void removeItem(Item item)
    {
        items.remove(item);
    }
}
