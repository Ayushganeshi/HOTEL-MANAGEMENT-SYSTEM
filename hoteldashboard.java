

package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class hoteldashboard extends JFrame implements ActionListener {

    hoteldashboard() {
        setLayout(null);
        setBounds(0, 0, 1250, 1000);
        setVisible(true);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 1000);
        add(image);

        JLabel text = new JLabel("THE TAJ GROUP WELCOMES YOU");
        text.setBounds(450, 80, 1000, 50);
        text.setFont(new Font("Tohoma", Font.PLAIN, 40));
        text.setForeground(Color.white);
        image.add(text);

        JMenuBar mb = new JMenuBar();
        mb.setBounds(0, 0, 1550, 30);
        image.add(mb);

        JMenu m1 = new JMenu("HOTEL MANAGEMENT");
        m1.setForeground(Color.BLUE);
        mb.add(m1);

        JMenu m2 = new JMenu("ADMIN");
        m2.setForeground(Color.BLACK);
        mb.add(m2);

        JMenuItem reception = new JMenuItem("RECEPTION");
        reception.addActionListener(this);
        m1.add(reception);

        JMenuItem addemployee = new JMenuItem("ADD EMPLOYEE");
        addemployee.addActionListener(this);
        m2.add(addemployee);

        JMenuItem addrooms = new JMenuItem("ADD ROOMS");
        addrooms.addActionListener(this);
        m2.add(addrooms);

        JMenuItem adddrivers = new JMenuItem("ADD DRIVERS");
        adddrivers.addActionListener(this);
        m2.add(adddrivers);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("ADD EMPLOYEE")) {
            new AddEmployee();
        } else if (ae.getActionCommand().equals("ADD ROOMS")) {
            new AddRooms();
        }
        else if(ae.getActionCommand().equals("ADD DRIVERS")){
            new AddDriver();
        }else if(ae.getActionCommand().equals("RECEPTION")){
            new Reception();
        }
        

    }

    public static void main(String[] args) {
        new hoteldashboard();
    }
}