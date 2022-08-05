package com.cinemarest.dao;

import com.cinemarest.model.Regista;

public interface RegistaDao {
	public void save(Regista regista);

	public void delete(Long id);

	public void update(Regista regista);

	public Regista getById(Long id);
}
