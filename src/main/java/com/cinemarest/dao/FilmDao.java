package com.cinemarest.dao;

import com.cinemarest.dto.FilmDto;
import com.cinemarest.model.Film;

public interface FilmDao {

	public Film save(FilmDto film);
	
}
