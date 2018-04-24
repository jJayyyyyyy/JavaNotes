package model;

import java.awt.Color;
import java.awt.Graphics;

public class Circle implements Shape{
	private int x;
	private int y;
	private int xlen, ylen;
	private int offset;
	
	public Circle(int x, int y, int grid_size)
	{
		this.x = x;
		this.y = y;
		this.offset = grid_size / 3;
		this.xlen = grid_size / 3;
		this.ylen = grid_size / 3;
	}
	
	@Override
	public void draw(Graphics g) {
//		g.drawOval(x + offset, y + offset, xlen, ylen);
		g.setColor(Color.darkGray);
		g.fillOval(x + offset, y + offset, xlen, ylen);
		g.setColor(Color.black);	
	}

	
	
}
