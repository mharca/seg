package org.harca.seg.chaves.control;

public class Key {
	private int numero;
	private String setor;
	private String localizacao;
	private String cor;
	private String torre;
	private String andar;
	private int id;
	
public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

public Key(){
	
}

public String getCor() {
	return cor;
}

public void setCor(String cor) {
	this.cor = cor;
}

public String getTorre() {
	return torre;
}

public void setTorre(String torre) {
	this.torre = torre;
}

public String getAndar() {
	return andar;
}

public void setAndar(String andar) {
	this.andar = andar;
}

public void setNumero(int num){
	this.numero = num;
}
public void setSetor(String setor){
	this.setor = setor;
}
public void setLocalizacao(String local){
	this.localizacao = local;
}
//---------//
public int getNumero(){
	return this.numero;
}
public String getSetor(){
	return this.setor;
}
public String getLocalizacao(){
	return this.localizacao;
}
}

