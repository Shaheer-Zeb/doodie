package view;

import datastructures.ArrayList;
import entity.Cart;
import entity.Item;
import entity.Order;
import entity.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import managers.OrderManager;

/**
 *
 * @author ShaheerZK
 */
public class CheckoutPanel extends JPanel implements ActionListener
{
    private User user;
    private Cart cart;
    
    private JLabel checkoutLabel;
    private JLabel grandTotalLabel;
    private JButton placeOrderBtn;
    
    private JTextField addressArea;
    private String address;
    private JPanel mainPanel;
    
    public CheckoutPanel(User user)
    {
        this.user = user;
        this.cart = user.getCart();
        setLayout(new BorderLayout());
        
        initCheckoutLabel();
        initMainPanel();
        initPlaceOrderBtn();
        
        add(checkoutLabel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(placeOrderBtn, BorderLayout.SOUTH);
    }
    private void initMainPanel()
    {
        mainPanel = new JPanel(new GridLayout(3, 1, 0, 2));
        initAddressArea();
        initGetTotalLabel();
        
        mainPanel.add(new JLabel("Enter your address below"));
        mainPanel.add(addressArea);
        mainPanel.add(grandTotalLabel);
    }
    private void initAddressArea()
    {
        addressArea = new JTextField();
    }
    private void initCheckoutLabel()
    {
        checkoutLabel = new JLabel("Checkout", SwingConstants.CENTER);
    }
    private void initGetTotalLabel()
    {
        grandTotalLabel = new JLabel("The grand total will be: " + cart.getGrandTotal(), SwingConstants.CENTER);
    }
    private void initPlaceOrderBtn()
    {
        placeOrderBtn = new JButton("Place Order");
        placeOrderBtn.addActionListener(this);
        placeOrderBtn.setFocusable(false);
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == placeOrderBtn)
        {
            address = getAddress();
            if (address == null)
                return;
            placeOrders();
            grandTotalLabel.setText("Orders placed successfully.");
            grandTotalLabel.setForeground(Color.green);
        }  
    }
    private void placeOrders()
    {
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Item> items = cart.getItems();
        for (int i = 0; i < items.size(); i++)
        {
            orders.add(new Order(items.get(i), user, address));
        }
        OrderManager.placeOrders(orders);
    }
    private String getAddress()
    {
        String text = addressArea.getText();
        if (text.equalsIgnoreCase(""))
        {
            addressArea.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            return null;
        }
        return text;
    }
}
