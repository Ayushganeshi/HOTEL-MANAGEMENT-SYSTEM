package hotelmanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class SearchRoom extends JFrame implements ActionListener {

    JTable table;
    JButton back, submit;
    JComboBox bedType;
    JCheckBox available;

    SearchRoom() {
        setBounds(200, 100, 1000, 600);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel text = new JLabel("Search for Room");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(400, 30, 200, 30);
        add(text);
        
        JLabel lblbed = new JLabel("Bed Type");
        lblbed.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(400, 30, 200, 30);
        add(lblbed);

        bedType = new JComboBox(new String[]{"Single Bed", "Double Bed"});
        bedType.setBounds(150, 100, 150, 25);
        bedType.setBackground(Color.white);
        add(bedType);

        available = new JCheckBox("Only display Available");
        available.setBounds(600, 100, 150, 25);
        available.setBackground(Color.white);
        add(available);

        table = new JTable();
        table.setBounds(0, 200, 1000, 300);
        add(table);

        JLabel l1 = new JLabel("Room_Number");
        l1.setBounds(50, 160, 100, 20);
        add(l1);
        JLabel l2 = new JLabel("Availability");
        l2.setBounds(270, 160, 100, 20);
        add(l2);
        JLabel l3 = new JLabel("Status");
        l3.setBounds(450, 160, 100, 20);
        add(l3);
        JLabel l4 = new JLabel("Price");
        l4.setBounds(640, 160, 100, 20);
        add(l4);
        JLabel l5 = new JLabel("Bed Type");
        l5.setBounds(870, 160, 100, 20);
        add(l5);

        try {
            connec c = new connec();
            ResultSet rs = c.s.executeQuery("select*from room");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);
        submit = new JButton("Submit");
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        submit.setBounds(300, 520, 120, 30);
        add(submit);

        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        back.setBounds(500, 520, 120, 30);
        add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            try {
                String query1 = "select * from room where bed_type = '" + bedType.getSelectedItem() + "'";
                String query2 = "select * from room where availability = 'Available' AND bed_type = '" + bedType.getSelectedItem() + "'";

                connec conn = new connec();
                ResultSet rs;
                if (available.isSelected()) {
                    rs = conn.s.executeQuery(query2);

                } else {
                    rs = conn.s.executeQuery(query1);
                }
                table.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}
