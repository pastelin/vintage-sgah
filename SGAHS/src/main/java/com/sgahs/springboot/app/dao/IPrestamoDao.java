package com.sgahs.springboot.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.sgahs.springboot.app.entity.Prestamo;

public interface IPrestamoDao extends CrudRepository<Prestamo, String> {

}
