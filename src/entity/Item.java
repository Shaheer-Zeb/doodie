package entity;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author ShaheerZK
 */
public class Item implements Serializable
{
    private String name;
    private double price;
    private int quantity;
    private ImageIcon image;
    private int id;
    private static int itemNumber = 0;
    private static final long serialVersionUID = 1;
    public Item (String name, double price, int quantity, ImageIcon image)
    {
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setImage(image);
        id = ++itemNumber;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setPrice(double price)
    {
        if (price < 0)
            try 
            {
                throw new Exception("The item price can't be negative.");
            } 
            catch (Exception ex) 
            {
                System.getLogger(Item.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        this.price = price;
    }
    public void setQuantity(int quantity)
    {
        if (quantity < 1)
            try 
            {
                throw new Exception("The item quantity can't be less than 1.");
            } 
            catch (Exception ex) 
            {
                System.getLogger(Item.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        this.quantity = quantity;
    }
    public void setImage(ImageIcon image)
    {
        this.image = image;
    }
    public String getName()
    {
        return name;
    }
    public double getPrice()
    {
        return price;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public ImageIcon getImage()
    {
        return image;
    }
    public int getId()
    {
        return id;
    }
}
