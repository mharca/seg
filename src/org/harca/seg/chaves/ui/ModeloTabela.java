package org.harca.seg.chaves.ui;

import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] colunas = {"Numero", "Localizacao", "Andar", "Setor", "Torre"};

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;

	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 10;
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
