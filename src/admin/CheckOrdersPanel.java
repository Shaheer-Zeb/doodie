package admin;

import datastructures.ArrayList;
import entity.Item;
import entity.Order;
import entity.User;
import java.awt.BorderLayout;
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
import managers.OrderManager;

/**
 *
 * @author ShaheerZK
 */
public class CheckOrdersPanel extends JPanel
{
    private JLabel checkOrdersLabel;
    
    private JPanel ordersPanel;
    private JScrollPane scrollPane;
    private final int scrollPaneWidth = 300, scrollPaneHeight = 400;
    
    private ArrayList<Order> orders = OrderManager.getOrders();
    
    public CheckOrdersPanel()
    {
        setLayout(new BorderLayout());
        initCheckOrdersLabel();
        initScrollPane();
        
        add(checkOrdersLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    private void initCheckOrdersLabel()
    {
        checkOrdersLabel = new JLabel("Check Orders", SwingConstants.CENTER);
    }
    private void initOrdersPanel()
    {
        ordersPanel = new JPanel(new GridLayout(0, 1, 2, 2));
        for (int i = 0; i < orders.size(); i++)
        {
            Order order = orders.get(i);
            if (!order.getIsFulfilled())
                ordersPanel.add(new OrderCard(order));
        }
    }
    private void initScrollPane()
    {
        initOrdersPanel();
        
        scrollPane = new JScrollPane(ordersPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.createVerticalScrollBar();
    }
    private class OrderCard extends JPanel implements ActionListener
    {
        private Order order;
        private User user;
        private Item item;
        
        private final int height = 140;
        
        private JButton setFulfilled;
        private JLabel itemNameLabel;
        private JLabel addressLabel;
        private JLabel itemImageLabel;
        private final int imageWidth = 100, imageHeight = 60;

        OrderCard(Order order)
        {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setMaximumSize(new Dimension(imageWidth, imageHeight));
            setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
            
            this.order = order;
            this.user = order.getUser();
            this.item = order.getItem();
            
            initSetFulfilled();
            initItemNameLabel();
            initAddressLabel();
            initItemImageLabel();
            
            add(itemNameLabel);
            add(itemImageLabel);
            add(addressLabel);
            add(setFulfilled);
        }
        public void initItemImageLabel()
        {
            itemImageLabel = new JLabel(new ImageIcon(item.getImage().getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH)), SwingConstants.CENTER);
        }
        public void initAddressLabel()
        {
            addressLabel = new JLabel("Address: " + order.getAddress(), SwingConstants.CENTER);
        }
        public void initItemNameLabel()
        {
            itemNameLabel = new JLabel(item.getName(), SwingConstants.CENTER);
        }
        private void initSetFulfilled()
        {
            setFulfilled = new JButton("Set Fulfilled");
            setFulfilled.setFocusable(false);
            setFulfilled.addActionListener(this);
        }
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if (ae.getSource() == setFulfilled)
            {
                order.setIsFulfilled(true);
                OrderManager.updateOrders();
                setFulfilled.setText("Order Fulfilled");
                setFulfilled.setEnabled(false);
            }
        }
    }
}
