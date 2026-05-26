package managers;

import datastructures.ArrayList;
import entity.Item;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author ShaheerZK
 */
public class ItemManager 
{
    private static ArrayList<Item> items = readItems();
    private static final String savedItemsPath = "src/record/items.ser";
    private static int currentItem = 0;
    private ItemManager() {}
    
    public static void addItem(Item item)
    {
        items.add(item);
        writeItems();
    }
    public static ArrayList<Item> getItems()
    {
        return items;
    }
    public static Item getItemByName(String name)
    {
        for (int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            if (item.getName().equalsIgnoreCase(name))
                return item;
        }
        return null;
    }
    public static Item getItemByIndex(int index)
    {
        return items.get(index);
    }
    public static int getItemCount()
    {
        return items.size();
    }
    public static Item nextItem()
    {
        return items.get(currentItem++);
    }
    public static void clearItems()
    {
        items.clear();
        writeItems();
    }
    public static ArrayList<Item> getItemsBelowPrice(double price)
    {
        ArrayList<Item> filteredItems = new ArrayList<>();
        for (int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            if (item.getPrice() <= price)
                filteredItems.add(item);
        }
        return filteredItems;
    }
    private static ArrayList<Item> readItems()
    {
        ArrayList<Item> items = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedItemsPath)))
        {
            while (true)
            {
                try 
                {
                    Item item = (Item)ois.readObject();
                    items.add(item);
                }
                catch (EOFException e)
                {
                    break;
                } 
                catch (ClassNotFoundException ex) 
                {
                    System.getLogger(UserManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        } 
        catch (IOException ex) 
        {
            System.getLogger(UserManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return items;
    }
    private static void writeItems()
    {
         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savedItemsPath)))
        {
            for (int i = 0; i < items.size(); i++)
            {
                oos.writeObject(items.get(i));
            }
        } 
        catch (FileNotFoundException ex) 
        {
            System.getLogger(UserManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } 
        catch (IOException ex) 
        {
            System.getLogger(UserManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    public static void updateItems()
    {
        writeItems();
    }
}
