/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class login{
    
    private JFrame frame;
    private JLabel username,password,alert;
    private JTextField user;
    private JPasswordField pass;
    private JComboBox<String> choose;
    private JButton submit,registerbtn;
    boolean login1=false;
    
    public login(){
        frame=new JFrame("Login");
        
        frame.setSize(500, 500);
        frame.setLocation(400, 100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setLocationByPlatform(true);
        
        
        
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
        }catch(ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e)
        {
            System.err.println("Unsupported look and feel");
        }
        username=new JLabel("Username:");
        password=new JLabel("Password:");
        user=new JTextField();
        user.setPreferredSize(new Dimension(200,30));
        pass=new JPasswordField();
        pass.setPreferredSize(new Dimension(200,30));
        submit=new JButton("Submit");
        registerbtn=new JButton("Register");
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        c.insets=new Insets(15,15,0,15);
        c.gridx=0;
        c.gridy=1;
        c.anchor=GridBagConstraints.LINE_END;
        panel.add(username,c);
        c.gridx=1;
        c.gridy=1;
        c.anchor=GridBagConstraints.LINE_START;
        panel.add(user,c);
        c.gridx=0;
        c.gridy=2;
        c.anchor=GridBagConstraints.LINE_END;
        panel.add(password,c);
        c.gridx=1;
        c.gridy=2;
        c.anchor=GridBagConstraints.LINE_START;
        panel.add(pass,c);
        c.gridx=0;
        c.gridy=4;
        c.anchor=GridBagConstraints.CENTER;
        c.gridwidth=2;
        panel.add(submit,c);
        c.gridx=1;
        c.gridy=4;
        c.anchor=GridBagConstraints.LINE_END;
        panel.add(registerbtn,c);
        frame.setResizable(false);
        panel.setBackground(Color.white);
        choose=new JComboBox();
        choose.addItem("Admin");
        choose.addItem("Student");
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.CENTER;
        c.gridwidth=2;
        panel.add(choose,c);
        alert=new JLabel();
        c.gridx=0;
        c.gridy=5;
        c.anchor=GridBagConstraints.CENTER;
        c.gridwidth=2;
        panel.add(alert,c);
        frame.setVisible(true);
        
        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int curr_user=choose.getSelectedIndex();
                boolean b=IsNotempty();
                if(b==TRUE)
                {
                    
                    if(curr_user==1)
                    {
                        String username=user.getText();
                        String pwd=pass.getText();
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdetails", "root", "Suruchi@2001")) {
                            java.sql.Statement sta= con.createStatement();
                          
                            String query= "SELECT Name,Password FROM Users WHERE Name=? AND Password=?";
                            PreparedStatement ps=con.prepareStatement(query);
                            //ps.setString(1,ID);
                            ps.setString(1, username);
                            ps.setString(2, pwd);
                            ResultSet rs=ps.executeQuery();
                            if(rs.next())
                            {
                                String checkUser = rs.getString(1);
                            
                                String checkPass = rs.getString(2);
                            
                                if(checkUser.equals(username) && (checkPass.equals(pwd)))
                                {
                                    login1=TRUE;
                                    alert.setText("Logging in!!!");
                                    studentpanel studentpanel = new studentpanel(username,pwd);
                                    frame.dispose();
                                }
                                else
                                {
                                    login1=FALSE;
                                    alert.setText("Incorrect username or password!!!");
                                }
                            }
                            else{
                                alert.setText("Incorrect username or password!!!");
                            }
                        }  
       
                            
                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else
                    {
                        
                        if(choose.getSelectedIndex()==0)
                        {
                            alert.setText("Please login as student!!!");
                        }
                        else{
                            String username=user.getText();
                            String pwd=pass.getText();
                            if(username.equals("admin") && pwd.equals("admin1"))
                            {
                                new AdminDash();
                            }
                        }
                        
                    }
                }
                else 
                    {
                        alert.setText("Please fill the required details!!!");
                    }
                    
                    
            }
        }
        );
        
        registerbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if(choose.getSelectedIndex()==0)
                {
                    alert.setText("Please register as student!!!");
                }
                else{
                    new Register();
                    frame.dispose();
                }
                
            }
        });
    }
    
    boolean IsNotempty()
    {
        String name=user.getText();
        String pwd=pass.getText();
        boolean retval=true;
        if((name.length()==0) || (pwd.length()==0))
        {
            retval=false;
        }
        return retval;    
    }
    
    public static void main(String args[]) {
        login loginform = new login();
    }
}
