package com.sgahs.springboot.app.dto;

import java.util.Date;

public class DtoGasto {

	private Long id;

	private Date fechaCreacion;

	private Double monto;

	private String descripcion;

	private String nbGastoRecurrente;

	private String nbTipoMovimiento;
	
	public DtoGasto(Date fechaCreacion, Double monto, String descripcion, String nbGastoRecurrente,
			String nbTipoMovimiento) {
		this.fechaCreacion = fechaCreacion;
		this.monto = monto;
		this.descripcion = descripcion;
		this.nbGastoRecurrente = nbGastoRecurrente;
		this.nbTipoMovimiento = nbTipoMovimiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNbGastoRecurrente() {
		return nbGastoRecurrente;
	}

	public void setNbGastoRecurrente(String nbGastoRecurrente) {
		this.nbGastoRecurrente = nbGastoRecurrente;
	}

	public String getNbTipoMovimiento() {
		return nbTipoMovimiento;
	}

	public void setNbTipoMovimiento(String nbTipoMovimiento) {
		this.nbTipoMovimiento = nbTipoMovimiento;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}
}
