package com.malla.registrationmanager.dao;

import javax.annotation.Resource;

import lombok.extern.java.Log;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.malla.registrationmanager.model.Credential;

@Repository
public class RegistrationDao {
	
	@Resource
	SessionFactory sessionFactory;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(Credential credential){
		sessionFactory.getCurrentSession().saveOrUpdate(credential);
	}
}
