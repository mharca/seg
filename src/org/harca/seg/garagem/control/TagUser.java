package org.harca.seg.garagem.control;

public class TagUser {
	String chaveMat = null;
	String nome;
	String placa = null;
	String obs = null;
	String data = null;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getChaveMat() {
		return chaveMat;
	}
	public void setChaveMat(String chaveMat) {
		this.chaveMat = chaveMat;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	//**********************************************//
	
	
	public TagUser(String chavematr, String placa, String nome, String data, String obs){
		this.setChaveMat(chavematr);
		this.setPlaca(placa);
		this.setNome(nome);
		this.setObs(obs);
		this.setData(data);
		
	}
}
