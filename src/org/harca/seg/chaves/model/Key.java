package org.harca.seg.chaves.model;

public class Key {
	private double numero;
	private String setor;
	private String localizacao;
public Key(){
	
}

public void setNumero(double num){
	this.numero = num;
}
public void setSetor(String setor){
	this.setor = setor;
}
public void setLocalizacao(String local){
	this.localizacao = local;
}
//---------//
public double getNumero(){
	return this.numero;
}
public String getSetor(){
	return this.setor;
}
public String getLocalizacao(){
	return this.localizacao;
}
}

