package org.harca.seg.garagem.ui;
import javax.swing.table.AbstractTableModel;

public class TabelaModelo extends AbstractTableModel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] colunas = {"Visitante", "Placa", "Visitado", "Cor", "Modelo", "Correio"};
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
