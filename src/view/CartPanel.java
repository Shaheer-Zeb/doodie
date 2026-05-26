package view;

import datastructures.ArrayList;
import entity.Cart;
import entity.Item;
import entity.User;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 *
 * @author ShaheerZK
 */
public class CartPanel extends JPanel implements ActionListener
{
    private User user;
    private Cart cart;
    private JLabel cartLabel;
    private JButton checkoutBtn;
    
    private CardLayout cardLayout;
    private CheckoutPanel checkoutPanel;
    
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private final int scrollPaneWidth = 300, scrollPaneHeight = 400;
    private JPanel itemsPanel;
    
    public CartPanel(User user)
    {
        this.user = user;
        this.cart = user.getCart();
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        
        initMainPanel();
        add("Main Panel", mainPanel);
        
        initCheckoutPanel();
        add("Checkout Panel", checkoutPanel);
    }
    private void initCartLabel()
    {
        cartLabel = new JLabel(user.getName() + "'s Cart", SwingConstants.CENTER);
    }
    private void initCheckoutBtn()
    {
        checkoutBtn = new JButton("Checkout");
        checkoutBtn.setFocusable(false);
        checkoutBtn.addActionListener(this);
    }
    private void initMainPanel()
    {
        mainPanel = new JPanel(new BorderLayout());
        initCartLabel();
        initCheckoutBtn();
        mainPanel.add(cartLabel, BorderLayout.NORTH);
        mainPanel.add(checkoutBtn, BorderLayout.SOUTH);
        
        initScrollPane();
        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }
    private void initCheckoutPanel()
    {
        checkoutPanel = new CheckoutPanel(user);
    }
    private void initScrollPane()
    {
        initItemsPanel();
        scrollPane = new JScrollPane(itemsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.createVerticalScrollBar();
    }
    private void initItemsPanel()
    {
        itemsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        displayItems();
    }
    private void displayItems()
    {
        ArrayList<Item> items = cart.getItems();
        int size = items.size();
        for (int i = 0; i < size; i++)
        {
            itemsPanel.add(new CartItemCard(items.get(i)));
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == checkoutBtn)
            cardLayout.show(this, "Checkout Panel");
    }
    private class CartItemCard extends JPanel implements ActionListener
    {
        private final int width = 40, height = 140;

        private final int imageWidth = 100, imageHeight = 60;
        private JLabel imageLabel;
        private JLabel nameLabel;
        private JLabel userNameLabel;
        private JLabel userEmailLabel;

        private Item item;
        private JButton addToCart;

        public CartItemCard(Item item)
        {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setMaximumSize(new Dimension(imageWidth, imageHeight));
            setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));

            this.item = item;

            initAddToCart();
            initNameLabel();
            initImageLabel();
            initUserNameLabel();
            initUserEmailLabel();

            add(imageLabel);
            add(nameLabel);
            add(addToCart);
        }
        private void initAddToCart()
        {
            addToCart = new JButton("Remove");
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
        private void initUserNameLabel()
        {
            
        }
        private void initUserEmailLabel()
        {
            
        }
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if (ae.getSource() == addToCart)
            {
                user.removeItemFromCart(item);
                addToCart.setText("Removed");
                addToCart.setEnabled(false);
            }
        }
    }
}
