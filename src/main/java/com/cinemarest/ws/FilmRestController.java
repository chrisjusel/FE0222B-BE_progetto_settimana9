package com.cinemarest.ws;

import com.cinemarest.dao.FilmDao;
import com.cinemarest.dao.impl.FilmDaoImpl;
import com.cinemarest.dto.FilmDto;
import com.cinemarest.model.Film;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/film")
public class FilmRestController {

	@PostMapping("/inserisci")
	public ResponseEntity<Film> save(@RequestBody FilmDto filmDto){
		FilmDao filmDao = new FilmDaoImpl();
		Film film = filmDao.save(filmDto);
		if(film != null)
			return new ResponseEntity<Film>(film, HttpStatus.OK);
		return new ResponseEntity<Film>(new Film(), HttpStatus.OK);
	}
}
