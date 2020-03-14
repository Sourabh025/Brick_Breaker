package block;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.swing.JFrame;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args){
		
		Gameplay play=new Gameplay();
		JFrame obj= new JFrame("Bad Bricks");  // creating an object of JFrame(frame) 
		obj.setBounds(10,10,700,600); //setting bounds of the frame(x_axis,y_axis,width,height) 
		obj.setResizable(false);		//nonResizable window 
		obj.setVisible(true);			// frame should be visible 
		//obj.setSize(1500,1000);  //setSize of JFrame
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // setting on close operation
		obj.add(play);  //adding object of our GamePlay class to the JFrame object
		
		
		
		try {
			
			AudioInputStream music=AudioSystem.getAudioInputStream(new File("/home/sourabh/Downloads/melodyloops-preview-stranger-runner-8m30s.wav"));
			Clip clip=AudioSystem.getClip();
			clip.open(music);  //open is static function so need call with object 
			clip.start(); //start music 
			
		} 
		
		catch(Exception e) {
			
			System.out.println(e);
			
			
		}
		
		
	}

}
