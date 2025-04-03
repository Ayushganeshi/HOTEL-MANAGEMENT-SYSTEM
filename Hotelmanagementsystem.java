
package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Hotelmanagementsystem extends JFrame implements ActionListener {
    JButton next;
    Hotelmanagementsystem(){
           setSize(1366, 565);
           setLocation(100,100);
           ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
           JLabel image = new JLabel(i1);
           add(image);
           
           JLabel text=new JLabel("HOTEL MANAGEMENT SYSYEM");
           text.setBounds(20,430,1000,90);
           text.setForeground(Color.WHITE);
           text.setFont(new Font("serif",Font.PLAIN, 50));
           image.add(text);
           setVisible(true);
           
           next=new JButton("Next");
           next.setBounds(1150,450,150,50);
           next.setBackground(Color.WHITE);
           next.setForeground(Color.BLACK);
           next.addActionListener(this);
           image.add(next);
           
           while (true)
           { text.setVisible(false);
             try
             { Thread.sleep(500);
             }
             catch (Exception e ){
                 e.printStackTrace();
             }
             text.setVisible(true);
             try
             { Thread.sleep(500);
             }
             catch (Exception e ){
                 e.printStackTrace();
             }
                     
           }

    }
    
    public void actionPerformed(ActionEvent ae){
         if(ae.getSource()== next){
             setVisible(false);
     new hotellogin();
   
     }
       
    }

    public static void main(String[] args) {
        new Hotelmanagementsystem();
    }
    
}



