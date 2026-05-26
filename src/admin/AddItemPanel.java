package admin;

import entity.Item;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import managers.ItemManager;

/**
 *
 * @author ShaheerZK
 */
public class AddItemPanel extends JPanel implements ActionListener
{
    private JLabel addItemLabel = new JLabel("Add Item", SwingConstants.CENTER);
    private JButton addItem;
    private JButton selectImage;
    private final int buttonWidth = 210, buttonHeight = 70;
    
    private JTextField itemNameField;
    private JTextField itemPriceField;
    private JTextField itemQuantityField;
    private JFileChooser imageChooser;
    private ImageIcon itemImage;
    
    private JPanel formsPanel;
    
    public AddItemPanel()
    {
        setLayout(new BorderLayout());
        add(addItemLabel, BorderLayout.NORTH);
        initAddItem();
        initFormsPanel();
    }
    private void initAddItem()
    {
        addItem = new JButton("Add Item");
        addItem.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        addItem.addActionListener(this);
        addItem.setFocusable(false);
        add(addItem, BorderLayout.SOUTH);
    }
    private void initSelectImage()
    {
        selectImage = new JButton("Select image");
        selectImage.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        selectImage.addActionListener(this);
        selectImage.setFocusable(false);
    }
    private void initFormsPanel()
    {
        formsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        itemNameField = new JTextField();
        itemPriceField = new JTextField();
        itemQuantityField = new JTextField();
        imageChooser = new JFileChooser();
        initSelectImage();
        
        JLabel nameFieldLabel = new JLabel("Name");
        JLabel priceFieldLabel = new JLabel("Price");
        JLabel quantityFieldLabel = new JLabel("Quantity");
        JLabel imageChooserLabel = new JLabel("Choose an image");
        
        formsPanel.add(nameFieldLabel);
        formsPanel.add(itemNameField);
        
        formsPanel.add(priceFieldLabel);
        formsPanel.add(itemPriceField);
        
        formsPanel.add(quantityFieldLabel);
        formsPanel.add(itemQuantityField);
        
        formsPanel.add(imageChooserLabel);
        formsPanel.add(selectImage);
        
        add(formsPanel);
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == addItem)
        {
            String itemName = getItemName();
            double itemPrice = getItemPrice();
            int itemQuantity = getItemQuantity();
            ImageIcon itemImage = getItemImage();
            
            if (itemName == null || itemPrice == -1 || itemQuantity == -1 || itemImage == null)
                return;
            Item item = new Item(itemName, itemPrice, itemQuantity, itemImage);
            ItemManager.addItem(item);
            
        }
        else if (ae.getSource() == selectImage)
        {
            if (imageChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                handleTheFileAndButton();
        }
    }
    private String getItemName()
    {
        if (isEmpty(itemNameField))
        {
            highlightBorders(itemNameField);
            return null;
        }
        return itemNameField.getText();
    }
    private double getItemPrice()
    {
        if (isEmpty(itemPriceField))
        {
            highlightBorders(itemPriceField);
            return -1;
        }
        return Integer.parseInt(itemPriceField.getText());
    }
    private int getItemQuantity()
    {
        if (isEmpty(itemQuantityField))
        {
            highlightBorders(itemQuantityField);
            return -1;
        }
        return Integer.parseInt(itemQuantityField.getText());
    }
    private ImageIcon getItemImage()
    {
        if (itemImage == null)
            selectImage.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        return itemImage;
    }
    private boolean isEmpty(JTextField field)
    {
        return field.getText().equalsIgnoreCase("");
    }
    private void highlightBorders(JTextField field)
    {
        field.setBorder(BorderFactory.createLineBorder(Color.red, 1));
    }
    private void handleTheFileAndButton()
    {
        File file = imageChooser.getSelectedFile();
        itemImage = new ImageIcon(file.getAbsolutePath());
        selectImage.setText(file.getName());
    }
}
