package com.cinemarest.ws;

import com.cinemarest.dao.RegistaDao;
import com.cinemarest.dao.impl.RegistaDaoImpl;
import com.cinemarest.model.Regista;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Regista> save(@RequestBody Regista regista){
		RegistaDao registaDao = new RegistaDaoImpl();
		if(registaDao.save(regista) != null)
			return new ResponseEntity<Regista>(regista, HttpStatus.OK);
		return new ResponseEntity<Regista>(new Regista(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Regista> getById(@PathVariable Long id){
		RegistaDao registaDao = new RegistaDaoImpl();
		Regista regista = registaDao.getById(id);
		if(regista != null)
			return new ResponseEntity<Regista>(regista, HttpStatus.OK);
		return new ResponseEntity<Regista>(new Regista(), HttpStatus.OK);
	}
}
