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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/film")
@Api(value = "FilmREst", tags = "Servizio rest per la gestione di film")
public class FilmRestController {

	@PostMapping("/inserisci")
	public ResponseEntity<Film> save(@RequestBody FilmDto filmDto) {
		FilmDao filmDao = new FilmDaoImpl();
		Film film = filmDao.save(filmDto);
		if (film != null && film.getRegista() != null)
			return new ResponseEntity<Film>(film, HttpStatus.OK);
		return new ResponseEntity<Film>(new Film(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Film> getById(@PathVariable Long id) {
		FilmDao filmDao = new FilmDaoImpl();
		Film film = filmDao.getById(id);
		if (film != null)
			return new ResponseEntity<Film>(film, HttpStatus.OK);
		return new ResponseEntity<Film>(new Film(), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		FilmDao filmDao = new FilmDaoImpl();
		if (filmDao.delete(id))
			return new ResponseEntity<String>("Eliminazione effettuata", HttpStatus.OK);
		return new ResponseEntity<String>("Eliminazione non effettuata", HttpStatus.OK);
	}

	@ApiOperation(value = "Ricerca dei film di un regista", notes = "Dato l'id di un regista, si ottengono i suoi film", response = String.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Recupero effettuato") })
	@GetMapping("/getAllFilmsByIdRegista/{id}")
	public List<Film> getAllByRegistaId(@PathVariable Long id) {
		FilmDao filmDao = new FilmDaoImpl();
		List<Film> films = filmDao.getAllFilmByRegistaId(id);
		return films;
	}

	@ApiOperation(value = "Ricerca dei film di un regista", notes = "Dato il cognome di un regista, si ottengono i suoi film", response = String.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Recupero effettuato") })
	@GetMapping("/searchFilmsBySurname")
	public List<Film> searchFilmsBySurname(@RequestParam String search) {
		FilmDao filmDao = new FilmDaoImpl();
		List<Film> films = filmDao.searchFilmsBySurname(search);
		return films;
	}
}
