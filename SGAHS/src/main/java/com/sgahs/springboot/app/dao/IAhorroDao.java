package com.sgahs.springboot.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.sgahs.springboot.app.entity.Ahorro;

public interface IAhorroDao extends CrudRepository<Ahorro, Long>{

}
