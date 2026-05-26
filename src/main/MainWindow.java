package main;

import authorize.*;
import javax.swing.*;
import com.formdev.flatlaf.*;
import java.awt.*;
import managers.FontManager;
import managers.ItemManager;
import managers.UserManager;

/**
 * @author ShaheerZK
 */
public class MainWindow
{
    private static JFrame frame;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;
    public static void main(String[] args) 
    {
        FlatDarculaLaf.setup();
        FontManager.setGlobalFont(FontManager.getOutfit().deriveFont(24.0f));
        initFrame();
        frame.setVisible(true);
    }
    private static void initFrame()
    {
        frame = new JFrame("Doodie");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        
        frame.add(new AuthorizeCard());
    }
}
