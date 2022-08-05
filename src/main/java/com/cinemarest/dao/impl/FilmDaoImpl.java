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

	EntityManager em;

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

	@Override
	public boolean delete(Long id) {
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		boolean success = false;
		try {
			entityTransaction.begin();
			Query query = em.createQuery("DELETE FROM Film f WHERE f.id = :id");
			query.setParameter("id", id);
			query.executeUpdate();
			entityTransaction.commit();
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
		return success;

	}

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

	@Override
	public List<Film> getAllFilmByRegistaId(Long id) {
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Film> films = new ArrayList<Film>();
		Query query = em.createQuery("SELECT f from Film f WHERE f.regista.id = :id");
		query.setParameter("id", id);
		films = query.getResultList();
		return films;
	}

	@Override
	public List<Film> searchFilmsBySurname(String ricerca) {
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Film> films = new ArrayList<Film>();
		Query query = em.createQuery("SELECT f from Film f WHERE f.regista.cognome LIKE :cognome");
		query.setParameter("cognome", "%" + ricerca + "%");
		films = query.getResultList();
		return films;
	}

}
