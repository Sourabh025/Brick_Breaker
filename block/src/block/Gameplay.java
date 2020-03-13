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

import javax.swing.JPanel;    // adding JPanel class from Swing 
import javax.swing.Timer;		//tracks time

@SuppressWarnings("serial")  // GamePlay class giving error for serialization of class so,Supress warning
public class Gameplay extends JPanel implements KeyListener,ActionListener{
	
	
	private boolean play=false; 
	
	private int score= 0;  //starting score of player 
	
	private int playerx=310; //player initial X axis value 
	
	private int ballposx=120; //ball initial value on x axis 	
	private int ballposy=350; //ball initial value on y axis 
	private int ballxdir=-1;  //ball direction on x axis
	private int ballydir=-2;	//ball direction on y axis 
	
	private int totalBricks=21;   // 7*3 bricks dimensions 
	
	private Timer timer; //adding timer to track time 
	private int delay=5;  // 5sec delay time 
	
	private int playerscore;
	
	private Map obj;
	
	//all of the methods below are abstract in nature from the KeyListner,ActionListner interfaces
	
	
	
	
	//--------------------------------------------------------------------------------------------------------
	
	//creating constructor so that values initialize itself when GamePlay object called from Main class  
	
	public Gameplay(){
		
		addKeyListener(this); 
		setFocusable(true);   //component get focus on JPanel
		obj=new Map(3,7);
		
		setFocusTraversalKeysEnabled(false);  // tab,shift, ctrl default values are not allowed in our app(we are overriding this method inside keyListener)
		timer= new Timer(delay,this);   //returns intValue 
		timer.start();  //start time 
	}
	
	//---------------------------------------------------------------------------------------------------------
	
	
	//note paint is a inbuilt function of an awt package so no need to call it from a main or constructor 
	
	public void paint(Graphics g) {
		
		Graphics2D ob=(Graphics2D) g;
		
		//background_paint  
		
		g.setColor(Color.GREEN);
		
		g.fillRect(1,1,697,592);  // filling screen with black color,using same dimensions that we have defined in Main class for JFrame setbounds 
		
		//borders 
		g.setColor(Color.YELLOW);
		
		g.fillRect(0,0,8,592);
		
		g.fillRect(0,0,700,8);
		
		g.fillRect(695,0,8,592);
		
		//pad 
		ob.setColor(Color.WHITE);
		ob.fillRect(playerx,550,125,10);  //playerx value is variable because it changes every time with the key entered by the user which is defined in (keyPressed) function below   
		
		//ball 
		ob.setColor(Color.WHITE);
		
		ob.fillOval(ballposx,ballposy,15,15);  //ballposx,ballposy are varible because changes with player action but dimensions of ball are 20x20
		
		//Brick map 2d creating  
		obj.draw((Graphics2D)g);
		
		
		//score 
		ob.setColor(Color.BLUE);
		ob.setFont(new Font("arvil",Font.CENTER_BASELINE,25));
		ob.drawString("" +playerscore,590,40);
		
		
		
		g.dispose();
		
		
	}
	
	
   	@Override
	public void actionPerformed(ActionEvent event) {
		timer.start();  //timer used to set speed of the ball
		
		//detects collision between ball and the pad 
		if(new Rectangle(ballposx,ballposy,15,15).intersects(playerx,550,125,10)) {
			
			ballydir=-ballydir;
			
		}
		//detects collision between brick and the ball
		for(int i=0;i<obj.map.length;i++){
			
			for(int j=0;j<obj.map[0].length;j++){
				
				if(obj.map[i][j]>0) {
					
					int brickxaxis= j * obj.brickwidth + 80; 
					int brickyaxis= i* obj.brickheight +50;
					int brickxdim=obj.brickwidth;
					int brickydim=obj.brickheight;
				
					Rectangle brick= new Rectangle(brickxaxis,brickyaxis,brickxdim,brickydim); 
					Rectangle ball=new Rectangle(ballposx,ballposy,15,15);
					
					if(brick.intersects(ball)){ 
						obj.map[i][j]=0;
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
		
		
		
		
		//if(ballposx>600) {   HERE code to stop player run if goes DownSide}
		
		
		if(play) {
			
			//origin lies on Upper left corner of the screen (Graph is downWard in direction) 
			
			// x increases toward right and y increases downSide 
			
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
