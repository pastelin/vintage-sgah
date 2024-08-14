package com.sgahs.springboot.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.sgahs.springboot.app.entity.GastoRecurrente;

public interface IGastoRecurrenteDao extends CrudRepository<GastoRecurrente, Integer> {

}
