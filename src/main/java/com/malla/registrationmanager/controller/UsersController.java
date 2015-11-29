package com.malla.registrationmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.malla.registrationmanager.dao.UsersDao;
import com.malla.registrationmanager.model.UsersResponse;

@Controller
public class UsersController {

	@Autowired
	UsersDao userDao;
	
	@RequestMapping(value="/FetchUsers", method=RequestMethod.GET)
	public @ResponseBody UsersResponse listUsers(){
		UsersResponse users = new UsersResponse();
		List<String> usernames = null;
		usernames = userDao.getUsers();
		
		for (String username : usernames) {
			System.out.println(username);
		}
		
		users.setNames(usernames);
		return users;
	}
}
