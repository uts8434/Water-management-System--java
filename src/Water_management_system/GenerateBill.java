
package Water_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener{

    String meter;
    JButton bill;
    Choice cmonth1;
    JTextArea area;
    GenerateBill(String meter) {
        this.meter = meter;
        
        setSize(500, 800);
        setLocation(550, 30);
        
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        
        JLabel heading = new JLabel("Generate Bill");
        JLabel meternumber = new JLabel(meter);
        
        cmonth1 = new Choice();
        
        cmonth1.add("January");
        cmonth1.add("February");
        cmonth1.add("March");
        cmonth1.add("April");
        cmonth1.add("May");
        cmonth1.add("June");
        cmonth1.add("July");
        cmonth1.add("August");
        cmonth1.add("September");
        cmonth1.add("October");
        cmonth1.add("November");
        cmonth1.add("December");
        
        area = new JTextArea(50, 15);
        area.setText("\n\n\t--------Click on the---------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month");
        area.setFont(new Font("Senserif", Font.ITALIC, 18));
        
        JScrollPane pane = new JScrollPane(area);
        
        bill = new JButton("Generate Bill");
        bill.addActionListener(this);
        
        panel.add(heading);
        panel.add(meternumber);
        panel.add(cmonth1);
        add(panel, "North");
        
        add(pane, "Center");
        add(bill, "South");
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn c = new Conn();
            
            String month = cmonth1.getSelectedItem();
            
            area.setText("\t \n Water BILL GENERATED FOR THE MONTH\n\tOF "+month+", 2022\n\n\n");
            
            ResultSet rs = c.s.executeQuery("select * from customer where lblmeter = '"+meter+"'");
        
            if(rs.next()) {
                area.append("\n    Customer Name: " + rs.getString("tfname"));
                area.append("\n    Meter Number   : " + rs.getString("lblmeter"));
                area.append("\n    Address             : " + rs.getString("tfaddress"));
                area.append("\n    City                 : " + rs.getString("tfcity"));
                area.append("\n    State                : " + rs.getString("tfstate"));
                area.append("\n    Email                : " + rs.getString("tfemail"));
                area.append("\n    Phone                 : "     + rs.getString("tfphone"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }
            
            rs = c.s.executeQuery("select * from meter_info where meternumber = '"+meter+"'");
        
            if(rs.next()) {
                area.append("\n    Meter Location: " + rs.getString("meterlocation"));
                area.append("\n    Meter Type:     " + rs.getString("metertype"));
                area.append("\n    Bill Type:          " + rs.getString("billtype"));
                area.append("\n    Days:                " + rs.getString("days"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }
            
            rs = c.s.executeQuery("select * from tax");
        
            if(rs.next()) {
                area.append("\n");
                area.append("\n    Cost Per Unit: " + rs.getString("cost_per_unit"));
                area.append("\n    Meter Rent:     " + rs.getString("meter_rent"));
                area.append("\n    Service Charge:        " + rs.getString("service_charge"));
                area.append("\n    Service Tax:          " + rs.getString("service_tax"));
                area.append("\n    Swacch Bharat Cess:           " + rs.getString("swacch_bharat_cess"));
                area.append("\n    Fixed Tax: " + rs.getString("fixed_tax"));
                area.append("\n");
            }
            
            rs = c.s.executeQuery("select * from bill where meternumber = '"+meter+"' and cmonth='"+month+"'");
        
            if(rs.next()) {
                area.append("\n");
                area.append("\n    Current Month: " + rs.getString("cmonth"));
                area.append("\n    Units Consumed:     " + rs.getString("tfunits"));
                area.append("\n    Total Charges:        " + rs.getString("totalbill"));
                area.append("\n-------------------------------------------------------");
                area.append("\n    Total Payable: " + rs.getString("totalbill"));
                area.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GenerateBill("");
    }
}
