
package com.springboot.bbva.app.response;

public class Restringido {

	private String idCliente;
	private String nombre;
	private String sexo;
	private char segmento;
	private String cuenta;

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public char getSegmento() {
		return segmento;
	}

	public void setSegmento(char segmento) {
		this.segmento = segmento;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

}
