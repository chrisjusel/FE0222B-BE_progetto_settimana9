package com.cinemarest.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.cinemarest.dao.FilmDao;
import com.cinemarest.dao.RegistaDao;
import com.cinemarest.dto.FilmDto;
import com.cinemarest.model.Film;
import com.cinemarest.util.DtoConverter;
import com.cinemarest.util.JpaUtil;

public class FilmDaoImpl implements FilmDao {

	EntityManager em;
	
	@Override
	public Film save(FilmDto filmDto) {
		Film film = DtoConverter.toEntity(filmDto);
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		try {
			entityTransaction.begin();
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

}
