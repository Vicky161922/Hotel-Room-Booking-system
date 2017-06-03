import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
public class delete extends JFrame implements ActionListener{
JLabel l1;
JTextField t1;
JButton b1;
Connection con;
public delete(String title)
{super (title);
    
    l1=new JLabel("Booking_no.");
    t1=new JTextField(30);
    b1=new JButton("Delete");
    b1.setMnemonic('D');
    setLayout(null);
    l1.setBounds(30,50,175,30);
    t1.setBounds(150,50,120,30);
    b1.setBounds(125,150,75,30);
    add(b1);
    b1.addActionListener(this);
    add(l1);
    add(t1);
     try 
        {
            connect();
            Statement st=con.createStatement();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
}

    private delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public void connect()
    {
        try 
        {
            Class.forName("oracle.jdbc.OracleDriver");//LOad Driver
            con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","161515101");
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
public void actionPerformed(ActionEvent ae)
{
    if(ae.getSource()==b1)
    {
        int i=JOptionPane.showConfirmDialog
                (null,"Are you sure to Cancel the Booking??",
                        "Message Box",JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
        if(i==JOptionPane.OK_OPTION)
        {
             try
            {   String booking_no=t1.getText();
                PreparedStatement ps=con.prepareStatement("delete from hotel where booking_no=?");
                ps.setString(1,booking_no);
                int ans=JOptionPane.showConfirmDialog(null,"Are you sure to Cancel Booking?","Delete Box",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                if(ans==JOptionPane.YES_OPTION)
                {
                   int j= ps.executeUpdate();
                    if(j==1)
                    {
                        t1.setText("");
                     JOptionPane.showMessageDialog(null,"Your Booking is canceled! Hope to see you again.");
                    }
                    
                }
                else
                {
                   JOptionPane.showMessageDialog(null, "Try again!!");
                    }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Try again!!");
        }
          
        }
        else
        {
           JOptionPane.showMessageDialog(null,"Try again");
        }
    }
}
public static void main(String[] args)
{
    delete d=new delete();
    d.setSize(350,350);
    d.setResizable(false);
    d.setLocation(250,350);
   // d.setDefaultCloseOperation(EXIT_ON_CLOSE);
    d.setVisible(true);
    
}
}
