package org.harca.seg.chaves.ui;

public class ModeloDynDevolver extends ModeloDevolverTabela{
	private String[] colunas;
	public ModeloDynDevolver(String[] colunaNome){
		this.setColunas(colunaNome);
		super.setColunas(colunaNome);
	}
	public void setColunas(String[]colunaNome){
		this.colunas = colunaNome;
	}
	
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
		return super.getValueAt(arg0, arg1);
	}
	
}
