package controller;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import model.Cell;
import view.Gameboard;
import view.View;

/*
 * https://testdrive-archive.azurewebsites.net/Performance/Minesweeper/Default.html
 * */

public class Controller {
	private Gameboard gameboard;
	private View view;
	private MouseClickHandler mouse;
	private JFrame frame;
	private static int cntCol = 20;
	private static int cntRow = 20;
	
	public class MouseClickHandler extends MouseHandler{

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX() - 3;
			int y = e.getY() - 26;
			int col = x / View.GRID_SIZE;
			int row = y / View.GRID_SIZE;
			System.out.printf("%d, %d\n", row, col);
			Cell cell = gameboard.getCell(row, col);
			
			/* https://blog.csdn.net/changqing5818/article/details/49497595
			 * */
			if( e.getButton() == MouseEvent.BUTTON1 && cell.isFlag == false ) {
				/* TODO : how to directly get the X and Y coordinates from the current component?
				 * rather than from the origin point of the whole frame
				 * 3 and 26 is the offset from the whole frame
				 * */
				if( cell.isMine ) {
					gameover();
				}else {
					if( cell.neiborMine == 0 ) {
						gameboard.floodFill(row, col);
					}else {
						cell.revealed = true;
					}
				}
			}else if( e.getButton() == MouseEvent.BUTTON3 ) {
//				System.out.println("right click");
				if( cell.revealed == false ) {
					cell.isFlag = !cell.isFlag;
				}
				

			}
			
			frame.repaint();
		}
		
		public void MousePressed(MouseEvent e) {
			/* http://www.aichengxu.com/java/2778900.htm
			 * https://blog.csdn.net/methods2011/article/details/12444003
			 * */
		}
	}
	
	public void gameover() {
		for( int i = 0; i < cntRow; i++ ) {
			for (int j = 0; j < cntCol; j++ ) {
				Cell cell = gameboard.getCell(i, j);
				if( cell.isFlag == false ) {
					cell.revealed = true;
				}else {
					if( cell.isMine ) {
						cell.isCorrectFlag = true;
					}else {
						cell.isCorrectFlag = false;
					}
				}
				
			}
		}
	}
	
	public Controller() {
		gameboard = new Gameboard(cntRow, cntCol);
		view = new View(gameboard);
		mouse = new MouseClickHandler();
		frame = new JFrame();
	}
	
	public static void main(String[] args) {
		Controller c = new Controller();
		c.frame = new JFrame();
		
		c.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.frame.setResizable(false);
		c.frame.setTitle("Mine Sweeper");
		c.frame.add(c.view);
		c.frame.addMouseListener(c.mouse);
		c.frame.pack();
		c.frame.setVisible(true);
		System.out.println("done");
	}

}
