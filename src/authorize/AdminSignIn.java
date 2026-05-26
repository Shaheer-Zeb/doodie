package authorize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import admin.AdminPanel;
import view.Home;
/**
 *
 * @author ShaheerZK
 */
public class AdminSignIn extends JPanel implements ActionListener
{
    private JLabel signInLabel = new JLabel("Admin Sign In", SwingConstants.CENTER);
    private JTextField emailArea;
    private JPasswordField passwordArea;
    private JButton signIn;
    
    private CardLayout cardLayout;
    private JPanel formsPanel = new JPanel(new BorderLayout());
    private Home homePanel;
    
    private final String email = "demo";
    private final String password = "demo";
    private AdminPanel adminPanel;
    public AdminSignIn()
    {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        
        formsPanel.add(signInLabel, BorderLayout.NORTH);
        formsPanel.add(getForms());
        formsPanel.add(signIn, BorderLayout.SOUTH);
        
        add(formsPanel, "Sign In");
    }
    private void initPasswordArea()
    {
        passwordArea = new JPasswordField();
    }
    private void initEmailArea()
    {
        emailArea = new JTextField();
    }
    private void initSignUpButton()
    {
        signIn = new JButton("Sign In");
        signIn.addActionListener(this);
    }
    private JPanel getForms()
    {
        JPanel forms = new JPanel(new GridLayout(2, 2, 5, 7));
        
        initEmailArea();
        initPasswordArea();
        initSignUpButton();
        
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");
        
        forms.add(emailLabel);
        forms.add(emailArea);
        
        forms.add(passwordLabel);
        forms.add(passwordArea);
        
        return forms;
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == signIn)
        {
            String email = getEmail();
            String password = getPassword();
            
            if (email == null || password == null)
                return;
            if (email.equalsIgnoreCase(this.email) && password.contentEquals(this.password))
            {
                adminPanel = new AdminPanel();
                add("Admin Panel", adminPanel);
                cardLayout.show(this, "Admin Panel");
            }
            else
                handleInvalidLogin();
        }
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
        if (isEmpty (passwordArea))
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
    private void handleInvalidLogin()
    {
        JLabel invalidLoginLabel = new JLabel("Invalid email or password.");
        invalidLoginLabel.setForeground(Color.red);
        formsPanel.add(invalidLoginLabel, BorderLayout.EAST);
        revalidate();
        repaint();
    }
}
