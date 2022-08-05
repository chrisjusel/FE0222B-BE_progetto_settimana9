package com.cinemarest.dao;

import java.util.List;

import com.cinemarest.model.Regista;

public interface RegistaDao {
	public Regista save(Regista regista);

	public boolean delete(Long id);

	public Regista getById(Long id);

	public List<Regista> getAll();
}
