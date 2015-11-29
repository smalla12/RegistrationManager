package com.malla.registrationmanager.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.malla.registrationmanager.model.Credential;

@Repository
public class UsersDao {
	
	@Resource
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<String> getUsers(){
		List<String> usernames = null;
		
		usernames = (List<String>) sessionFactory.getCurrentSession()
				.createCriteria(Credential.class)
				.setProjection(Projections.property("username"))
				.list();
		
		return usernames;
	}
	
}
