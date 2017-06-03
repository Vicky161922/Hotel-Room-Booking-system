import java.sql.*;
import java.util.*;
import javax.swing.*;
import javafx.scene.paint.Color;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.table.*;
public class JTableDemo extends JFrame{
   
    public JTableDemo(){
    Vector columnNames = new Vector();
    Vector data = new Vector();
    JPanel p=new JPanel();
   
    try{
      Class.forName("oracle.jdbc.OracleDriver"); //Load Driver
       Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","161515101");
    String sql="Select * from hotel";
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery(sql);
    ResultSetMetaData md=rs.getMetaData();
    int columns=md.getColumnCount();
    for(int i=1;i<=columns;i++){
    columnNames.addElement(md.getColumnClassName(i));
          }
    while(rs.next()){
   Vector row=new Vector(columns);
   for(int i=1;i<=columns;i++){
   row.addElement(rs.getObject(i));
   
   }
   data.addElement(row); 
    }
    rs.close();
    stmt.close();    
    
    }
    catch(Exception e){
      System.out.println(e);
    }
    JTable table=new JTable(data,columnNames);
    TableColumn col;
    for(int i=0;i<table.getColumnCount();i++){
    col=table.getColumnModel().getColumn(i);
    col.setMaxWidth(250);
    }
    JScrollPane scrollPane=new JScrollPane(table);
    p.add(scrollPane);
    JFrame f=new JFrame();
    f.add(p);
    f.setSize(1000,1000);
    f.setVisible(true);
    }
    public static void main(String[] arg){
    JTableDemo ob=new JTableDemo();
        ob.setSize(1000,1000);
        ob.setLocation(150,100);
        ob.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ob.setResizable(false);
        ob.setVisible(true);
    
    }

  
    
}
