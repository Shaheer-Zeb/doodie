package authorize;

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
public class AuthorizeCard extends JPanel implements ActionListener
{
    private JButton signIn, signUp, adminSignIn;
    private final int buttonWidth = 210, buttonHeight = 70;
    private JPanel authorizePanel;
    private CardLayout cardLayout = new CardLayout();
    public AuthorizeCard()
    {
        initSignIn();
        initSignUp();
        initAdminSignIn();
        
        this.setLayout(cardLayout);
        
        initAuthorizePanel();
        this.add(authorizePanel);
        this.add("Sign In", new SignInPanel());
        this.add("Sign Up", new SignUpPanel());
        this.add("Admin Sign In", new AdminSignIn());
    }
    private void initSignIn()
    {
        signIn = new JButton("Sign In");
        signIn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        signIn.setFocusable(false);
        signIn.addActionListener(this);
    }
    private void initSignUp()
    {
        signUp = new JButton("Sign Up");
        signUp.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        signUp.setFocusable(false);
        signUp.addActionListener(this);
    }
    private void initAdminSignIn()
    {
        adminSignIn = new JButton("Admin Sign In");
        adminSignIn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        adminSignIn.setFocusable(false);
        adminSignIn.addActionListener(this);
    }
    private void initAuthorizePanel()
    {
        authorizePanel = new JPanel(new BorderLayout());
        
        JPanel forms = new JPanel(new GridLayout(1, 2, 10, 0));
        authorizePanel.add(forms, BorderLayout.CENTER);
        authorizePanel.add(new JLabel("Authorize", SwingConstants.CENTER), BorderLayout.NORTH);
        
        forms.add(signIn);
        forms.add(signUp);
        forms.add(adminSignIn);
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == signIn)
            cardLayout.show(this, "Sign In");
        else if (ae.getSource() == signUp)
            cardLayout.show(this, "Sign Up");
        else if (ae.getSource() == adminSignIn)
            cardLayout.show(this, "Admin Sign In");
    }
}
