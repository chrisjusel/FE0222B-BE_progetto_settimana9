package com.cinemarest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.cinemarest.dao.RegistaDao;
import com.cinemarest.model.Film;
import com.cinemarest.model.Regista;
import com.cinemarest.util.JpaUtil;

public class RegistaDaoImpl implements RegistaDao {

	EntityManager em;

	@Override
	public Regista save(Regista regista) {
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		try {
			entityTransaction.begin();
			em.persist(regista);
			entityTransaction.commit();
			return regista;
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

		entityTransaction.begin();
		Regista regista = em.find(Regista.class, id);
		if (regista != null) {
			em.remove(regista);
			entityTransaction.commit();
			em.close();
			return true;
		}
		em.close();
		return false;

	}

	@Override
	public Regista getById(Long id) {
		Regista regista = null;
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		try {
			entityTransaction.begin();
			regista = em.find(Regista.class, id);
			entityTransaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		} finally {
			em.close();
		}
		return regista;
	}

	@Override
	public List<Regista> getAll() {
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Regista> registi = new ArrayList<Regista>();
		Query query = em.createQuery("SELECT r from Regista r");
		registi = query.getResultList();
		return registi;
	}

}
