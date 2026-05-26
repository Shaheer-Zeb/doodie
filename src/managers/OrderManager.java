package managers;

import datastructures.ArrayList;
import entity.Item;
import entity.Order;
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
public class OrderManager 
{
    private static ArrayList<Order> orders = readOrders();
    private static final String savedOrdersPath = "src/record/orders.ser";
    private static ArrayList<Order> readOrders()
    {
        ArrayList<Order> orders = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedOrdersPath)))
        {
            while (true)
            {
                try 
                {
                    orders = (ArrayList<Order>)ois.readObject();
                }
                catch (EOFException e)
                {
                    break;
                }
            }
        } 
        catch (FileNotFoundException ex) 
        {
            System.getLogger(OrderManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } 
        catch (IOException | ClassNotFoundException ex) 
        {
            System.getLogger(OrderManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return orders;
    }
    private static void writeOrders()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savedOrdersPath)))
        {
            oos.writeObject(orders);
        } 
        catch (FileNotFoundException ex) 
        {
            System.getLogger(OrderManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } 
        catch (IOException ex) 
        {
            System.getLogger(OrderManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    public static void updateOrders()
    {
        writeOrders();
    }
    public static void placeOrder(Order order)
    {
        orders.add(order);
        writeOrders();
    }
    public static void placeOrders(ArrayList<Order> sentOrders)
    {
        for (int i = 0; i < sentOrders.size(); i++)
        {
            orders.add(sentOrders.get(i));
        }
        writeOrders();
    }
    public static ArrayList<Order> getOrders()
    {
        return orders;
    }
    public static Order getOrderByItem(Item item)
    {
        for (int i = 0; i < orders.size(); i++)
        {
            Order order = orders.get(i);
            if (order.getItem() == item)
                return order;
        }
        return null;
    }
    
}
