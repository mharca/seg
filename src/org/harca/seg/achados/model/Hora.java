package org.harca.seg.achados.model;

public class Hora {
	private String hora, minuto;
	
	public Hora(String hora){
		this.hora = hora.substring(0, 2);
		this.minuto = hora.substring(3, 5);
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getMinuto() {
		return minuto;
	}

	public void setMinuto(String minuto) {
		this.minuto = minuto;
	}
	
}
