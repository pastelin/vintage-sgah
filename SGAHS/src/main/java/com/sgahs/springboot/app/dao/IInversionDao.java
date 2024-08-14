package com.sgahs.springboot.app.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sgahs.springboot.app.entity.Inversion;

public interface IInversionDao extends CrudRepository<Inversion, String> {

	@Procedure(name = "spuInversion")
	void spuInversion(@Param("folio") String folio, @Param("monto") Double monto, @Param("descripcion") String descripcion,
			@Param("fecha") Date fecha, @Param("estatus") Integer estatus, @Param("app_inversion") Integer appInversion);
	
}
