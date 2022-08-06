/**
 * Entità regista, relazione uno a molti con l'entità film:
 * un film può avere un solo regista (assunzione a solo scopo didattico, per semplificare,
 * nella realtà un film può avere anche più di un regista, in quel caso sarebbe dovuta esserci
 * una relazione molti a molti tra film e regista)
 */
package com.cinemarest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Regista {

	private Long id;

	private String nome;

	private String cognome;

	private int annoNascita;

	private List<Film> film;

	public Regista() {
		film = new ArrayList<Film>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(nullable = false)
	public String getNome() {
		return nome.toLowerCase();
	}

	@Column(nullable = false)
	public String getCognome() {
		return cognome.toLowerCase();
	}

	@Column(nullable = false)
	public int getAnnoNascita() {
		return annoNascita;
	}

	@JsonIgnoreProperties("regista")
	@OneToMany(mappedBy = "regista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Film> getFilm() {
		return film;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setAnnoNascita(int annoNascita) {
		this.annoNascita = annoNascita;
	}

	public void setFilm(List<Film> film) {
		this.film = film;
	}

}
