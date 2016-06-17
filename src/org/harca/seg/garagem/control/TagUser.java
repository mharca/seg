package org.harca.seg.garagem.control;

public class TagUser {
	String chaveMat = null;
	String nome;
	String placa = null;
	String obs = null;
	
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
	
	//**********************************************//
	
	public TagUser(String chavematr, String placa, String nome, String obs){
		this.setChaveMat(chavematr);
		this.setPlaca(placa);
		this.setNome(nome);
		this.setObs(obs);
		
	}
}
