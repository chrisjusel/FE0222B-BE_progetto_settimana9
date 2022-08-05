package com.cinemarest.ws;

import java.util.List;

import com.cinemarest.dao.FilmDao;
import com.cinemarest.dao.RegistaDao;
import com.cinemarest.dao.impl.RegistaDaoImpl;
import com.cinemarest.model.Film;
import com.cinemarest.model.Regista;

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
@RequestMapping("/regista")
public class RegistaRestController {

	@PostMapping("inserisci")
	public ResponseEntity<Regista> save(@RequestBody Regista regista) {
		RegistaDao registaDao = new RegistaDaoImpl();
		if (registaDao.save(regista) != null)
			return new ResponseEntity<Regista>(regista, HttpStatus.OK);
		return new ResponseEntity<Regista>(new Regista(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Regista> getById(@PathVariable Long id) {
		RegistaDao registaDao = new RegistaDaoImpl();
		Regista regista = registaDao.getById(id);
		if (regista != null)
			return new ResponseEntity<Regista>(regista, HttpStatus.OK);
		return new ResponseEntity<Regista>(new Regista(), HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public List<Regista> getAll() {
		RegistaDao registaDao = new RegistaDaoImpl();
		List<Regista> regista = registaDao.getAll();
		return regista;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		RegistaDao registaDao = new RegistaDaoImpl();
		if (registaDao.delete(id))
			return new ResponseEntity<String>("Eliminazione effettuata", HttpStatus.OK);
		return new ResponseEntity<String>("Eliminazione non effettuata", HttpStatus.OK);
	}

}
