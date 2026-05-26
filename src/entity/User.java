package entity;

import java.io.Serializable;
import managers.UserManager;

/**
 *
 * @author ShaheerZK
 */
public class User implements Serializable
{
    private String name;
    private String email;
    private String password;
    private String address;
    private Cart cart;
    private static String savedUsersPath = "src/record/users.ser";
    private static final long serialVersionUID = 1;
    public User(String name, String email, String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        cart = new Cart();
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getName()
    {
        return name;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPassword()
    {
        return password;
    }
    public Cart getCart()
    {
        return cart;
    }
    public String getAddress()
    {
        return address;
    }
    public void addItemToCart(Item item)
    {
        cart.addItem(item);
        UserManager.removeUser(this);
        UserManager.addUser(this);
    }
    public void removeItemFromCart(Item item)
    {
        cart.removeItem(item);
        UserManager.updateUsers();
    }
}
