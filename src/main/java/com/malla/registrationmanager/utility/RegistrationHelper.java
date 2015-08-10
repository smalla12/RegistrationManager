package com.malla.registrationmanager.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.malla.registrationmanager.model.Credential;

@Component
public class RegistrationHelper {
	
	private Pattern pattern;
	private Matcher matcher;
	
	public String validate(Credential credential){
		//System.out.println("Validation reached.");
		String response = null;
		
		//validate username
		String text = credential.getUsername();
		if(text==null || text.isEmpty())
			response = RegistrationMessage.UNAME_REQUIRED;
		else if(text.length() < 5)
			response = RegistrationMessage.UNAME_LENGTH;
		else if(!text.matches("^[a-zA-Z0-9]*$"))
			response = RegistrationMessage.UNAME_ALPHANUM;
		
		//validate password
		text = credential.getPassword();
		if(text==null || text.isEmpty())
			response = RegistrationMessage.PWD_REQUIRED;
		else if(text.length() < 8)
			response = RegistrationMessage.PWD_LENGTH;
		else if(!text.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})"))
			response = RegistrationMessage.PWD_REGEX;
		
		return response;
	}
	
	/*private boolean usernameRegexValidator(String username){
		
	}*/
}
