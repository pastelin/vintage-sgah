package com.sgahs.springboot.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.sgahs.springboot.app.validation.MontoValido;

@Entity
@Table(name = "inversiones")
@NamedStoredProcedureQuery(name="spuInversion",
	procedureName = "spu_inversion", parameters = {
			@StoredProcedureParameter(mode= ParameterMode.IN, name= "folio", type= String.class),
			@StoredProcedureParameter(mode= ParameterMode.IN, name= "monto", type= Double.class),
			@StoredProcedureParameter(mode= ParameterMode.IN, name= "descripcion", type= String.class),
			@StoredProcedureParameter(mode= ParameterMode.IN, name= "fecha", type= Date.class),
			@StoredProcedureParameter(mode= ParameterMode.IN, name= "estatus", type= Integer.class),
			@StoredProcedureParameter(mode= ParameterMode.IN, name= "app_inversion", type= Integer.class),
	}
)
public class Inversion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String folio;

	@MontoValido
	private Double monto;

	@NotEmpty
	private String descripcion;

	@NotNull
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaCreacion;

	@Column(name = "cd_estatus")
	private Integer cdEstatus;

	@NotNull
	@Column(name = "cd_app_inversion")
	private Integer cdAppInversion;
	
	public Inversion() {
		this.fechaCreacion = new Date();
		this.cdEstatus = 1;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCdAppInversion() {
		return cdAppInversion;
	}

	public void setCdAppInversion(Integer cdAppInversion) {
		this.cdAppInversion = cdAppInversion;
	}

	public Integer getCdEstatus() {
		return cdEstatus;
	}

	public void setCdEstatus(Integer cdEstatus) {
		this.cdEstatus = cdEstatus;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
