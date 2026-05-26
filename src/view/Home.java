package view;

import entity.User;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import managers.ItemManager;

/**
 *
 * @author ShaheerZK
 */
public class Home extends JPanel implements ActionListener
{
    private User user;
    
    private JPanel navbar;
    private JButton profileBtn;
    private final int profileBtnSize = 30;
    private JButton cartBtn;
    private final int cartBtnSize = 55;
    private JLabel homeLabel;
    
    private CardLayout cardLayout;
    private ProfilePanel profilePanel;
    private CartPanel cartPanel;
    
    private JPanel mainPanel;
    private JPanel itemsPanel;
    private final int rowsOfItems = 0, columnsOfItems = 3;
    
    private JScrollPane scrollPane;
    private final int scrollPaneWidth = 300, scrollPaneHeight = 450;
    
    public Home (User user)
    {
        setUser(user);
        
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        
        initMainPanel();
        add("Main Panel", mainPanel);
        
        profilePanel = new ProfilePanel(user);
        add("Profile Panel", profilePanel);
        
        cartPanel = new CartPanel(user);
        add("Cart Panel", cartPanel);
    }
    public void setUser(User user)
    {
        this.user = user;
    }
    public User getUser()
    {
        return user;
    }
    private void initMainPanel()
    {
        mainPanel = new JPanel(new BorderLayout());
        initNavbar();
        mainPanel.add(navbar, BorderLayout.NORTH);
        
        initItemsPanel();
        scrollPane = new JScrollPane(itemsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
        mainPanel.add(scrollPane);
    }
    private void initItemsPanel()
    {
        itemsPanel = new JPanel(new GridLayout(rowsOfItems, columnsOfItems, 10, 10));
        displayItemCards();
    }
    private void displayItemCards()
    {
        for (int i = 0; i < ItemManager.getItemCount(); i++)
        {
            itemsPanel.add(new ItemCard(ItemManager.getItemByIndex(i), user));
        }
    }
    private void initNavbar()
    {
        navbar = new JPanel(new BorderLayout());
        initProfileBtn();
        initCartBtn();
        initHomeLabel();
        navbar.add(profileBtn, BorderLayout.EAST);
        navbar.add(homeLabel, SwingConstants.CENTER);
        navbar.add(cartBtn, BorderLayout.WEST);
    }
    private void initHomeLabel()
    {
        homeLabel = new JLabel("Doodie");
    }
    private void initProfileBtn()
    {
        ImageIcon profileBtnImage = new ImageIcon("src/images/profile.png");
        profileBtn = new JButton(new ImageIcon(profileBtnImage.getImage().getScaledInstance(profileBtnSize, profileBtnSize, Image.SCALE_SMOOTH)));
        profileBtn.setFocusable(false);
        profileBtn.setBorderPainted(false);
        profileBtn.setContentAreaFilled(false);
        profileBtn.addActionListener(this);
    }
    private void initCartBtn()
    {
        ImageIcon cartBtnImage = new ImageIcon("src/images/cart.png");
        cartBtn = new JButton(new ImageIcon(cartBtnImage.getImage().getScaledInstance(cartBtnSize, cartBtnSize, Image.SCALE_SMOOTH)));
        cartBtn.setFocusable(false);
        cartBtn.setBorderPainted(false);
        cartBtn.setContentAreaFilled(false);
        cartBtn.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == profileBtn)
            cardLayout.show(this, "Profile Panel");
        else if (ae.getSource() == cartBtn)
            cardLayout.show(this, "Cart Panel");
    }
}
