package block;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;		// argument for action listener 
import java.awt.event.ActionListener;  // this is for doing action on ball after keyListening 
import java.awt.event.KeyEvent;			// argument for keyListener methods 
import java.awt.event.KeyListener;   // this is for detecting arrow key tapped by a user 

import javax.swing.JPanel;    // adding JPanel class from Swing 
import javax.swing.Timer;		//tracks time

@SuppressWarnings("serial")  // GamePlay class giving error for serialization of class so,Supper warning
public class Gameplay extends JPanel implements KeyListener,ActionListener{
	
	
	private boolean play=false;  //game should not start itself
	
	private int score= 0;  //starting score of player 
	
	private int playerx=310; //player initial X axis value 
	
	private int ballposx=120; //ball initial value on x axis 	
	private int ballposy=350; //ball initial value on y axis 
	private int ballxdir=-1;  //ball direction on x axis
	private int ballydir=-2;	//ball direction on y axis 
	
	private int totalBricks=21; // 7*3 bricks dimensions 
	
	private Timer timer; //adding timer to track time 
	private int delay=6;  // 5sec delay time 
	
	
	//all of the methods below are abstract in nature from the KeyListner,ActionListner interfaces
	
	
	
	
	//--------------------------------------------------------------------------------------------------------
	
	//creating constructor so that values initialize itself when Gameplay object called from Main class  
	
	public Gameplay(){
		
		addKeyListener(this); 
		setFocusable(true);   //component get focus on JPanel	
		setFocusTraversalKeysEnabled(false);  // tab,shift,ctrl default values are not allowed in our app(we are overriding this method inside keyLitener)
		timer= new Timer(delay,this);   //returns intValue 
		timer.start();  //start time 
	}
	
	//---------------------------------------------------------------------------------------------------------
	
	
	
	public void paint(Graphics g) {
		
		Graphics2D ob=(Graphics2D) g;
		//background_paint  
		
		g.setColor(Color.BLACK);
		
		g.fillRect(1,1,697,592);  // filling screen with black color,using same dimensions that we have defined in Main class for JFrame setbounds 
		
		//borders 
		g.setColor(Color.ORANGE);
		
		g.fillRect(0,0,8,592);
		g.fillRect(0,0,700,8);
		g.fillRect(695,0,8,592);
		
		//pad 
		ob.setColor(Color.YELLOW);
		ob.fillRect(playerx,550,125,10);  //playerx value is variable because it changes every time with the key entered by the user which is defined in (keyPressed) function below   
		
		//ball 
		ob.setColor(Color.YELLOW);
		ob.fillOval(ballposx,ballposy,15,15);  //ballposx,ballposy are varible because changes with player action but dimensions of ball are 20x20
		
		g.dispose();
		
		
	}
	
	
   	@Override
	public void actionPerformed(ActionEvent event) {
		timer.start();  //timer used to set speed of the ball
	
		if(new Rectangle(ballposx,ballposy,15,15).intersects(playerx,550,100,8)) {
			
			ballydir=-ballydir;
			
		}
		
		//if(ballposx>600) {   HERE code to stop player run if goes DownSide}
		
		
		if(play) {
			
			//note : origin lies on Upper left corner of the screen (Graph is downWard in direction) 
			// x increases toward right and y increases toward downSide 
			
			ballposx +=ballxdir;  
			ballposy +=ballydir;  //here ball moves in triangle form in direction of third side of triangle 
			
		if(ballposx==0) {       // if ball touches left side than move ball to upside
			
			ballxdir=-ballxdir;   
	
		}	
	
		if(ballposy==0) {		//if 
			
			ballydir=-ballydir;
		}	
			
		if(ballposx>670) {
			
			ballxdir=-ballxdir;
			
		}
		
		}
		
		repaint();  //inBuilt function to repaint again JFrame with after each action of a player 
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			if(playerx>=595) {
				
				playerx=595;	// current location= padX+595=695 // total x axis should include pad length(100);
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
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

}
