package org.harca.seg.chaves.ui;

import java.util.List;

public class ModeloDynDevolver extends ModeloDevolverTabela{
	private String[] colunas;
	private List<List<String>> lista;
	
	public ModeloDynDevolver(String[] colunaNome){
		this.setColunas(colunaNome);
		super.setColunas(colunaNome);
	}
	public ModeloDynDevolver(String[] colunaNome, List<List<String>> lista){
		this.setColunas(colunaNome);
		//this.lista = new List<List<String>>();
		setLista(lista);
		this.lista = lista;
		super.setColunas(colunaNome);
	}
	public void setColunas(String[]colunaNome){
		this.colunas = colunaNome;
	}
	
	public void addLista(List<List<String>> lista2){
		this.lista = lista2;
		
	}
	@Override
	public int getRowCount(){
	//	if(lista.size() > 0)
			return lista.size();
		//return 10;
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
		case 8: return list.get(linha).get(8);
}
		
		return null;
		//return super.getValueAt(arg0, arg1);
	}
	
}
