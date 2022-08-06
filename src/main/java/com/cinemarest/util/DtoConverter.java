/**
 * Questa classe di servizio ha il compito di assegnare tutti i dati di un regista
 * ad un determinato film fornendo semplicemente l'id del regista a cui ci si vuole
 * riferire
 */
package com.cinemarest.util;

import com.cinemarest.dao.RegistaDao;
import com.cinemarest.dao.impl.RegistaDaoImpl;
import com.cinemarest.dto.FilmDto;
import com.cinemarest.model.Film;

public abstract class DtoConverter {

	public static Film toEntity(FilmDto filmDto) {
		RegistaDao registaDao = new RegistaDaoImpl();
		Film film = new Film();
		film.setAnno(filmDto.getAnno());
		film.setIncasso(filmDto.getIncasso());
		film.setRegista(registaDao.getById(filmDto.getIdRegista()));
		film.setTipo(filmDto.getTipo());
		film.setTitolo(filmDto.getTitolo());
		return film;
	}
}
