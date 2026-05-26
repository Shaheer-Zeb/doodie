package admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author ShaheerZK
 */
public class AdminPanel extends JPanel implements ActionListener
{
    private JButton addItem, editItem, checkOrders;
    private final int buttonWidth = 210, buttonHeight = 70;
    private JPanel buttonsPanel;
    private CardLayout cardLayout = new CardLayout();
    public AdminPanel()
    {
        initAddItem();
        initEditItem();
        initCheckOrders();
        
        this.setLayout(cardLayout);
        
        initAuthorizePanel();
        this.add(buttonsPanel);
        
        this.add("Add Item", new AddItemPanel());
        this.add("Edit Item", new EditItemPanel());
        this.add("Check Orders", new CheckOrdersPanel());
    }
    private void initAddItem()
    {
        addItem = new JButton("Add Item");
        addItem.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        addItem.setFocusable(false);
        addItem.addActionListener(this);
    }
    private void initEditItem()
    {
        editItem = new JButton("Edit Item");
        editItem.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        editItem.setFocusable(false);
        editItem.addActionListener(this);
    }
    private void initCheckOrders()
    {
        checkOrders = new JButton("Check Orders");
        checkOrders.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        checkOrders.setFocusable(false);
        checkOrders.addActionListener(this);
    }
    private void initAuthorizePanel()
    {
        buttonsPanel = new JPanel(new BorderLayout());
        
        JPanel forms = new JPanel();
        buttonsPanel.add(forms, BorderLayout.CENTER);
        buttonsPanel.add(new JLabel("Admin Panel", SwingConstants.CENTER), BorderLayout.NORTH);
        
        forms.add(addItem);
        forms.add(editItem);
        forms.add(checkOrders);
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == addItem)
            cardLayout.show(this, "Add Item");
        else if (ae.getSource() == editItem)
            cardLayout.show(this, "Edit Item");
        else if (ae.getSource() == checkOrders)
            cardLayout.show(this, "Check Orders");
    }
}
