package com.cloudrecipe.data.dao;

import com.cloudrecipe.data.exception.DAOException;
import com.zola.recipe.domain.User;

public interface UserDAO {

	
	void addUser( User user);
	
	User getUser( String username ) ;
	
	
	void deleteUser( String username);
	
	
}
