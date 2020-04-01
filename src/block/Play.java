package block;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;		// argument for action listener 
import java.awt.event.ActionListener;  // this is for doing action on ball after keyListening 
import java.awt.event.KeyEvent;			// argument for keyListener methods 
import java.awt.event.KeyListener;   // this is for detecting arrow key tapped by a user 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;    // adding JPanel class from Swing 
import javax.swing.Timer;		//tracks time

@SuppressWarnings("serial")  // GamePlay class giving error for serialization of class so,Supress warning
public class Play extends JPanel implements KeyListener,ActionListener //,Runnable
{
	
	private int life;    //each player gets 2 lifes to play 
	
	private int cycle;
	
	private boolean play=false; 
	
	private int playerx=310; //player initial X axis value 
	
	private int ballposx=180; //ball initial value on x axis
	
	private int ballposy=360; //ball initial value on y axis 
	
	private int ballxdir=-1;  //ball direction on x axis
	
	private int ballydir=-2;	//ball direction on y axis 
	
	private int totalBricks=21;   // 7*3 bricks dimensions 
	
	private Timer timer; //adding timer to track time 
	
	private int delay;   
	
	private int playerscore;
	
	private Structure obj;
	
	private int n;
	
	public String playername;
	
	
	

	
	
	
	
	//--------------------------------------------------------------------------------------------------------
	
	//creating constructor so that values initialize itself when GamePlay object called from Main class  
	
	public Play(){
		
		delay=8;
		addKeyListener(this); 
		setFocusable(true);   //component get focus on JPanel
		obj=new Structure();
		obj.start();
		setFocusTraversalKeysEnabled(false);  // tab,shift, ctrl default values are not allowed in our app(we are overriding this method inside keyListener)
		timer= new Timer(delay,this);   //returns intValue 
		timer.start();  //start time 
	}
	
	//---------------------------------------------------------------------------------------------------------
	
	
	
	
	public void count() {
		
			life=life+1;
		
		
	}
	
	
	
	public void paint(Graphics g) {
		
		Graphics2D ob=(Graphics2D) g;
		
		//background_paint  
		
		g.setColor(Color.BLACK);
		
		
		//Borders
		//700x600 is window size
		
		g.fillRect(1,1,697,592); 
		g.setColor(Color.BLACK);
		g.fillRect(0,0,4,592);
		
		g.fillRect(0,0,700,4);
		
		g.fillRect(697,0,4,600);
		
		g.setColor(Color.YELLOW);

		//pad 
		ob.setColor(Color.WHITE);
		ob.fillRect(playerx,550,125,10);  //playerx value is variable because it changes every time with the key entered by the user which is defined in (keyPressed) function below   
		
//		Extra bars
//		g.fillRect(0,400, 100, 10);
//		g.fillRect(600,400, 100, 10);
		
		//ball 
		ob.setColor(Color.WHITE);
		
		ob.fillOval(ballposx,ballposy,15,15);  //ballposx,ballposy are varible because changes with player action but dimensions of ball are 20x20
		
		//Brick Structure 2d creating  
		obj.draw((Graphics2D)g);
		
			
		
		//score 
		ob.setColor(Color.BLUE);
		ob.setFont(new Font("arvil",Font.CENTER_BASELINE,25));
		ob.drawString("SCORE : " +playerscore,520,40);
		
		if(ballposy>=600) {   
			
			if(life==1){
				try { 
				Class.forName("com.mysql.jdbc.Driver");
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "admin");    //creating connection c 
                String sqlString = "SELECT playername,player_password from register WHERE playername=? and player_password=?";

        		PreparedStatement login = c.prepareStatement(sqlString);
        		login.setString(1, name);
        		
        		ResultSet rs = login.executeQuery();
       
        		System.out.print("Login success");
       
				
				
				}
				catch(Exception e) {
					
					
					
				}
				ob.setColor(Color.GREEN);
				ob.setFont(new Font("arvil",Font.LAYOUT_LEFT_TO_RIGHT,30));
				ob.drawString("Total Score : "+playerscore,240,300);
				ob.setFont(new Font("arvil",Font.CENTER_BASELINE,30));
				ob.drawString("You lost your all lives ",180,360);
				
				
				
				
				
			}
			
			else {
			
			ob.setColor(Color.RED);
			ob.setFont(new Font("arvil",Font.LAYOUT_LEFT_TO_RIGHT,30));
			ob.drawString("You lost life",280,300);
			ob.setFont(new Font("arvil",Font.CENTER_BASELINE,30));
			ob.drawString("Press Enter To Continue",180,340);
			
			
			}
			
			
			
			
		}

