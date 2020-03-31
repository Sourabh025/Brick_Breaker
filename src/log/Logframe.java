package log;

import block.Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.sql.*;  
import java.util.Date;

public class Logframe extends JFrame implements ActionListener   
{  
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;  
 
    JTextField tf1, tf2, tf11, tf22;  
    
    JButton btn1, btn2,btn3,log,clr,btn11,btn22;  
    
    JPasswordField p1, p2;  
    
    Logframe()  
    {  
        setVisible(true);  
        setSize(700, 400);  
        setLayout(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setTitle("Signup for Bad Brick");  
    
        l1 = new JLabel("Register for Bad Brick Game");  
        l1.setForeground(Color.blue);  
        l1.setFont(new Font("Serif", Font.BOLD, 20));  
        l2 = new JLabel("Name:");  
        l3 = new JLabel("Email-ID:");  
        l4 = new JLabel("Create Passowrd:");  
      
        tf1 = new JTextField();  
        tf2 = new JTextField();  
        p1 = new JPasswordField();  
         
        btn1 = new JButton("Signup");  
        
        btn2 = new JButton("Clear");
        
        btn3=new JButton("Login");
        
        
        btn1.addActionListener(this);  
        
        btn2.addActionListener(this);  
        
        btn3.addActionListener(this);
        
        l1.setBounds(150, 30, 400, 30);  
        l2.setBounds(80, 70, 200, 30);  
        l3.setBounds(80, 110, 200, 30);  
        l4.setBounds(80, 150, 200, 30);  
      
        tf1.setBounds(300, 70, 200, 30);  
        tf2.setBounds(300, 110, 200, 30);  
        p1.setBounds(300, 150, 200, 30);  
       
        btn1.setBounds(300, 200, 100, 30);  
        btn2.setBounds(400, 200, 100, 30);  
        btn3.setBounds(350,250,100,30);
        
        add(l1);  
        add(l2);  
        add(tf1);  
        add(l3);  
        add(tf2);  
        add(l4);  
        add(p1);  
       
        add(btn1);  
        add(btn2);  
        add(btn3);
    
    }  
    
    
    public void newframe(){
    	JFrame j=new JFrame();
    	j.setSize(700,400);
        j.setVisible(true);  
         
        j.setLayout(null);  
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        j.setTitle("Signin for Bad Brick");  
    
        l1 = new JLabel("Login for Bad Brick ");  
        l1.setForeground(Color.blue);  
        l1.setFont(new Font("Serif", Font.BOLD, 20));  
        l2 = new JLabel("Name:");  
          
        l4 = new JLabel("Passowrd:");  
      
        tf1 = new JTextField();  
          
        p1 = new JPasswordField();  
         
        log = new JButton("Log in");  
        
        clr = new JButton("Clear");
        
        
        
        
        log.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	int x = 0;  
                
                String name = tf1.getText();  
                 
                char[] s = p1.getPassword();  
                
                String pass=new String(s);
                    
                
                try  
                    {  
                        Class.forName("com.mysql.jdbc.Driver");  //load driver class that is mysql-connection(jar file package)  Register JDBC driver
                        
                        
                        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "aa44bd80sr");    //creating connection c 

                        System.out.println("Connected");
                        
                       

                        		String sqlString = "SELECT playername,player_password from register WHERE playername=? and player_password=?";

                        		PreparedStatement login = c.prepareStatement(sqlString);
                        		login.setString(1, name);
                        		login.setString(2, pass);
                        		ResultSet rs = login.executeQuery();
                       
                        		System.out.print("Login success");
                        		System.out.println(rs);
                        		
                        		x++;  		
                        
                        		if (rs.next() ) {  
                        			   System.out.println("Welcome::: "+name);
                        			   JOptionPane.showMessageDialog(log, "Login Successfully");
                        			   c.close();
                  
                        			   dispose();
                        			   Game obj=new Game();
                        			   dispose();
                        			   //call game from this point 
                        		}	  
                        	    else {  
                        	    	
                        	    	JOptionPane.showMessageDialog(log, "Enter valid username and password");  
                        		      
                        			  
                        			
                        	    }	   
                        		
                         	
                        
                        
                    }
                    catch (Exception ex)   
                    {  
                    	ex.printStackTrace();  
                    }  
      
            	
            	
            	
        }}); 
        
        clr.addActionListener(null);  
        
              
        l1.setBounds(150, 30, 400, 30);  
        l2.setBounds(80, 70, 200, 30);  
          
        l4.setBounds(80, 150, 200, 30);  
      
        tf1.setBounds(300, 70, 200, 30);  
          
        p1.setBounds(300, 150, 200, 30);  
       
        log.setBounds(350, 200, 100, 30);  
        clr.setBounds(400, 200, 100, 30);  
              
        j.add(l1);  
        j.add(l2);  
        j.add(tf1);  
            
        j.add(l4);  
        j.add(p1);  
       
        j.add(log);  
         
        

        
        
}
    	
        
    
          
        
        
        
        
        
        
    

 
    public void actionPerformed(ActionEvent e)   
    {  
        if (e.getSource() == btn1)  
         {  
            int x = 0;  
            String name = tf1.getText();  
            String mail = tf2.getText();  
            char[] s = p1.getPassword();  
            
            Date d=new Date();
            System.out.print(d);
            String pass=new String(s);
                
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String time = sdf.format(d);
            
            try  
                {  
                    Class.forName("com.mysql.jdbc.Driver");  //load driver class that is mysql-connection(jar file package)  Register JDBC driver
                    
                    
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "aa44bd80sr");    //creating connection c 

                    System.out.println("Connected");
                    
                    PreparedStatement ps = c.prepareStatement("insert into db.register(playername,player_email,player_password,dt) values(?,?,?,?)");  //prepare Statement to execute
                    
                   
                    ps.setString(1, name);  
                    ps.setString(2, mail);  
                    ps.setString(3, pass);
                    ps.setString(4,time);

                    ps.executeUpdate();  

                    System.out.print("Uploaded");
                    x++;  		
                    if (x > 0)   
                    {  
                 
                    	JOptionPane.showMessageDialog(btn1, "Data Saved Successfully");  
                    	
                    	ps.close();
                    	
                    	c.close();
                    	
                    	dispose();
                    	
                    	newframe();
                    
                    }  
                }
                catch (Exception ex)   
                {  
                	ex.printStackTrace();  
                }  
            
          }   
          
        if(e.getSource()== btn2) 
          {  
    
        	tf1.setText("");  
            tf2.setText("");  
            p1.setText("");  
          
          }  
        
        if (e.getSource() == btn3) {
        	
        	
        	newframe();
        	
        	
        	
   
        }
        
