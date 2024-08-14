package com.sgahs.springboot.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.sgahs.springboot.app.validation.MontoValido;

@Entity
@Table(name = "prestamos")
public class Prestamo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String folio;

	@NotNull
	@Column(name = "monto_prestado")
	private Double montoPrestado;

	@NotEmpty
	private String descripcion;

	@NotNull
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaCreacion;

	@Column(name = "monto_pagado")
	private Double montoPagado;

	@Column(name = "cd_estatus")
	private Integer cdEstatus;

	public Prestamo() {
		this.fechaCreacion = new Date();
		this.cdEstatus = 1;
		this.montoPagado = 0.0;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public Double getMontoPrestado() {
		return montoPrestado;
	}

	public void setMontoPrestado(Double montoPrestado) {
		this.montoPrestado = montoPrestado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Double montoPagado) {
		this.montoPagado = montoPagado;
	}

	public Integer getCdEstatus() {
		return cdEstatus;
	}

	public void setCdEstatus(Integer cdEstatus) {
		this.cdEstatus = cdEstatus;
	}

}
