package org.harca.seg.chaves.ui;

import java.util.List;

public class ModeloDynDevolver extends ModeloDevolverTabela{
	private String[] colunas;
	private List<List<String>> lista;
	
	public ModeloDynDevolver(String[] colunaNome){
		this.setColunas(colunaNome);
		super.setColunas(colunaNome);
	}
	public void setColunas(String[]colunaNome){
		this.colunas = colunaNome;
	}
	
	public void addLista(List<List<String>> lista2){
		this.lista = lista2;
		
	}
	
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
		return super.getValueAt(arg0, arg1);
	}
	
}
