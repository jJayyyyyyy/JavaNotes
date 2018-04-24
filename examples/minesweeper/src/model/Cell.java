package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Cell {
	public boolean isMine = false;
	public boolean revealed = false;
	public boolean isFlag = false;
	public boolean isCorrectFlag = false;
	public int neiborMine = 0;
	
	
	public void draw(Graphics g, int row, int col, int grid_size) {
		int y = row * grid_size;
		int x = col * grid_size;

		if( this.revealed ) {
			g.setColor(Color.lightGray);
			g.fillRect(x, y, grid_size, grid_size);
			g.setColor(Color.black);
			if ( isMine ) {
				Circle c = new Circle(x, y, grid_size);
				c.draw(g);
			}else if( neiborMine != 0 ){
				/*
				 * https://blog.csdn.net/zixiaomuwu/article/details/51068698
				 * https://zhidao.baidu.com/question/72726636.html
				 * */
				
				g.setFont(new Font("Consolas", Font.BOLD, 20));
				g.drawString(""+neiborMine, x + grid_size / 2 - 4, y + grid_size / 2 + 8);
			}
		}else {
			g.setFont(new Font("Consolas", Font.BOLD, 20));
			if( this.isFlag ) {
				// 在 floodFill时会被覆盖
				// 左键点击flag，还是会生效
				// gameover 之后，isCorrectFlag 标识正确的flag,
				// 正确的还是用o，错误的用x
				g.setColor(Color.red);
				g.drawString("o", x + grid_size / 2 - 4, y + grid_size / 2 + 8);
				g.setColor(Color.black);
			}else {

			}
		}

		g.drawRect(x, y, grid_size, grid_size);
		
	}
}

