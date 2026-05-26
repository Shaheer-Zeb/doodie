package view;

import entity.Item;
import entity.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ItemCard extends JPanel implements ActionListener
{
    private final int width = 40, height = 140;
    
    private final int imageWidth = 100, imageHeight = 60;
    private JLabel imageLabel;
    private JLabel nameLabel;
    
    private Item item;
    private JButton addToCart;
    
    private User user;
    public ItemCard(Item item, User user)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(imageWidth, imageHeight));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        
        this.item = item;
        this.user = user;
        
        initAddToCart();
        initNameLabel();
        initImageLabel();

        add(imageLabel);
        add(nameLabel);
        add(addToCart);
    }
    private void initAddToCart()
    {
        addToCart = new JButton("Add to cart");
        addToCart.addActionListener(this);
        addToCart.setFocusable(false);
        addToCart.setAlignmentX(CENTER_ALIGNMENT);
    }
    private void initImageLabel()
    {
        imageLabel = new JLabel(new ImageIcon(item.getImage().getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH)));
        imageLabel.setPreferredSize(new Dimension(imageWidth, imageHeight));
        imageLabel.setAlignmentX(CENTER_ALIGNMENT);
    }
    private void initNameLabel()
    {
        nameLabel = new JLabel(item.getName());
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == addToCart)
        {
            user.addItemToCart(item);
            addToCart.setText("Added");
            addToCart.setEnabled(false);
        }
    }
}