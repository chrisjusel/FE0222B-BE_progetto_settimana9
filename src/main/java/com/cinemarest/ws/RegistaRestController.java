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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/regista")
@Api(value = "RegistaRest", tags = "Gestione Registi")
public class RegistaRestController {

	@ApiOperation(value = "Inserimento di un regista", notes = "Dati i campi di un regista, esso viene inserito", response = String.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Inserimento effettuato") })
	@PostMapping("inserisci")
	public ResponseEntity<Regista> save(@RequestBody Regista regista) {
		RegistaDao registaDao = new RegistaDaoImpl();
		if (registaDao.save(regista) != null)
			return new ResponseEntity<Regista>(regista, HttpStatus.OK);
		return new ResponseEntity<Regista>(new Regista(), HttpStatus.OK);
	}

	@ApiOperation(value = "Recupero di un regista tramite id", notes = "Dato l'id di un regista, esso viene restituito", response = String.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Recupero effettuato") })
	@GetMapping("/{id}")
	public ResponseEntity<Regista> getById(@PathVariable Long id) {
		RegistaDao registaDao = new RegistaDaoImpl();
		Regista regista = registaDao.getById(id);
		if (regista != null)
			return new ResponseEntity<Regista>(regista, HttpStatus.OK);
		return new ResponseEntity<Regista>(new Regista(), HttpStatus.OK);
	}

	@ApiOperation(value = "Cancellazione di un regista", notes = "Dato l'id di un regista, esso viene eliminato", response = String.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cancellazione effettuata") })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		RegistaDao registaDao = new RegistaDaoImpl();
		if (registaDao.delete(id))
			return new ResponseEntity<String>("Eliminazione effettuata", HttpStatus.OK);
		return new ResponseEntity<String>("Eliminazione non effettuata", HttpStatus.OK);
	}

	@ApiOperation(value = "Recupero di tutti registi", notes = "Viene restituita la lista di tutti i registi presenti", response = String.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Recupero effettuato") })
	@GetMapping("/getall")
	public List<Regista> getAll() {
		RegistaDao registaDao = new RegistaDaoImpl();
		List<Regista> regista = registaDao.getAll();
		return regista;
	}

	@ApiOperation(value = "Modifica di un regista", notes = "Dati i dati di un regista, ed il suo id, esso verrà modificato", response = String.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Modifica effettuata") })
	@PutMapping("/update")
	public ResponseEntity<Regista> update(@RequestBody Regista regista) {
		RegistaDao registaDao = new RegistaDaoImpl();
		if (registaDao.update(regista))
			return new ResponseEntity<Regista>(regista, HttpStatus.OK);
		return new ResponseEntity<Regista>(new Regista(), HttpStatus.OK);
	}

}
