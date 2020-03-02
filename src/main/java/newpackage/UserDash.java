/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;  
public class UserDash {  
JFrame f;
JLabel nameLabel;
UserDash(String name){  
    f=new JFrame();
    JPanel p = new JPanel();
    nameLabel = new JLabel("Welcome"+name+"!");
    p.add(nameLabel);
    f.add(p);
    p.setVisible(true);
    JTextArea ta=new JTextArea(200,200);  
    JPanel p1=new JPanel();  
    p1.add(ta);  
    JPanel p2=new JPanel();  
    JPanel p3=new JPanel();  
    JTabbedPane tp=new JTabbedPane();  
    tp.setBounds(50,50,200,200);  
    tp.add("main",p1);  
    tp.add("visit",p2);  
    tp.add("help",p3);    
    f.add(tp);  
    f.setSize(400,400);  
    f.setLayout(null);  
    f.setVisible(true);  
}  
public static void main(String[] args) {  
    new UserDash("kartik");  
}}  
