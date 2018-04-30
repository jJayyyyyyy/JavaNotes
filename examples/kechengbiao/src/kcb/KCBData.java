package kcb;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class KCBData implements TableModel {
	private String[] titleList = {"Mon", "Tue", "Wed", "Thur", "Fri"};
	private String[][] courseMatrix = new String[8][5];
	
	public KCBData() {
		for ( int i = 0; i < courseMatrix.length; i++ ) {
			for( int j = 0; j < courseMatrix[i].length; j++ ) {
				courseMatrix[i][j] = "";
			}
		}
	}
	
	@Override
	public void addTableModelListener(TableModelListener arg0) {
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getColumnName(int col) {
		return this.titleList[col];
	}

	@Override
	public int getRowCount() {
		return 8;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return courseMatrix[row][col];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
	}

	@Override
	public void setValueAt(Object courseName, int row, int col) {
		courseMatrix[row][col] = (String)courseName;
	}
}
