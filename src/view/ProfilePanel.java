package view;

import entity.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import managers.UserManager;

/**
 *
 * @author ShaheerZK
 */
public class ProfilePanel extends JPanel implements ActionListener
{
    private User user;
    
    private JLabel profileLabel = new JLabel("Edit Profile", SwingConstants.CENTER);
    
    private JPanel formsPanel;
    private JButton editButton;
    private final int buttonWidth = 210, buttonHeight = 70;
    
    private JTextField nameArea;
    private JTextField emailArea;
    private JPasswordField passwordArea;
    
    private JLabel successLabel;
    public ProfilePanel(User user)
    {
        this.user = user;

        setLayout(new BorderLayout());
        add(profileLabel, BorderLayout.NORTH);
        
        initFormsPanel();
        initEditButton();
    }
    private void initFormsPanel()
    {
        formsPanel = new JPanel(new GridLayout(3, 2, 5, 7));
        
        JLabel nameLabel = new JLabel("Name");
        nameArea = new JTextField();
        nameArea.setText(user.getName());
        formsPanel.add(nameLabel);
        formsPanel.add(nameArea);
        
        JLabel emailLabel = new JLabel("Email");
        emailArea = new JTextField();
        emailArea.setText(user.getEmail());
        formsPanel.add(emailLabel);
        formsPanel.add(emailArea);
        
        JLabel passwordLabel = new JLabel("Password");
        passwordArea = new JPasswordField();
        passwordArea.setText(user.getPassword());
        formsPanel.add(passwordLabel);
        formsPanel.add(passwordArea);
        
        add(formsPanel);
    }
    private void initEditButton()
    {
        editButton = new JButton("Edit");
        editButton.addActionListener(this);
        editButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        editButton.setFocusable(false);
        add(editButton, BorderLayout.SOUTH);
    }
    private String myGetName()
    {
        if (isEmpty(nameArea))
        {
            highlightBorder(nameArea);
            return null;
        }
        return nameArea.getText();
    }
    private String getEmail()
    {
        if (isEmpty(emailArea))
        {
            highlightBorder(emailArea);
            return null;
        }
        return emailArea.getText();
    }
    private String getPassword()
    {
        if (isEmpty(passwordArea))
        {
            highlightBorder(passwordArea);
            return null;
        }
        return passwordArea.getText();
    }
    private boolean isEmpty(JTextField field)
    {
        return field.getText().equalsIgnoreCase("");
    }
    private void highlightBorder(JTextField field)
    {
        field.setBorder(BorderFactory.createLineBorder(Color.red, 1));
    }
    private void initSuccessLabel()
    {
        successLabel = new JLabel("Updated successfully.");
        successLabel.setForeground(Color.GREEN);
        add(successLabel, BorderLayout.EAST);
        repaint();
        revalidate();
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == editButton)
        {
            String name = myGetName();
            String email = getEmail();
            String password = getPassword();
            
            User updatedUser = new User(name, email, password);
            UserManager.removeUser(user);
            UserManager.addUser(updatedUser);
            
            initSuccessLabel();
        }
    }
}
