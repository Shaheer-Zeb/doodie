package entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ShaheerZK
 */
public class Order implements Serializable
{
    private Date date;
    private User user;
    private Item item;
    private String address;
    private boolean isFulfilled;
    private static final long serialVersionUID = 1;
    
    public Order(Item item, User user, String address)
    {
        this.item = item;
        this.user = user;
        this.address = address;
        date = new Date();
    }
    public void setIsFulfilled(boolean b)
    {
        isFulfilled = b;
    }
    public boolean getIsFulfilled()
    {
        return isFulfilled;
    }
    public Item getItem()
    {
        return item;
    }
    public User getUser()
    {
        return user;
    }
    public String getAddress()
    {
        return address;
    }
}
