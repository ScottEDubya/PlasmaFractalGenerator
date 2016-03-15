/*
 *Program: Plasma Fractal Generator, PlasmaFractalGenerator.java
 *Contributor(s): Scott Williams
 *Created on: 10/11/15
 *Last Modified On: 3/15/16
 */

package plasmafractalgenerator;

import java.awt.*;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class PlasmaFractalGenerator extends JComponent{
	
	Graphics g;

	public static void main(String[] args) {
		
		//frame initialization
		JFrame frame = new JFrame();
		frame.setSize(375, 395);
		frame.setTitle("Plasma Fractal Image");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//instance of PlasmaFractalGenerator
		PlasmaFractalGenerator p = new PlasmaFractalGenerator();
		
		//add to the frame
		frame.add(p);
	}
	
	/**
	 * Paint component overrides the JComponent method.
	 * Starts by generating a random color for each corner of the frame,
	 * followed by a call to the recursive function to find the color of
	 * the rest of the pixels. 
	 * @param accepts Graphics to modify
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
	
		//ESTABLISHES COLORS FOR THE CORNER POINTS
		Random gen = new Random();
		int r1 = gen.nextInt(256);
		int r2 = gen.nextInt(256);
		int r3 = gen.nextInt(256);
		Color c = new Color(r1,r2,r3);
		r1 = gen.nextInt(256);
		r2 = gen.nextInt(256);
		r3 = gen.nextInt(256);
		Color c2 = new Color(r1,r2,r3);
		r1 = gen.nextInt(256);
		r2 = gen.nextInt(256);
		r3 = gen.nextInt(256);
		Color c3 = new Color(r1,r2,r3);
		r1 = gen.nextInt(256);
		r2 = gen.nextInt(256);
		r3 = gen.nextInt(256);
		Color c4 = new Color(r1,r2,r3);

		
		//initial call to recursive function
		DivideGrid(g2, 0, 0, getWidth(), getHeight() , c, c2, c3, c4);
		
	}
	
	/**
	 * Calculates the average color of each edge based on the color of the
	 * 4 points passed in. Then recursively calls the 4 inner grids from the
	 * current one. Recursion ends when the grid is smaller than 2x2.
	 * @param g graphics
	 * @param x current x value
	 * @param y current y value
	 * @param width of the current grid
	 * @param height of the current grid
	 * @param c1 color of first corner
	 * @param c2 color of second corner
	 * @param c3 color of third corner
	 * @param c4 color of fourth corner
	 */
	void DivideGrid(Graphics g, float x, float y, float width, float height, Color c1, Color c2, Color c3, Color c4)
	{
		float newWidth = width / 2;
		float newHeight = height / 2;
		
		
		if (width > 2 || height > 2)
		{	
			//finds average color for new points
			Color Edge1 = (ComputeColor(c1,c2));
			Color Edge2 = (ComputeColor(c2,c3));
			Color Edge3 = (ComputeColor(c3,c4));
			Color Edge4 = (ComputeColor(c4,c1));
			
			Color Middle = ComputeColor(Edge1,Edge3);
			
			//Recursion to divide into 4 new grids			
			DivideGrid(g, x, y, newWidth, newHeight, c1, Edge1, Middle, Edge4);
			DivideGrid(g, x + newWidth, y, newWidth, newHeight, Edge1, c2, Edge2, Middle);
			DivideGrid(g, x + newWidth, y + newHeight, newWidth, newHeight, Middle, Edge2, c3, Edge3);
			DivideGrid(g, x, y + newHeight, newWidth, newHeight, Edge4, Middle, Edge3, c4);
		}
		else //base case
		{
			g.setColor(ComputeColor(c1,c2,c3,c4));
			//I MADE MY OVAL 5x5 BECAUSE THERE WAS WHITESPACE WITH 1x1 OVALS
			g.drawOval((int)x, (int)y, 5, 5);
		}
	}

	
	/**
	 * Function takes the two colors input, finds the average and returns a 
	 * new color that is the average of the two.
	 * @param c1 first color
	 * @param c2 second color
	 * @return a new color that is the average of the two parameters
	 */
	Color ComputeColor(Color c1, Color c2)
	{		
		int Red = 0;
		int Green = 0;
		int Blue = 0;
		
		Red = (c1.getRed() + c2.getRed())/2;
		Green = (c1.getGreen() + c2.getGreen())/2;
		Blue = (c1.getBlue() + c2.getBlue())/2;
		
		
		return new Color(Red, Green, Blue);
	}

	/**
	 * Computes the color of the midpoint based on the 4 corner colors
	 * @param c1 color 1
	 * @param c2 color 2
	 * @param c3 color 3
	 * @param c4 color 4
	 * @return the new color that is the average of the other 4
	 */
	Color ComputeColor(Color c1, Color c2, Color c3, Color c4)
	{		
		int Red = 0;
		int Green = 0;
		int Blue = 0;
		
		Red = (c1.getRed() + c2.getRed() + c3.getRed() + c4.getRed())/4;
		Green = (c1.getGreen() + c2.getGreen() + c3.getGreen() + c4.getGreen())/4;
		Blue = (c1.getBlue() + c2.getBlue() + c3.getBlue() + c4.getBlue())/4;
		
		
		return new Color(Red, Green, Blue);
	}
	
}


	

