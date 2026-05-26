package admin;

import entity.Item;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import managers.ItemManager;

/**
 * @author ShaheerZK
 */
public class EditItemPanel extends JPanel
{
    private CardLayout cardLayout = new CardLayout();
    private EditItem editItemPanel;
    private JPanel itemsPanel;
    private JScrollPane scrollPane;
    private final int scrollPaneWidth = 300, scrollPaneHeight = 450;
    
    private JPanel mainPanel;

    public EditItemPanel()
    {
        setLayout(cardLayout);
        initMainPanel();
        add(mainPanel);
    }
    private void initMainPanel()
    {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JLabel("Edit Items", SwingConstants.CENTER), BorderLayout.NORTH);
        initScrollPane();
        add(scrollPane, BorderLayout.CENTER);
    }
    private void initItemsPanel()
    {
        itemsPanel = new JPanel(new GridLayout(0, 3, 3, 3));
        displayItems();
    }
    private void displayItems()
    {
        for (int i = 0; i < ItemManager.getItemCount(); i++)
        {
            itemsPanel.add(new EditItemCard(ItemManager.getItemByIndex(i)));
        }
    }
    private void initScrollPane()
    {
        initItemsPanel();
        scrollPane = new JScrollPane(itemsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
    }
    private class EditItemCard extends JPanel implements ActionListener
    {
        private JButton editItemBtn;
        private JLabel itemName;
        private JLabel imageLabel;
        private Item item;
        private final int imageWidth = 70, imageHeight = 40;
        private EditItemCard(Item item)
        {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.item = item;
            
            itemName = new JLabel(item.getName());
            imageLabel = new JLabel(new ImageIcon(item.getImage().getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH)));
            initEditItem();
            add(itemName);
            add(imageLabel);
            add(editItemBtn);
        }
        private void initEditItem()
        {
            editItemBtn = new JButton("Edit Item");
            editItemBtn.setFocusable(false);
            editItemBtn.addActionListener(this);
        }
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if (ae.getSource() == editItemBtn)
            {
                editItemPanel = new EditItem(item);
                EditItemPanel.this.add("Edit Item", editItemPanel);
                cardLayout.show(EditItemPanel.this, "Edit Item");
            }
        }
    }
    private class EditItem extends JPanel implements ActionListener
    {
        private Item item;
        
        private JTextField nameArea;
        private JTextField quantityArea;
        private JTextField priceArea;
                
        private JPanel formsPanel;
        private JFileChooser imageChooser;
        private JButton chooseImageBtn;
        private JButton editBtn;
        private ImageIcon itemImage;
        private final int buttonWidth = 210, buttonHeight = 70;
        
        public EditItem(Item item)
        {
            setLayout(new BorderLayout());
            this.item = item;
            
            add(new JLabel("Edit Item", SwingConstants.CENTER), BorderLayout.NORTH);
            initFormsPanel();
            add(formsPanel, BorderLayout.CENTER);
            initEditBtn();
            add(editBtn, BorderLayout.SOUTH);
        }
        public void setItem(Item item)
        {
            this.item = item;
        }
        private void initFormsPanel()
        {
            formsPanel = new JPanel(new GridLayout(4, 2, 3, 3));
            
            initNameArea();
            initQuantityArea();
            initPriceArea();
            initImageChooser();
            initChooseImageBtn();
                        
            formsPanel.add(new JLabel("Name"));
            formsPanel.add(nameArea);
            
            formsPanel.add(new JLabel("Price"));
            formsPanel.add(priceArea);
            
            formsPanel.add(new JLabel("Quantity"));
            formsPanel.add(quantityArea);
            
            formsPanel.add(new JLabel("Select Image"));
            formsPanel.add(chooseImageBtn);
            
        }
        private void initChooseImageBtn()
        {
            chooseImageBtn = new JButton("Choose Image");
            chooseImageBtn.setFocusable(false);
            chooseImageBtn.addActionListener(this);
            chooseImageBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        }
        private void initNameArea()
        {
            nameArea = new JTextField(item.getName());
        }
        private void initQuantityArea()
        {
            quantityArea = new JTextField(String.valueOf(item.getQuantity()));
        }
        private void initPriceArea()
        {
            priceArea = new JTextField(String.valueOf((int)item.getPrice()));
        }
        private void initImageChooser()
        {
            imageChooser = new JFileChooser();
            
        }
        private void initEditBtn()
        {
            editBtn = new JButton("Edit");
            editBtn.setFocusable(false);
            editBtn.addActionListener(this);
            editBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        }
        private String getItemName()
        {
            String text = nameArea.getText();
            if (text.equalsIgnoreCase(""))
            {
                highlightBorder(nameArea);
                return null;
            }
            return text;
        }
        private double getPrice()
        {
            String text = priceArea.getText();
            if (text.equalsIgnoreCase(""))
            {
                highlightBorder(priceArea);
                return -1;
            }
            double price = Double.parseDouble(text);
            if (price < 0)
            {
                System.out.println("The price can't be negative.");
                return -1;
            }
            return price;
        }
        private int getQuantity()
        {
            String text = quantityArea.getText();
            if (text.equalsIgnoreCase(""))
            {
                highlightBorder(quantityArea);
                return -1;
            }
            int quantity = Integer.parseInt(text);
            if (quantity < 0)
            {
                System.out.println("The quantity can't be negative.");
                return -1;
            }
            return quantity;
        }
        private ImageIcon getImage()
        {
            if (itemImage == null)
                chooseImageBtn.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            return itemImage;
        }
        private void highlightBorder(JTextField field)
        {
            field.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        }
        private void handleTheFileAndButton()
        {
            File file = imageChooser.getSelectedFile();
            itemImage = new ImageIcon(file.getAbsolutePath());
            chooseImageBtn.setText(file.getName());
        }
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if (ae.getSource() == editBtn)
            {
                String name = getItemName();
                double price = getPrice();
                int quantity = getQuantity();
                ImageIcon image = getImage();
                
                if (name == null || price == -1 || quantity == -1 || image == null)
                    return;
                item.setName(name);
                item.setPrice(price);
                item.setQuantity(quantity);
                item.setImage(image);
                ItemManager.updateItems();
            }
            else if (ae.getSource() == chooseImageBtn)
            {
                if (imageChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                    handleTheFileAndButton();
            }
        }
    }
}
