package authorize;

import datastructures.ArrayList;
import entity.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import managers.UserManager;
/**
 *
 * @author ShaheerZK
 */
public class SignUpPanel extends JPanel implements ActionListener
{
    private JLabel signUpLabel = new JLabel("Sign Up", SwingConstants.CENTER);
    private JLabel alreadyExistsLabel;
    private JLabel signUpSuccessLabel;
    private JTextField nameArea;
    private JTextField emailArea;
    private JPasswordField passwordArea;
    private JButton signUp;
    
    private CardLayout cardLayout;
    private JPanel formsPanel;
    
    private boolean isSuccessLabelOnScreen, isAlreadyExistsLabelOnScreen;
    SignUpPanel()
    {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        
        formsPanel = new JPanel(new BorderLayout());
        formsPanel.add(signUpLabel, BorderLayout.NORTH);
        formsPanel.add(getForms());
        formsPanel.add(signUp, BorderLayout.SOUTH);
        
        add("Sign Up", formsPanel);
        add("Sign In", new SignInPanel());
    }
    private void initNameArea()
    {
        nameArea = new JTextField();
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
        signUp = new JButton("Sign Up");
        signUp.addActionListener(this);
    }
    private JPanel getForms()
    {
        JPanel forms = new JPanel(new GridLayout(3, 2, 5, 7));
        
        initNameArea();
        initEmailArea();
        initPasswordArea();
        initSignUpButton();
        
        JLabel nameLabel = new JLabel("Name");
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");
        
        forms.add(nameLabel);
        forms.add(nameArea);
        
        forms.add(emailLabel);
        forms.add(emailArea);
        
        forms.add(passwordLabel);
        forms.add(passwordArea);
        
        return forms;
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == signUp)
        {
            String name = myGetName();
            String email = getEmail();
            String password = getPassword();
            if (name == null || email == null || password == null)
                return;
            
            User user = new User(name, email, password);
            if (alreadyExists(user))
            {
                initAlreadyExistsLabel();
                return;
            }
            UserManager.addUser(user);
            initSignUpSuccessLabel();
            cardLayout.show(this, "Sign In");
        }
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
    private boolean alreadyExists(User user)
    {
        String name = user.getName();
        String email = user.getEmail();
        ArrayList<User> users = UserManager.getUsers();
        for (int i = 0; i < users.size(); i++)
        {
            User tempUser = users.get(i);
            if (tempUser.getEmail().equalsIgnoreCase(email))
                return true;
        }
        return false;
    }
    private void initAlreadyExistsLabel()
    {
        if (!isAlreadyExistsLabelOnScreen)
        {
            alreadyExistsLabel = new JLabel("User with the same details already exists.");
            alreadyExistsLabel.setForeground(Color.red);
            formsPanel.add(alreadyExistsLabel, BorderLayout.EAST);
            isAlreadyExistsLabelOnScreen = true;
            revalidate();
            repaint();
        }
        if (signUpSuccessLabel != null && isSuccessLabelOnScreen)
            remove(signUpSuccessLabel);
    }
    private void initSignUpSuccessLabel()
    {
        if (!isSuccessLabelOnScreen)
        {
            alreadyExistsLabel = new JLabel("Signed up successfully.");
            alreadyExistsLabel.setForeground(Color.green);
            formsPanel.add(alreadyExistsLabel, BorderLayout.EAST);
            isSuccessLabelOnScreen = true;
            revalidate();
            repaint();
        }
        if (alreadyExistsLabel != null && isAlreadyExistsLabelOnScreen)
            remove(alreadyExistsLabel);
    }
}