package org.harca.seg.achados.ui;
import org.harca.seg.achados.control.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel{

	private String[] colunas = {"ID", "Objeto","Descricao", "Local", "Data encontrado", "Hora encontrado", "Inspetor", "Data recebido", "Hora Recebido","Escaninho"}; 
	private Control control;
	List<List<String>> lista = new ArrayList<>();
	
	public ModeloTabela(List<String> lista1){
		control = new Control();
		System.out.println("Criando lista lista 3");
		this.limpar();
		//lista = new ArrayList<>();
		lista.add(lista1);
			
	}
	
	public void addLista(List<List<String>> lista2){
		this.lista = lista2;
		
	}

	public ModeloTabela() {
	
		control = new Control();
		System.out.println("Criando lista");
		lista = new ArrayList<>();
		this.limpar();
		lista = control.selectBasico();
		
		//this.lista.add(control.selectBasico());
		
		System.out.println(lista.get(0).get(1));
	}
	
	public void limpar()
	{
		for (int i=1; i< this.getRowCount();i++)
			this.fireTableRowsDeleted(i, this.getRowCount());
		this.fireTableRowsUpdated(1, this.getRowCount());
	}
	
	public void addRow(List<List<String>> lista1){
		
		this.limpar();
		
		for(int i=0; i< lista.size(); i++){
			lista.add(lista1.get(i));
			this.addRow(lista1);
			System.out.println(lista1);
			this.fireTableDataChanged();
		}
		
	}
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
	public Object getValueAt(int linha, int coluna) {
		// TODO Auto-generated method stub
		
		switch(coluna){
		
			
			case 0: return lista.get(linha).get(0);
			case 1: return lista.get(linha).get(1);
			case 2: return lista.get(linha).get(2);
			case 3: return lista.get(linha).get(3);
			case 4: return lista.get(linha).get(4);
			case 5: return lista.get(linha).get(5);
			case 6: return lista.get(linha).get(6);
			case 7: return lista.get(linha).get(7);
			case 8: return lista.get(linha).get(8);
			case 9: return lista.get(linha).get(9);

			
		}
		
		return null;
	}
	
	public String getColumnName(int c)
	{ return colunas[c];  }
	
	
}
