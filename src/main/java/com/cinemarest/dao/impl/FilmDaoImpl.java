/**
 * Classe contenente i metodi crud
 */
package com.cinemarest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.cinemarest.dao.FilmDao;
import com.cinemarest.dto.FilmDto;
import com.cinemarest.model.Film;
import com.cinemarest.util.DtoConverter;
import com.cinemarest.util.JpaUtil;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class FilmDaoImpl implements FilmDao {

	private EntityManager em;

	/**
	 * Restituisce il film se creato correttamente, null altrimenti
	 */
	@Override
	public Film save(FilmDto filmDto) {
		Film film = DtoConverter.toEntity(filmDto);
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		try {
			entityTransaction.begin();
			String incasso = BCrypt.hashpw(film.getIncasso(), BCrypt.gensalt());
			film.setIncasso(incasso);
			em.persist(film);
			entityTransaction.commit();
			return film;
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Restituisce true se il film da eliminare è stato trovato, false altrimenti
	 */
	@Override
	public boolean delete(Long id) {
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		boolean success = false;
		try {
			entityTransaction.begin();
			Film film = em.find(Film.class, id);
			if (film != null) {
				em.remove(film);
				entityTransaction.commit();
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
		return success;

	}

	/**
	 * Restituisce il film se è stato trovato, null altrimenti
	 */
	@Override
	public Film getById(Long id) {
		Film film = null;
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		try {
			entityTransaction.begin();
			film = em.find(Film.class, id);
			entityTransaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		} finally {
			em.close();
		}
		return film;
	}

	/**
	 * Restituisce una lista contente i film trovati, una lista vuota altrimenti
	 */
	@Override
	public List<Film> getAllFilmByRegistaId(Long id) {
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Film> films = new ArrayList<Film>();
		Query query = em.createQuery("SELECT f from Film f WHERE f.regista.id = :id");
		query.setParameter("id", id);
		films = query.getResultList();
		return films;
	}

	/**
	 * Restituisce una lista contente i film trovati, una lista vuota altrimenti
	 */
	@Override
	public List<Film> searchFilmsBySurname(String ricerca) {
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Film> films = new ArrayList<Film>();
		Query query = em.createQuery("SELECT f from Film f WHERE f.regista.cognome LIKE :cognome");
		query.setParameter("cognome", "%" + ricerca.toLowerCase() + "%");
		films = query.getResultList();
		return films;
	}

}
