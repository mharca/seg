package org.harca.seg.chaves.ui;

import javax.swing.table.AbstractTableModel;

public class ModeloDevolverTabela extends AbstractTableModel{
	private String[] colunas = {"Nome", "Numero", "Local", "Data", "Hora", "Torre", "Andar"};
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	public String getColumnName(int c)
	{ return colunas[c];  
	
	}
}

