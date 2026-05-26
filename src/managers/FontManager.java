package managers;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import javax.swing.plaf.FontUIResource;
/**
 *
 * @author ShaheerZK
 */
public class FontManager 
{
    public static void setGlobalFont(Font font) 
    {
        FontUIResource fontResource = new FontUIResource(font);

        Enumeration<Object> keys = UIManager.getDefaults().keys();

        while (keys.hasMoreElements()) 
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);

            if (value instanceof FontUIResource) 
            {
                UIManager.put(key, fontResource);
            }
        }
    }
    public static Font getGoogleSans()
    {
        try 
        {
            return Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/GoogleSans-Medium.ttf"));
        } 
        catch (FontFormatException | IOException ex) 
        {
            System.getLogger(FontManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return new Font(Font.SANS_SERIF, Font.PLAIN, 16);
    }
    public static Font getOutfit()
    {
        try 
        {
            return Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Outfit-Medium.ttf"));
        } 
        catch (FontFormatException | IOException ex) 
        {
            System.getLogger(FontManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return new Font(Font.SANS_SERIF, Font.PLAIN, 16);
    }
}
