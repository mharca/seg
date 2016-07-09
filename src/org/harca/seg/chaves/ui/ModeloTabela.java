package org.harca.seg.chaves.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.harca.seg.chaves.control.Key;
import org.harca.seg.chaves.control.Controle;

public class ModeloTabela extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] colunas = {"Numero", "Localizacao", "Andar", "Setor", "Torre","Cor","ID"};
	Controle c;
	List<Key> listaChaves;
	public ModeloTabela() {
		this.c = new Controle();
		//limpar();
		listaChaves = new ArrayList<Key>();
		listaChaves = c.selectAll();
		
		
	}
	public ModeloTabela(List<Key>lista){
		this.c = new Controle(); // alterar depois
		//limpar();
		listaChaves = new ArrayList<Key>();
		listaChaves = lista;


	}
	
	public ModeloTabela(String palavra){
		this.c = new Controle(); // alterar depois 
		listaChaves = new ArrayList<Key>();
		listaChaves = c.selectByWord(palavra);
		
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;

	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaChaves.toArray().length;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// TODO Auto-generated method stub
		//listaChaves = c.selectAll();
		// int count=0;
	
			switch(coluna){
				
				case 0: return listaChaves.get(linha).getNumero();
				case 1: return listaChaves.get(linha).getLocalizacao();
				case 2: return listaChaves.get(linha).getAndar();
				case 3: return listaChaves.get(linha).getSetor();
				case 4: return listaChaves.get(linha).getTorre();
				case 5: return listaChaves.get(linha).getCor();
				case 6: return listaChaves.get(linha).getId();
				
			}
		
		return null;
	}
	public String getColumnName(int c)
	{ return colunas[c];  
	
	}
	public void limpar()
	{
		for (int i=1; i< this.getRowCount();i++)
			this.fireTableRowsDeleted(i, this.getRowCount());
		this.fireTableRowsUpdated(1, this.getRowCount());
		this.fireTableDataChanged();

	}
}
