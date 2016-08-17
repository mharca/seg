package org.harca.seg.achados.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.harca.seg.achados.control.Control;

public class ModeloTabelaDevolvido extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] colunas = {"ID", "Objeto","Descricao", "Local", "Encontrado", "Devolvido"};
	private Control control;
	List<List<String>> lista = new ArrayList<>();
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public Object getValueAt(int linha, int col) {
		// TODO Auto-generated method stub
		switch(col){
		case 0: return lista.get(linha).get(0);
		case 1: return lista.get(linha).get(1);
		case 2: return lista.get(linha).get(2);
		case 3: return lista.get(linha).get(3);
		case 4: return lista.get(linha).get(4);
		case 5: return lista.get(linha).get(5);
		case 6: return lista.get(linha).get(6);
		}
		return null;
	}

}