//        if(e.getSource()== log) {
//        	
//        	 int x = 0;  
//             
//             String name = tf11.getText();  
//              
//             char[] s = p1.getPassword();  
//             
//             String pass=new String(s);
//                 
//             
//             try  
//                 {  
//                     Class.forName("com.mysql.jdbc.Driver");  //load driver class that is mysql-connection(jar file package)  Register JDBC driver
//                     
//                     
//                     Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "aa44bd80sr");    //creating connection c 
//
//                     System.out.println("Connected");
//                     
//                    
//
//                     		String sqlString = "SELECT * from register WHERE playername=? and player_password=?";
//
//                     		PreparedStatement login = c.prepareStatement(sqlString);
//                     		login.setString(1, name);
//                     		login.setString(2, pass);
//                     		ResultSet rs = login.executeQuery();
//                    
//                     		System.out.print("Login success");
//                     		x++;  		
//                     
//                   if (x > 0)   
//                     {  
//                  
//                     	JOptionPane.showMessageDialog(log, "Login Successfully");  
//                     	
//                     	login.close();
//                     	
//                     	c.close();
//                     	
//                     	dispose();
//                     	
//                     	//call game class
//                     
//                     }  
//                 }
//                 catch (Exception ex)   
//                 {  
//                 	ex.printStackTrace();  
//                 }  
//             
//
//        	
//        	
//        	
//        	
//        	System.out.println("Success");
//        	
//        }
//        
//        
//
   }
    
    public static void main(String args[])  
    {  

    	new Logframe();  
    
    }  

}  
