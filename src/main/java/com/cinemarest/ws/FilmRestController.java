package com.cinemarest.ws;

import java.util.List;

import com.cinemarest.dao.FilmDao;
import com.cinemarest.dao.RegistaDao;
import com.cinemarest.dao.impl.FilmDaoImpl;
import com.cinemarest.dao.impl.RegistaDaoImpl;
import com.cinemarest.dto.FilmDto;
import com.cinemarest.model.Film;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		if(film != null && film.getRegista() != null)
			return new ResponseEntity<Film>(film, HttpStatus.OK);
		return new ResponseEntity<Film>(new Film(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Film> getById(@PathVariable Long id){
		FilmDao filmDao = new FilmDaoImpl();
		Film film = filmDao.getById(id);
		if(film != null)
			return new ResponseEntity<Film>(film, HttpStatus.OK);
		return new ResponseEntity<Film>(new Film(), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		FilmDao filmDao = new FilmDaoImpl();
		if(filmDao.delete(id))
			return new ResponseEntity<String>("Eliminazione effettuata", HttpStatus.OK);
		return new ResponseEntity<String>("Eliminazione non effettuata", HttpStatus.OK);
	}
	
	@GetMapping("/getAllFilmsByIdRegista/{id}")
	public List<Film> getAllByRegistaId(@PathVariable Long id) {
		FilmDao filmDao = new FilmDaoImpl();
		List<Film> films = filmDao.getAllFilmByRegistaId(id);
		return films;
	}
}
