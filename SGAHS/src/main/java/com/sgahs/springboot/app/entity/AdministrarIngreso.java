package com.sgahs.springboot.app.entity;

import java.io.Serializable;

import com.sgahs.springboot.app.validation.MontoValido;

public class AdministrarIngreso implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Variables para monto inicial
	 * 
	 * */
	@MontoValido
	private Double montoInicial;

	/*
	 * Variables para gasto
	 * 
	 * */
	private Double montoGasto;
	
	/*
	 * Variables para prestamo
	 * 
	 * */
	private Double montoPrestamo;
	
	/*
	 * Variables para inversion
	 * 
	 * */
	private Double montoInversion;
	
	/*
	 * Variables para ahorro
	 * 
	 * */
	private Double montoAhorro;
	
	public AdministrarIngreso() {
		this.montoAhorro = 0.0;
		this.montoGasto = 0.0;
		this.montoInicial = 0.0;
		this.montoInversion = 0.0;
		this.montoPrestamo = 0.0;
	}

	public Double getMontoInicial() {
		return montoInicial;
	}

	public void setMontoInicial(Double montoInicial) {
		this.montoInicial = montoInicial;
	}

	public Double getMontoGasto() {
		return montoGasto;
	}

	public void setMontoGasto(Double montoGasto) {
		this.montoGasto = montoGasto;
	}

	public Double getMontoPrestamo() {
		return montoPrestamo;
	}

	public void setMontoPrestamo(Double montoPrestamo) {
		this.montoPrestamo = montoPrestamo;
	}

	public Double getMontoInversion() {
		return montoInversion;
	}

	public void setMontoInversion(Double montoInversion) {
		this.montoInversion = montoInversion;
	}

	public Double getMontoAhorro() {
		return montoAhorro;
	}

	public void setMontoAhorro(Double montoAhorro) {
		this.montoAhorro = montoAhorro;
	}

}
