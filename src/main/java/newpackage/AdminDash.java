/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kartik
 */
public class AdminDash {

    private JFrame f;
    private JButton btn, btn1;
    private JTable j;
    private String[] columnnames = {"Name", "ID", "Mobile Number", "Password"};
    //private Object[][] data;
    private String[][] data;

    public AdminDash() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AWPusers_and_Login", "kartik", "Kartik1901");
            Statement sta = con.createStatement();
            String query = "SELECT * FROM Users;";
            ResultSet rs = sta.executeQuery(query);
            int count = 0;
            while (rs.next()) {
                count++;
            }
            data = new String[count][4];
            //int columns = rs.getMetaData().getColumnCount();
            int i = 0;
            rs.beforeFirst();
            while (rs.next()) {
                data[i][0] = rs.getString("Name");
                data[i][1] = rs.getString("ID");
                data[i][2] = rs.getString("mobno");
                data[i][3] = rs.getString("pass");
                i++;

            }

        } catch (Exception e) {
        }

        f = new JFrame();
        String[] columnNames = {"Name", "ID", "Mobile Number", "Password"};
        // Frame Title 
        f.setTitle("Admin Panel");
        j = new JTable(data, columnNames);
        btn = new JButton("Delete");
        btn1 = new JButton("Logout");
        Object rowData[] = new Object[4];
        f.setLayout(new GridBagLayout());
        GridBagConstraints x = new GridBagConstraints();
        x.insets = new Insets(5, 5, 5, 5);
        //j.setPreferredSize(new Dimension(200, 300));
        // adding it to JScrollPane 
        JScrollPane sp = new JScrollPane(j);
        x.gridx = 0;
        x.gridy = 0;
        x.anchor = GridBagConstraints.LINE_END;
        f.add(sp, x);
        //btn.setSize(100, 40);
        x.gridx = 0;
        x.gridy = 1;
        x.anchor = GridBagConstraints.LINE_START;
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    int selected = j.getSelectedRow();
                    String key = j.getValueAt(selected, 1).toString();
                    String Query = "DELETE FROM Users WHERE ID=" + key + ";";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AWPusers_and_Login", "kartik", "Kartik1901");
                    Statement sta = con.createStatement();
                    sta.executeUpdate(Query);
                    f.dispose();
                    new AdminDash();
                } catch (Exception ex) {
                    Logger.getLogger(AdminDash.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                f.dispose();
                new Login();

            }
        });
        f.add(btn, x);
        x.gridx = 0;
        x.gridy = 1;
        x.anchor = GridBagConstraints.LINE_END;
        f.add(btn1, x);
        // Frame Size 
        f.setSize(500, 500);
        // Frame Visible = true
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new AdminDash();
    }

}
