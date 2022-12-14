/**
 * Entità film, relazione molti a uno con l'entità regista:
 * un regista può aver girato più film
 */
package com.cinemarest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Film {

	private Long id;

	private String titolo;

	private int anno;

	private Regista regista;

	private String tipo;

	private String incasso;

	public Film() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(nullable = false)
	public String getTitolo() {
		return titolo;
	}

	public int getAnno() {
		return anno;
	}

	@ManyToOne
	@JsonIgnoreProperties("film")
	@JoinColumn(name = "regista_id")
	public Regista getRegista() {
		return regista;
	}

	@Column(nullable = false)
	public String getTipo() {
		return tipo;
	}

	public String getIncasso() {
		return incasso;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public void setRegista(Regista regista) {
		this.regista = regista;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setIncasso(String incasso) {
		this.incasso = incasso;
	}

}
