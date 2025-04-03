
package hotelmanagementsystem;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;

public class Food extends JFrame implements ActionListener {

    Choice categoryChoice, itemChoice, croom;
    JButton addOrder, back;
    JLabel foodImage;
    JTable table;

    Food() {
        setTitle("Food Ordering System");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(400, 150, 800, 500); 

        JLabel heading = new JLabel("Select Food");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(300, 20, 200, 30);
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel lblRoom = new JLabel("Room number:");
        lblRoom.setBounds(50, 80, 150, 25);
        add(lblRoom);

        croom = new Choice();
        croom.setBounds(200, 80, 150, 25);
        add(croom);

        try {
            connec c = new connec();
            ResultSet rs = c.s.executeQuery("SELECT * FROM room WHERE availability = 'Occupies'");
            while (rs.next()) {
                croom.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblCategory = new JLabel("Food Category:");
        lblCategory.setBounds(50, 140, 150, 30);
        add(lblCategory);

        categoryChoice = new Choice();
        categoryChoice.setBounds(200, 140, 200, 25);
        categoryChoice.add("Paratha");
        categoryChoice.add("Pizza");
        categoryChoice.add("Burger");
        categoryChoice.add("Non-Veg");
        categoryChoice.add("Drinks");
        categoryChoice.add("Coffee");
        categoryChoice.add("Water");
        add(categoryChoice);

        JLabel lblItem = new JLabel("Select Item:");
        lblItem.setBounds(50, 200, 150, 30);
        add(lblItem);

        itemChoice = new Choice();
        itemChoice.setBounds(200, 200, 200, 25);
        add(itemChoice);

        foodImage = new JLabel();
        foodImage.setBounds(450, 80, 300, 200);
        add(foodImage);

        categoryChoice.addItemListener(e -> updateFoodItems());

        addOrder = new JButton("Order Now");
        addOrder.setBackground(Color.BLACK);
        addOrder.setForeground(Color.WHITE);
        addOrder.setBounds(50, 330, 150, 40);
        addOrder.addActionListener(this);
        add(addOrder);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(220, 330, 150, 40);
        back.addActionListener(this);
        add(back);

        updateFoodItems();

        setVisible(true);
    }

    public void updateFoodItems() {
        itemChoice.removeAll();
        String selectedCategory = categoryChoice.getSelectedItem();

        if (selectedCategory.equals("Paratha")) {
            itemChoice.add("Aloo Paratha - ₹20");
            itemChoice.add("Gobi Paratha - ₹20");
            itemChoice.add("Paneer Paratha - ₹20");
            setFoodImage("icons/Paratha.jpg");
        } else if (selectedCategory.equals("Pizza")) {
            itemChoice.add("Cheese Pizza - ₹120");
            itemChoice.add("Veggie Pizza - ₹150");
            itemChoice.add("Pepperoni Pizza - ₹200");
            setFoodImage("icons/pizza.jpg");
        } else if (selectedCategory.equals("Burger")) {
            itemChoice.add("Veg Burger - ₹50");
            itemChoice.add("Chicken Burger - ₹80");
            itemChoice.add("Double Patty Burger - ₹100");
            setFoodImage("icons/burger.jpg");
        } else if (selectedCategory.equals("Non-Veg")) {
            itemChoice.add("Chicken Curry - ₹180");
            itemChoice.add("Mutton Biryani - ₹220");
            itemChoice.add("Fish Fry - ₹200");
            setFoodImage("icons/non_veg.jpg");
        } else if (selectedCategory.equals("Drinks")) {
            itemChoice.add("Cold Drink - ₹30");
            itemChoice.add("Juice - ₹50");
            itemChoice.add("Mocktail - ₹80");
            setFoodImage("icons/drinks.jpg");
        } else if (selectedCategory.equals("Coffee")) {
            itemChoice.add("Espresso - ₹40");
            itemChoice.add("Cappuccino - ₹60");
            itemChoice.add("Latte - ₹70");
            setFoodImage("icons/coffee.jpg");
        } else if (selectedCategory.equals("Water")) {
            itemChoice.add("Mineral Water - ₹20");
            itemChoice.add("Sparkling Water - ₹30");
            itemChoice.add("Flavored Water - ₹40");
            setFoodImage("icons/water2.jpg");
        }
    }

    public void setFoodImage(String imagePath) {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(imagePath));
        Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        foodImage.setIcon(new ImageIcon(img));
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addOrder) {
            try {
                connec c = new connec();
                String selectedRoom = croom.getSelectedItem();
                String selectedFood = itemChoice.getSelectedItem();

                int food_price = Integer.parseInt(selectedFood.replaceAll("[^0-9]", "")); 

                ResultSet rs = c.s.executeQuery("SELECT bill FROM customer WHERE room = '" + selectedRoom + "'");
                int current_bill = 0;
                if (rs.next()) {
                    current_bill = rs.getInt("bill");
                }

                int new_bill = current_bill + food_price;

                String query = "UPDATE customer SET bill = " + new_bill + " WHERE room = '" + selectedRoom + "'";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Room " + selectedRoom + " ordered: " + selectedFood + "\nUpdated Bill: ₹" + new_bill);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new Food();
    }
}




