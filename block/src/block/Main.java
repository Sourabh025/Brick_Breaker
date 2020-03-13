package block;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		Gameplay play=new Gameplay();
		JFrame obj= new JFrame("Bad Bricks");  // creating an object of JFrame(frame) 
		obj.setBounds(10,10,700,600); //setting bounds of the frame(x_axis,y_axis,width,height) 
		obj.setResizable(false);		//nonResizable window 
		obj.setVisible(true);			// frame should be visible 
		//obj.setSize(1500,1000);  //setSize of JFrame
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // setting on close operation
		obj.add(play);  //adding object of our GamePlay class to the JFrame object  
		
	}

}
