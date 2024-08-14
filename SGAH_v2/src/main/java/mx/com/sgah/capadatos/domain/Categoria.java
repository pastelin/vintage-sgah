package mx.com.sgah.capadatos.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cat_categoria")
public class Categoria {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer idCatCategoria;
	
	private String nombre;
	
	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}

	
	public Categoria() {
		super();
	}

	public Categoria(Integer idCatCategoria, String nombre) {
		super();
		this.idCatCategoria = idCatCategoria;
		this.nombre = nombre;
	}

	public Integer getIdCatCategoria() {
		return idCatCategoria;
	}

	public void setIdCatCategoria(Integer idCatCategoria) {
		this.idCatCategoria = idCatCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Categoria [idCatCategoria=" + idCatCategoria + ", nombre=" + nombre + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCatCategoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(idCatCategoria, other.idCatCategoria);
	}
	
}
