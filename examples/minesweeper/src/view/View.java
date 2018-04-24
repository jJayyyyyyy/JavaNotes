package view;

import java.awt.Dimension;
import java.awt.Graphics;

//import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Cell;

public class View extends JPanel {
	private static final long serialVersionUID = 2118299654730994785L;
	public static final int GRID_SIZE = 32;
	private Gameboard gameboard;
	
	public View(Gameboard gameboard) {
		this.gameboard = gameboard;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for( int i = 0; i < gameboard.getRow(); i++ ) {
			for( int j = 0; j < gameboard.getCol(); j++ ) {
				Cell cell = gameboard.getCell(i, j);
				if( cell != null ) {
					cell.draw(g, i, j, GRID_SIZE);
				}
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(gameboard.getRow()*GRID_SIZE+1, gameboard.getCol()*GRID_SIZE+1);
	}
	
}
