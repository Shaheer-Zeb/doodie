package managers;

import datastructures.ArrayList;
import entity.User;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

 /**
  * @author ShaheerZK
  */
public class UserManager
{
    private static ArrayList<User> users = readUsers();
    private static final String savedUserPath = "src/record/users.ser";
    private UserManager(){}
    
    public static void addUser(User user)
    {
        users.add(user);
        writeUsers();
    }
    public static ArrayList<User> getUsers()
    {
        return users;
    }
    private static void writeUsers()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savedUserPath)))
        {
            for (int i = 0; i < users.size(); i++)
            {
                oos.writeObject(users.get(i));
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
    private static ArrayList<User> readUsers()
    {
        ArrayList<User> users = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedUserPath)))
        {
            while (true)
            {
                try 
                {
                    User user = (User)ois.readObject();
                    users.add(user);
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
        return users;
    }
    public static User getUserByName(String name)
    {
        for (int i = 0; i < users.size(); i++)
        {
            User user = users.get(i);
            if (user.getName().equalsIgnoreCase(name))
                return user;
        }
        return null;
    }
    public static User getUserByEmail(String email)
    {
        for (int i = 0; i < users.size(); i++)
        {
            User user = users.get(i);
            if (user.getEmail().equalsIgnoreCase(email))
                return user;
        }
        return null;
    }
    public static void clearUsers()
    {
        users.clear();
        writeUsers();
    }
    public static void removeUser(User user)
    {
        users.remove(user);
        writeUsers();
    }
    public static void updateUsers()
    {
        writeUsers();
    }
}