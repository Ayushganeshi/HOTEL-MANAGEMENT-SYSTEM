

package hotelmanagementsystem; 

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class CustomerInfo extends JFrame implements ActionListener{

    JTable table;
    JButton back;

    CustomerInfo() {
        setBounds(200, 150, 1300, 600);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        table = new JTable();
        table.setBounds(0, 40, 1300, 400);
        table.setFont(new Font("Tahoma", Font.PLAIN, 13)); 
        table.setRowHeight(25); 
        add(table);

        JLabel l1 = new JLabel("Document Type");
        l1.setBounds(30, 10, 120, 20);
        add(l1);
        
        JLabel l2 = new JLabel("Number");
        l2.setBounds(180, 10, 100, 20);
        add(l2);
        
        JLabel l3 = new JLabel("Name");
        l3.setBounds(330, 10, 100, 20);
        add(l3);
        
        JLabel l4 = new JLabel("Gender");
        l4.setBounds(480, 10, 100, 20);
        add(l4);
        
        JLabel l5 = new JLabel("Country");
        l5.setBounds(625, 10, 100, 20);
        add(l5);
        
        JLabel l6 = new JLabel("Room Number");
        l6.setBounds(765, 10, 120, 20);
        add(l6);
        
        JLabel l7 = new JLabel("Checkin Time");
        l7.setBounds(905, 10, 120, 20);
        add(l7);
        
        JLabel l8 = new JLabel("Deposit"); 
        l8.setBounds(1055, 10, 100, 20);
        add(l8);
        
        JLabel l9 = new JLabel("Bill"); 
        l9.setBounds(1175, 10, 100, 20); 
        add(l9);

        try {
            connec c = new connec();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);

        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        back.setBounds(600, 500, 120, 30);
        add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Reception();
    }

    public static void main(String[] args) {
        new CustomerInfo();
    }
}











