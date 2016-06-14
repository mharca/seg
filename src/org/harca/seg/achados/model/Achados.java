package org.harca.seg.achados.model;

public class Achados {
	private int id;
	private String objetoNome, objetoDescricao, lugarEncontrado, encontrouMatricula;
	private Calendario dataEncontrado, dataRecebido,dataRetirou;
	private String matriculaISI, retirouMatricula, escaninho;
	private Hora horaEncontrado, horaRecebido;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getObjetoNome() {
		return objetoNome;
	}
	public void setObjetoNome(String objetoNome) {
		this.objetoNome = objetoNome;
	}
	public String getObjetoDescricao() {
		return objetoDescricao;
	}
	public void setObjetoDescricao(String objetoDescricao) {
		this.objetoDescricao = objetoDescricao;
	}
	public String getLugarEncontrado() {
		return lugarEncontrado;
	}
	public void setLugarEncontrado(String lugarEncontrado) {
		this.lugarEncontrado = lugarEncontrado;
	}
	public String getEncontrouMatricula() {
		return encontrouMatricula;
	}
	public void setEncontrouMatricula(String encontrouMatricula) {
		this.encontrouMatricula = encontrouMatricula;
	}
	public Calendario getDataEncontrado() {
		return dataEncontrado;
	}
	public void setDataEncontrado(Calendario dataEncontrado) {
		this.dataEncontrado = dataEncontrado;
	}
	public Calendario getDataRecebido() {
		return dataRecebido;
	}
	public void setDataRecebido(Calendario dataRecebido) {
		this.dataRecebido = dataRecebido;
	}
	public Calendario getDataRetirou() {
		return dataRetirou;
	}
	public void setDataRetirou(Calendario dataRetirou) {
		this.dataRetirou = dataRetirou;
	}
	public String getMatriculaISI() {
		return matriculaISI;
	}
	public void setMatriculaISI(String matriculaISI) {
		this.matriculaISI = matriculaISI;
	}
	public String getRetirouMatricula() {
		return retirouMatricula;
	}
	public void setRetirouMatricula(String retirouMatricula) {
		this.retirouMatricula = retirouMatricula;
	}
	public String getEscaninho() {
		return escaninho;
	}
	public void setEscaninho(String escaninho) {
		this.escaninho = escaninho;
	}
	public Hora getHoraEncontrado() {
		return horaEncontrado;
	}
	public void setHoraEncontrado(Hora horaEncontrado) {
		this.horaEncontrado = horaEncontrado;
	}
	public Hora getHoraRecebido() {
		return horaRecebido;
	}
	public void setHoraRecebido(Hora horaRecebido) {
		this.horaRecebido = horaRecebido;
	}
	
}
