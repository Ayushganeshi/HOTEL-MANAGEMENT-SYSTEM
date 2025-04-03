package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Bills extends JFrame implements ActionListener {

    Choice croom, foodChoice;
    JButton addBill, back;

    Bills() {
        setTitle("Place Your Order");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(400, 200, 800, 350); 

        JLabel text = new JLabel("Pace Order To Customer");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(30, 20, 250, 30);
        text.setForeground(Color.BLUE);
        add(text);

        JLabel lblroom = new JLabel("Room Number:");
        lblroom.setBounds(30, 80, 100, 30);
        add(lblroom);

        croom = new Choice();
        croom.setBounds(150, 80, 150, 25);
        add(croom);

        try {
            connec c = new connec();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                croom.add(rs.getString("room"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblfood = new JLabel("Select Food:");
        lblfood.setBounds(30, 130, 100, 30);
        add(lblfood);

        foodChoice = new Choice();
        foodChoice.setBounds(150, 130, 300, 25); 
        add(foodChoice);


        foodChoice.add("Pizza - 200");
        foodChoice.add("Burger - 100");
        foodChoice.add("Pasta - 150");
        foodChoice.add("Sandwich - 80");
        foodChoice.add("Coffee - 50");

        addBill = new JButton("Add Bill");
        addBill.setBackground(Color.BLACK);
        addBill.setForeground(Color.WHITE);
        addBill.setBounds(30, 240, 120, 30);
        addBill.addActionListener(this);
        add(addBill);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(180, 240, 120, 30);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/cash2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 350, 300); // Positioned to right half
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBill) {
            String roomNumber = croom.getSelectedItem();
            String selectedFood = foodChoice.getSelectedItem();
            int foodPrice = 0;

            // Extracting price from the selected item
            String[] parts = selectedFood.split("-");
            foodPrice = Integer.parseInt(parts[1].trim());

            try {
                connec c = new connec();
                c.s.executeUpdate("UPDATE customer SET bill = bill + " + foodPrice + " WHERE room = '" + roomNumber + "'");

                JOptionPane.showMessageDialog(null, "Bill Updated Successfully");

                setVisible(false);
                new Reception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new Bills();
    }
}