package block;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gameplay play=new Gameplay();
		JFrame obj= new JFrame();  // creating an object of JFrame(frame) 
		obj.setBounds(10,10,700,600); //setting bounds of the frame(x_axis,y_axis,width,height) 
		obj.setResizable(false);		//nonResizable window 
		obj.setVisible(true);			// frame should be visible 
		//obj.setSize(1500,1000);  //setSize of JFrame
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // setting on close operation
		obj.add(play);
		
	}

}
