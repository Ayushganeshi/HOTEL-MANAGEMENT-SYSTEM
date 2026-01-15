
package hotelmanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sq.*;

public class EmployeeInfo extends JFrame implements ActionListener{

    JTable table;
    JButton back;

    EmployeeInfo() {
        setBounds(200, 150, 1200, 600);    
        getContentPane().setBackground(Color.white);
        setLayout(null);


        table = new JTable();
        table.setBounds(0, 40, 1200, 400);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12)); 
        table.setRowHeight(25); 
        add(table);

        JLabel l1 = new JLabel("Number");
        l1.setBounds(30, 10, 100, 20);
        add(l1);
        
        JLabel l2 = new JLabel("Age");
        l2.setBounds(200, 10, 100, 20);
        add(l2);
        
        JLabel l3 = new JLabel("Gender");
        l3.setBounds(350, 10, 100, 20);
        add(l3);
        
        JLabel l4 = new JLabel("Phone");
        l4.setBounds(500, 10, 100, 20);
        add(l4);
        
        JLabel l5 = new JLabel("Salary");
        l5.setBounds(650, 10, 100, 20);
        add(l5);
        
        JLabel l6 = new JLabel("Phone");
        l6.setBounds(800, 10, 100, 20);
        add(l6);
        
        JLabel l7 = new JLabel("Email");
        l7.setBounds(950, 10, 100, 20);
        add(l7);
        
        JLabel l8 = new JLabel("Aadhar");
        l8.setBounds(1090, 10, 100, 20);
        add(l8);

        try {
            connec c = new connec();
            ResultSet rs = c.s.executeQuery("select*from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        

        setVisible(true);

       
    back = new JButton("Back");
    back.setBackground(Color.black);
    back.setForeground(Color.white);
    back.addActionListener(this);
    back.setBounds(450,500,120,30);
    add(back);
    }
    
    public void actionPerformed(ActionEvent ae){
    setVisible(false);
    new Reception();
    }
    
    
    public static void main(String[] args) {
        new EmployeeInfo();
    }
}

