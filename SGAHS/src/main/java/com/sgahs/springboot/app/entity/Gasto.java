package com.sgahs.springboot.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.sgahs.springboot.app.validation.MontoValido;

@Entity
@Table(name = "gastos")
public class Gasto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaCreacion;

	@MontoValido
	private Double monto;

	@NotEmpty
	private String descripcion;

	@NotNull
	@Column(name = "cd_gasto_recurrente")
	private Integer cdGastoRecurrente;

	@NotNull
	@Column(name = "cd_estatus")
	private Integer cdEstatus;

	@NotNull
	@Column(name = "cd_tipo_movimiento_gasto")
	private Integer cdTipoMovimiento;

	public Gasto() {
		this.fechaCreacion = new Date();
		this.cdTipoMovimiento = 1;
		this.cdEstatus = 1;
		this.cdGastoRecurrente = 0;
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

	public Integer getCdGastoRecurrente() {
		return cdGastoRecurrente;
	}

	public void setCdGastoRecurrente(Integer cdGasto) {
		this.cdGastoRecurrente = cdGasto;
	}

	public Integer getCdEstatus() {
		return cdEstatus;
	}

	public void setCdEstatus(Integer cdEstatus) {
		this.cdEstatus = cdEstatus;
	}

	public Integer getCdTipoMovimiento() {
		return cdTipoMovimiento;
	}

	public void setCdTipoMovimiento(Integer cdTipoMovimiento) {
		this.cdTipoMovimiento = cdTipoMovimiento;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

}
