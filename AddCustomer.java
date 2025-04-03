
package hotelmanagementsystem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class AddCustomer extends JFrame implements ActionListener {

    JTextField tfnumber, tfname, tfcountry, tfdeposite;
    JComboBox comboid;
    JRadioButton rmale, rfemale;
    Choice croom;
    JLabel Checkintime;
    JButton add, back;

    public AddCustomer() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel text = new JLabel("NEW CUSTOMER FORM");
        text.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        text.setBounds(118, 11, 260, 53);
        add(text);

        JLabel lblid = new JLabel("ID");
        lblid.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblid.setBounds(35, 80, 100, 20);
        add(lblid);

        String options[] = {"Aadhar Card", "Passport", "Driving License", "Voter-id Card", "Ration Card"};
        comboid = new JComboBox(options);
        comboid.setBounds(200, 80, 150, 25);
        comboid.setBackground(Color.white);
        add(comboid);

        JLabel lblnumber = new JLabel("Number");
        lblnumber.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblnumber.setBounds(35, 120, 100, 20);
        add(lblnumber);

        tfnumber = new JTextField();
        tfnumber.setBounds(200, 120, 150, 25);
        add(tfnumber);

        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblname.setBounds(35, 160, 100, 20);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblgender.setBounds(35, 200, 100, 20);
        add(lblgender);

        rmale = new JRadioButton("Male");
        rmale.setBackground(Color.white);
        rmale.setBounds(200, 200, 60, 25);
        add(rmale);

        rfemale = new JRadioButton("Female");
        rfemale.setBackground(Color.white);
        rfemale.setBounds(270, 200, 100, 25);
        add(rfemale);

        JLabel lblcountry = new JLabel("Country");
        lblcountry.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblcountry.setBounds(35, 240, 100, 20);
        add(lblcountry);

        tfcountry = new JTextField();
        tfcountry.setBounds(200, 240, 150, 25);
        add(tfcountry);

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblroom.setBounds(35, 280, 150, 20);
        add(lblroom);

        croom = new Choice();
        try {
            connec conn = new connec();
            String query = "select * from room where availability = 'Available'";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                croom.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        croom.setBounds(200, 280, 150, 25);
        add(croom);

        JLabel lbltime = new JLabel("Check-in Time");
        lbltime.setFont(new Font("Raleway", Font.PLAIN, 20));
        lbltime.setBounds(35, 320, 150, 20);
        add(lbltime);

        Date date = new Date();
        Checkintime = new JLabel("" + date);
        Checkintime.setFont(new Font("Raleway", Font.PLAIN, 16));
        Checkintime.setBounds(200, 320, 200, 25);
        add(Checkintime);

        JLabel lbldeposite = new JLabel("Deposit");
        lbldeposite.setFont(new Font("Raleway", Font.PLAIN, 20));
        lbldeposite.setBounds(35, 360, 100, 20);
        add(lbldeposite);

        tfdeposite = new JTextField();
        tfdeposite.setBounds(200, 360, 150, 25);
        add(tfdeposite);

        add = new JButton("Add");
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        add.setBounds(50, 410, 120, 30);
        add.addActionListener(this);
        add(add);

        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(230, 410, 120, 30);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i3 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(420, 10, 300, 500);
        add(l1);
        
        setBounds(350, 200, 800, 550);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String id = (String) comboid.getSelectedItem();
            String number = tfnumber.getText().trim();
            String name = tfname.getText().trim();
            String gender = rmale.isSelected() ? "Male" : rfemale.isSelected() ? "Female" : "";
            String country = tfcountry.getText().trim();
            String room = croom.getSelectedItem();
            String time = Checkintime.getText();
            String deposite = tfdeposite.getText().trim();
            int bill=0;

            if (number.isEmpty() || name.isEmpty() || gender.isEmpty() || country.isEmpty() || deposite.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!deposite.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Deposit must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                connec conn = new connec();

                String query = "INSERT INTO customer VALUES('" + id + "', '" + number + "', '" + name + "', '" + gender + "', '" + country + "', '" + room + "', '" + time + "', '" + deposite + "','"+bill+"')";
                
                String query2 = "UPDATE room SET availability = 'Occupied' WHERE roomnumber = '" + room + "'";

                conn.s.executeUpdate(query);
                conn.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "New Customer Added Successfully");
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
        new AddCustomer();
    }
}
