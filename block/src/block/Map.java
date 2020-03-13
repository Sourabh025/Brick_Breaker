package block;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Map {
	
	int map[][];
	int row,col;
	
	public  int brickwidth;
	public  int brickheight;
	
	public Map(int row,int col) {
		
		map = new  int[row][col];
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				
				map[i][j]=1;
				
				
			}
			
			
		}
		brickwidth=560/col;
		brickheight=150/row;

	
	}
	
	// called from a GamePlay class paint function
	public void draw(Graphics2D g) 
	{
		for(int i=0;i<map.length;i++) {
			
			for(int j=0;j<map[0].length;j++) {
		
				if(map[i][j]>0) {
					
					g.setColor(Color.WHITE);
					g.fillRect(j * brickwidth + 80, i * brickheight + 50, brickwidth, brickheight);
					
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickwidth + 80, i * brickheight + 50, brickwidth, brickheight);	
				}
				
				
				
				
			}
			
			
		}
		
		
		
		
	}
	
	

public void set(int x) {
	
	//nothing used
	
}	
	

	
	

}