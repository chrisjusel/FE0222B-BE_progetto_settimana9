package com.cinemarest.dto;

public class FilmDto {

	private Long id;

	private String titolo;

	private int anno;

	private String tipo;

	private Long idRegista;

	private int incasso;

	public Long getId() {
		return id;
	}

	public String getTitolo() {
		return titolo;
	}

	public int getAnno() {
		return anno;
	}

	public String getTipo() {
		return tipo;
	}

	public Long getIdRegista() {
		return idRegista;
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

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setIdRegista(Long idRegista) {
		this.idRegista = idRegista;
	}

	public int getIncasso() {
		return incasso;
	}

	public void setIncasso(int incasso) {
		this.incasso = incasso;
	}

}
