package Water_management_system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ViewInformation extends JFrame implements ActionListener{

    JButton cancel;
    ViewInformation(String meter) {
        setBounds(350, 150, 850, 650);
        getContentPane().setBackground(new Color(0X78DEC7));
        setLayout(null);
        
        
        JLabel heading = new JLabel("VIEW CUSTOMER INFORMATION");
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(70, 80, 100, 20);
        add(lblname);
        
        JLabel name = new JLabel("");
        name.setBounds(250, 80, 100, 20);
        add(name);
        
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(70, 140, 100, 20);
        add(lblmeternumber);
        
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(250, 140, 100, 20);
        add(meternumber);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(70, 200, 100, 20);
        add(lbladdress);
        
        JLabel address = new JLabel("");
        address.setBounds(250, 200, 100, 20);
        add(address);
        
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(70, 260, 100, 20);
        add(lblcity);
        
        JLabel city = new JLabel("");
        city.setBounds(250, 260, 100, 20);
        add(city);
        
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(500, 80, 100, 20);
        add(lblstate);
        
        JLabel state = new JLabel("");
        state.setBounds(650, 80, 100, 20);
        add(state);
        
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(500, 140, 100, 20);
        add(lblemail);
        
        JLabel email = new JLabel("");
        email.setBounds(650, 140, 100, 20);
        add(email);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(500, 200, 100, 20);
        add(lblphone);
        
        JLabel phone = new JLabel("");
        phone.setBounds(650, 200, 100, 20);
        add(phone);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where lblmeter = '"+meter+"'");
            while(rs.next()) {
                name.setText(rs.getString("tfname"));
                address.setText(rs.getString("tfaddress"));
                city.setText(rs.getString("tfcity"));
                state.setText(rs.getString("tfstate"));
                email.setText(rs.getString("tfemail"));
                phone.setText(rs.getString("tfphone"));
                meternumber.setText(rs.getString("lblmeter"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(350, 340, 100, 25);
        add(cancel);
        cancel.addActionListener(this);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20, 350, 600, 300);
        add(image);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }
    
    public static void main(String[] args) {
        new ViewInformation("");
    }
}
