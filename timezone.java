import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
public class timezone implements ActionListener, Runnable
{
    JFrame jf;
    JLabel l1,l2,time1,label,label2;
    int hours,minutes,seconds;
    String timeString,target;
    JButton b1,reset;
    Thread t = null;
    
    JComboBox<String> select;
    timezone()
    {
        l1 = new JLabel("World Clock");
        l1.setFont(l1.getFont().deriveFont(45.0f));
        String[] locations = 
        {
            "Select",
            "Los Angeles",
            "New York",
            "Sao Paulo",
            "London",
            "Berlin",
            "Singapore",
            "Tokyo"
    };
        t = new Thread(this);
        t.start();
        target="Select";
        Color val = new Color(36, 21, 195);
        l1.setSize(100, 100);
        l1.setForeground(val);
        l1.setBounds(205, 25, 450, 100);
        l2 = new JLabel("Select location:");
        l2.setFont(l2.getFont().deriveFont(20.0f));
        l2.setForeground(val);
        l2.setBounds(130, 200, 200, 40);
        label = new JLabel("Current Time is ");
        label.setFont(label.getFont().deriveFont(20.0f));
        label.setForeground(val);
        label.setBounds(95, 340, 750, 40);
        label2 = new JLabel();
        label2.setFont(label2.getFont().deriveFont(20.0f));
        label2.setForeground(val);
        label2.setBounds(100, 400, 650, 40);
        time1  = new JLabel();
        time1.setFont(time1.getFont().deriveFont(20.0f));
        time1.setForeground(val);
        time1.setBounds(250, 340, 400, 40);
        jf = new JFrame("World Clock");
        jf.setSize(800,600);
        jf.add(l1);
        b1=new JButton("Find Time");
        b1.setForeground(Color.white);
        b1.setBackground(val);
        b1.setBounds(210,270,130,30);
        b1.addActionListener(this);
        reset = new JButton("Reset");
        reset.setForeground(Color.white);
        reset.setBackground(val);
        reset.setBounds(360, 270, 130, 30);
        reset.addActionListener(this);
        select = new JComboBox<String>(locations);
        select.setBounds(370, 200, 200, 40);
        select.setForeground(val);
        jf.add(select);
        jf.add(label);
        jf.add(time1);
        jf.add(l2);
        jf.add(label2);
        jf.add(b1);
        jf.add(reset);
        jf.setLayout(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public void run() {  
      try {  
         while (true) {  
            Format formatter = new SimpleDateFormat("hh:mm:ss a      ||      dd-MM-yyyy");
            Date date = new Date();
            timeString = formatter.format( date );  
            time1.setText(timeString);
            if(target!="Select")
            label2.setText("Time in " + target + " is "+getTimeatZone(target));
            else
            label2.setText("");
            Thread.sleep(1000);  // interval in ms 
         }
         
      }
      catch (Exception e) {
      }
    }
    
    public static String getTimeatZone(String s1) {
        Instant instant = Instant.now();
        String res="";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm:ss a      ||      dd-MM-yyyy");
        switch(s1)
        {
            case "London":
            ZonedDateTime EnglandTime = instant.atZone(ZoneId.of("Europe/London"));
            res= format.format(EnglandTime);
            break;

            case "Berlin":
            ZonedDateTime GermanyTime = instant.atZone(ZoneId.of("Europe/Berlin"));
            res= format.format(GermanyTime);
            break;

            case "Los Angeles":
            ZonedDateTime WestUSTime = instant.atZone(ZoneId.of("America/Los_Angeles"));
            res=format.format(WestUSTime);
            break;

            case "New York":
            ZonedDateTime EastUSTime = instant.atZone(ZoneId.of("America/New_York"));
            res=format.format(EastUSTime);
            break;

            case  "Sao Paulo":
            ZonedDateTime BrazilTime = instant.atZone(ZoneId.of("Brazil/East"));
            res= format.format(BrazilTime);
            break;

            case "Singapore":
            ZonedDateTime SingaporeTime = instant.atZone(ZoneId.of("Singapore"));
            res =  format.format(SingaporeTime);
            break;

            case  "Tokyo":
            ZonedDateTime JapanTime = instant.atZone(ZoneId.of("Asia/Tokyo"));
            res=format.format(JapanTime);
            break;
        }
        return res;

    }
    public static void main(String[] args)
    {
       new timezone();
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
            String s2 = select.getItemAt(select.getSelectedIndex());
            target=s2;
        }
        if (e.getSource() == reset) {
            target = "Select";
            select.setSelectedItem("Select");
        }
    }
    
}