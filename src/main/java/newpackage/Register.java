/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.util.regex.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.activation.Activatable;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author kartik
 */
public class Register {

    private JFrame frame;
    private JLabel namelabel, nameHiddenLabel, idlabel, idLabelHidden, passlabel, passHiddenLabel, mobnoLabel, mobnoHiddenLabel,status;
    private JTextField namef, idf, mobnof;
    private JPasswordField passwordf;
    private JButton btn;

    public Register() {
        frame = new JFrame("Registration Form");
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setLocation(300, 200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        namelabel = new JLabel("Name:");
        nameHiddenLabel = new JLabel();
        idLabelHidden = new JLabel();
        idlabel = new JLabel("ID:");
        passHiddenLabel = new JLabel();
        passlabel = new JLabel("Password:");
        mobnoLabel = new JLabel("Mobile number:");
        mobnoHiddenLabel = new JLabel();
        status = new JLabel();
        namef = new JTextField();
        idf = new JTextField();
        mobnof = new JTextField();
        passwordf = new JPasswordField();
        btn = new JButton("Register");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints x = new GridBagConstraints();
        x.insets = new Insets(5, 5, 5, 5);
        x.gridx = 0;
        x.gridy = 0;
        x.anchor = GridBagConstraints.LINE_END;
        frame.add(namelabel, x);
        x.gridx = 1;
        x.gridy = 0;
        x.anchor = GridBagConstraints.LINE_START;
        frame.add(namef, x);
        namef.setPreferredSize(new Dimension(100, 20));
        x.gridx = 2;
        x.gridy = 0;
        x.anchor = GridBagConstraints.LINE_START;
        frame.add(nameHiddenLabel, x);
        x.gridx = 0;
        x.gridy = 1;
        x.anchor = GridBagConstraints.LINE_END;
        frame.add(idlabel, x);
        x.gridx = 1;
        x.gridy = 1;
        x.anchor = GridBagConstraints.LINE_START;
        frame.add(idf, x);
        idf.setPreferredSize(new Dimension(100, 20));
        x.gridx = 2;
        x.gridy = 1;
        x.anchor = GridBagConstraints.LINE_START;
        frame.add(idLabelHidden, x);
        x.gridx = 0;
        x.gridy = 2;
        x.anchor = GridBagConstraints.LINE_END;
        frame.add(mobnoLabel, x);
        x.gridx = 1;
        x.gridy = 2;
        x.anchor = GridBagConstraints.LINE_START;
        frame.add(mobnof, x);
        mobnof.setPreferredSize(new Dimension(100, 20));
        x.gridx = 2;
        x.gridy = 2;
        x.anchor = GridBagConstraints.LINE_START;
        frame.add(mobnoHiddenLabel, x);
        x.gridx = 0;
        x.gridy = 3;
        x.anchor = GridBagConstraints.LINE_END;
        frame.add(passlabel, x);
        x.gridx = 1;
        x.gridy = 3;
        x.anchor = GridBagConstraints.LINE_START;
        frame.add(passwordf, x);
        passwordf.setPreferredSize(new Dimension(100, 20));
        x.gridx = 2;
        x.gridy = 3;
        x.anchor = GridBagConstraints.LINE_START;
        frame.add(passHiddenLabel, x);
        x.gridx = 1;
        x.gridy = 4;
        x.gridwidth = 2;
        x.anchor = GridBagConstraints.CENTER;
        x.insets = new Insets(20, 0, 0, 0);
        frame.add(btn, x);
        x.gridx = 1;
        x.gridy = 5;
        x.gridwidth = 2;
        x.anchor = GridBagConstraints.CENTER;
        x.insets = new Insets(20, 0, 0, 0);
        frame.add(status,x);
        

        frame.pack();
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean b;
                b = control();
                if (b == true) {
                    //Register user..
                    register();

                }
            }
        });
    }

    boolean CheckValidMOB(String mobno) {
        boolean retval = false;
        Pattern mobpattern = Pattern.compile("\\d{10}");
        Matcher m = mobpattern.matcher(mobno);
        if (m.matches() == true) {
            retval = true;
        }
        return retval;

    }

    boolean CheckValidpass(String pass) {
        boolean retval = false;
        Pattern mobpattern = Pattern.compile(".{6,}");
        Matcher m = mobpattern.matcher(pass);
        if (m.matches() == true) {
            retval = true;
        }
        return retval;
    }

    boolean control() {
        String name = namef.getText();
        String id = idf.getText();
        String pass = passwordf.getText();
        String mobno = mobnof.getText();
        boolean b = false;
        boolean b1 = CheckValidMOB(mobno), b2 = CheckValidpass(pass);
        System.out.println(b1);
        if (b1 == false) {
            mobnoHiddenLabel.setText("* Please enter valid mobile number");
        } else {
            mobnoHiddenLabel.setText("");
        }
        if (b2 == false) {
            passHiddenLabel.setText("* Please enter a longer password");
        } else {
            passHiddenLabel.setText("");
        }
        if (b1 == true && b2 == true) {
            b = true;
        }
        return b;

    }

    void register() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AWPusers_and_Login", "kartik", "Kartik1901");
            Statement sta= con.createStatement();
            String name=namef.getText();String id = idf.getText();String pass=passwordf.getText();String mobno = mobnof.getText();
            String query= "INSERT INTO Users VALUES('"+name+"','"+id+"','"+pass+"','"+mobno+"')";
            int m = sta.executeUpdate(query);
            if(m==1)
            {
                status.setText("Registration Successful");
            }
        } catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String args[]) {
        new Register();
    }

}
