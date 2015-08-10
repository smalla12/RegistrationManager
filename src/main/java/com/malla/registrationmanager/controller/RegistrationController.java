package com.malla.registrationmanager.controller;

import java.io.IOException;

import javax.servlet.ServletContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.malla.registrationmanager.model.Credential;
import com.malla.registrationmanager.model.RegistrationResponse;
import com.malla.registrationmanager.services.RegistrationServices;
import com.malla.registrationmanager.utility.RegistrationHelper;

@Controller
public class RegistrationController{
	
	static final String TYPE_ERROR="errors";
	static final String TYPE_SUCCESS="success";

	@Autowired
	RegistrationHelper helper;

	@Autowired
	RegistrationServices services;
	
	@Autowired
	ServletContext context;

	@RequestMapping(value = "/SaveCredential", method = RequestMethod.POST)
	public @ResponseBody RegistrationResponse register(
			@RequestBody Credential credential) {
		RegistrationResponse response = new RegistrationResponse();
		response.setType(TYPE_ERROR);

		String validateMsg = helper.validate(credential);
		if (validateMsg != null) {
			response.setMessage(validateMsg);
		} else {
			try {
				String pathToTextFile = context.getRealPath("/WEB-INF/output");
				response.setMessage(services.persist(credential, pathToTextFile));
				response.setType(TYPE_SUCCESS);
			} catch (IOException e) {
				response.setMessage(e.getMessage());
				return response;
			}
		}

		return response;
	}

}