		if(totalBricks==0) {
			
			ballposx=-25;
			ballposy=-25;
			ob.setColor(Color.GREEN);
			ob.setFont(new Font("arvil",Font.LAYOUT_LEFT_TO_RIGHT,30));
			ob.drawString("Score : "+playerscore,280,300);
			ob.setFont(new Font("arvil",Font.CENTER_BASELINE,30));
			ob.drawString("Press Space To Continue",180,360);
			
			
			
			}
		
		
		g.dispose();
		
		
	}
	
	
   	@Override
	public void actionPerformed(ActionEvent event) {
		
   		
   		timer.start();  //timer used to set speed of the ball
		
		
		
		//detects collision between ball and the pad 
		if(new Rectangle(ballposx,ballposy,15,15).intersects(playerx,550,125,10)) {
			
			ballydir=-ballydir;
			
		}
		
//		 if(new Rectangle(ballposx,ballposy,15,15).intersects(0,400, 100, 10)) {
//			
//			ballydir=-ballydir;
//			
//		}
//
//		if(new Rectangle(ballposx,ballposy,15,15).intersects(600,400, 100, 10)) {
//			
//			ballydir=-ballydir;
//			
//		}
		
	
		
		
		
		//detects collision between brick and the ball
		for(int i=0;i<obj.Structure.length;i++){
			
			for(int j=0;j<obj.Structure[0].length;j++){
				
				if(obj.Structure[i][j]>0) {
					
					int brickxaxis= j * obj.brickwidth + 80; 
					int brickyaxis= i* obj.brickheight +50;
					int brickxdim=obj.brickwidth;
					int brickydim=obj.brickheight;
				
					Rectangle brick= new Rectangle(brickxaxis,brickyaxis,brickxdim,brickydim); 
					
					Rectangle ball=new Rectangle(ballposx,ballposy,10,10);
					
					if(brick.intersects(ball)){ 
						
						obj.Structure[i][j]=0;
						
						totalBricks--;
						
						playerscore+=10; 
					
						//for right side collision of ball with a brick
					
					if(ballposx+18<=brick.x || ballposx +1 >= brick.x+brick.width) {
						
						//here .x is a x dimension of a shape(rectangle brick)
						
						ballxdir=-ballxdir; // goes to the left if touched from a left side 
						
					}	
					//for left side collision of ball with the brick
					
			else {
					
						ballydir=-ballydir;  //goes upward on strike  
						
				
					}
					
					
					
					}
				}
				
			}
			
		}
		
		
		
		
				
		
		if(play) {
			
		
			ballposx +=ballxdir;  
			ballposy +=ballydir;  //here ball moves in triangle form in direction of third side of triangle 
			
		if(ballposx==0) {       
			
			ballxdir=-ballxdir;   
	
		}	
	
		if(ballposy==0) {		 
			
			ballydir=-ballydir;
		}	
			
		if(ballposx>670) {
			
			ballxdir=-ballxdir;
			
		}
		
		}
		
		repaint();  //inBuilt function to repaint again JFrame with after each action
		}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			if(playerx>=560) {
				
				playerx=570;	// current location= padX+590=695 // total x axis should include pad length(100);
			}
			else {
				
				play=true;
				playerx+=20;
				
			}
			
			
		}
		
		if(event.getKeyCode()== KeyEvent.VK_LEFT) {
			if(playerx<=5) {
				
				playerx=3;
			}
			else {
				
				play=true;
				playerx-=20;
				
			}
			
			
		}
		
		if(event.getKeyCode()==KeyEvent.VK_ENTER ) {  //Enter to continue play 
			
			//start game again 
			
			 play=true; 
			 
			 life=life+1;
			 
			 delay-=2;
			   
			if(life==2) {
				
				
				playerscore=0;
				life=0;
				
			}
			
			 playerx=310;  
			
			 ballposx=180;  	
			 ballposy=350;  
			 ballxdir=-1;  
			 ballydir=-2;	 
			
			 totalBricks=21; 
			
			 obj=new Structure();
			 obj.start();
			 repaint();
			 
			
			 
		}
		
		
		if(event.getKeyCode()==KeyEvent.VK_SPACE ) {  //space is for continue 
			
			
			play=true; 
			 
			 delay-=2;
			   
			 playerx=310;  
			
			 ballposx=180;  	
			 ballposy=350;  
			 ballxdir=-1;  
			 ballydir=-2;	 
			
			 totalBricks=21; 
			
			 obj=new Structure();
			 
			 obj.start();
			
			 repaint();
			 
			
			
		}
		
		
		
		
	}
	
	//KeyReleased and KeyTyped are not required for any functionality 
	
	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

}
