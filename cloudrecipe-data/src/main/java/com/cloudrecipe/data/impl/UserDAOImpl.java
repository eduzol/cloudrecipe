package com.cloudrecipe.data.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloudrecipe.data.dao.UserDAO;
import com.zola.recipe.domain.User;


@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	
	private SessionFactory sessionFactory;
	
	@Autowired
	public UserDAOImpl( SessionFactory sessionFactory ){
		
		this.sessionFactory = sessionFactory;
	}
	
	private Session currentSession(){
		return sessionFactory.getCurrentSession();
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void addUser(User user) {
		
		currentSession().saveOrUpdate(user);
	

	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public User getUser(String username) {
		
		Query hqlQuery =currentSession().createQuery("from User u where u.username = :username");
		hqlQuery.setString("username", username);
		
		User user = (User)hqlQuery.uniqueResult();
		
		return user;
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteUser(String username) {
		
		User user =(User) currentSession().get(User.class, username);
		currentSession().delete(user);
		
	}

	
	
}
