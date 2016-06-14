package org.harca.seg.achados.model;

public class Calendario {
	public String dia,mes,ano;
	
	public Calendario(String c){
		this.setData(c);
	}
	public void setData(String data){
		this.dia = data.substring(0, 2);
		this.mes = data.substring(3, 5);
		this.ano = data.substring(6, 10);
	}
	public String getDia(){
		return dia;
	}
	public String getMes(){
		return mes;
	}
	public String getAno(){
		return ano;
	}
	public String getFormatedMonth(String mes){
		String mesFormatado = mes;
		if (Integer.parseInt(mes) < 10){
			 mesFormatado = "0"+mes;
		}
		return mesFormatado;
	}
}
