package org.harca.seg.chaves.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeloDevolverTabela extends AbstractTableModel{
	private String[] colunas = {"Nome", "Numero", "Local", "Data retirado", "Hora retirado", "Torre", "Andar"};
	List<List<String>> list;
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}
	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
			setLista(list);
			return list.size();
		//else return 10;
	}
	public void  setLista(List<List<String>> lista){
		this.list = lista;
	}
	@Override
	public Object getValueAt(int linha, int coluna) {
		// TODO Auto-generated method stub
		
		switch(coluna){
			
				case 0: return list.get(linha).get(0);
				case 1: return list.get(linha).get(1);
				case 2: return list.get(linha).get(2);
				case 3: return list.get(linha).get(3);
				case 4: return list.get(linha).get(4);
				case 5: return list.get(linha).get(5);
				case 6: return list.get(linha).get(6);
				case 7: return list.get(linha).get(7);
		}
			
		return null;
	}
	public String getColumnName(int c)
	{ return colunas[c];  
	
	}
}

