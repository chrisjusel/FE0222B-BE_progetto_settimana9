package com.cinemarest.dao;

import java.util.List;

import com.cinemarest.dto.FilmDto;
import com.cinemarest.model.Film;

public interface FilmDao {

	public Film save(FilmDto film);

	public boolean delete(Long id);

	public Film getById(Long id);

	public List<Film> getAllFilmByRegistaId(Long id);

	public List<Film> searchFilmsBySurname(String ricerca);
}
