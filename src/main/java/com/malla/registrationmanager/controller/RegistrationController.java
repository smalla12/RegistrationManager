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
		response.setSuccess(false);

		String validateMsg = helper.validate(credential);
		if (validateMsg != null) {
			response.setMessage(validateMsg);
		} else {
			try {
				String pathToTextFile = context.getRealPath("/WEB-INF/output");
				response.setMessage(services.persist(credential, pathToTextFile));
				response.setSuccess(true);
			} catch (IOException e) {
				response.setMessage(e.getMessage());
				return response;
			}
		}

		return response;
	}

}
