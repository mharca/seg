package org.harca.seg.garagem.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.harca.seg.garagem.control.Control;

public class TagTabelaModelo extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String[] colunas = {"ID","Nome","Chave/Matricula","Placa","Data","Observacao"};
	List<List<String>> l2 = new ArrayList<>();
	
	public TagTabelaModelo(List<String> l1){
	//	Control c = new Control();
		this.limpar();
		//if(!l2.isEmpty())
			l2.add(l1);
		
	}
	public TagTabelaModelo() {
		Control c = new Control();
		l2 = new ArrayList<>();
		
		l2 = c.selectTag();
	}
	public TagTabelaModelo(String filtro){
		Control c = new Control();
		l2 = new ArrayList<>();
		l2 = c.filtrarTag(filtro);
	}
	/*
	public TagTabelaModelo( List< List<String>> l2 ) {
		//Control c = new Control();
	//	this.l2 = new ArrayList<>();
		
		this.l2 = l2;
	}
	*/
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return l2.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// TODO Auto-generated method stub
		switch(coluna){
		
		
		case 0: return l2.get(linha).get(0);
		case 1: return l2.get(linha).get(3);
		case 2: return l2.get(linha).get(2);
		case 3: return l2.get(linha).get(1);
		case 4: return l2.get(linha).get(4);
		case 5: return l2.get(linha).get(5);
		}
		return null;
	}
	public String getColumnName(int c)
	{ return colunas[c];  }
	
	
	public void limpar()
	{
		for (int i=1; i< this.getRowCount();i++)
			this.fireTableRowsDeleted(i, this.getRowCount());
		this.fireTableRowsUpdated(1, this.getRowCount());
	}

	public void addRow(List<List<String>> lista1){
		this.limpar();
		
		for(int i=0; i< l2.size(); i++){
			l2.add(lista1.get(i));
			this.addRow(lista1);
			System.out.println(lista1);
			this.fireTableDataChanged();
		}
	
}
}
