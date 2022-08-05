package com.cinemarest.dao.impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.cinemarest.dao.RegistaDao;
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
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Regista regista) {
		// TODO Auto-generated method stub

	}

	@Override
	public Regista getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
