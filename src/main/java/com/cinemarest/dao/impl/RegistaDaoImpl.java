/**
 * Classe contente i metodi crud
 */
package com.cinemarest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.cinemarest.dao.RegistaDao;
import com.cinemarest.model.Regista;
import com.cinemarest.util.JpaUtil;

public class RegistaDaoImpl implements RegistaDao {

	private EntityManager em;

	/**
	 * Restituisce il regista se è stato correttamente creato, null altrimenti
	 */
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

	/**
	 * Restituisce true se il regista da modificare è stato trovato, falso altrimenti
	 */
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

	/**
	 * Restituisce il regista se è stato trovato, null altrimenti
	 */
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

	/**
	 * Restituisce una lista di registi se presenti, una lista vuota altrimenti
	 */
	@Override
	public List<Regista> getAll() {
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Regista> registi = new ArrayList<Regista>();
		Query query = em.createQuery("SELECT r from Regista r");
		registi = query.getResultList();
		return registi;
	}

	/*
	 * Restituisce true se il regista da modificare è stato trovato, falso altrimenti
	 * 
	 * @see com.cinemarest.dao.RegistaDao#update(com.cinemarest.model.Regista)
	 */
	@Override
	public boolean update(Regista regista) {
		boolean success = false;
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();

		try {
			entityTransaction.begin();
			Regista registaToModify = em.find(Regista.class, regista.getId());

			if (registaToModify != null) {
				regista.setFilm(registaToModify.getFilm());
				em.merge(regista);
				success = true;
			}

			entityTransaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		} finally {
			em.close();
		}
		return success;
	}

}
