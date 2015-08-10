package com.malla.registrationmanager.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.malla.registrationmanager.dao.RegistrationDao;
import com.malla.registrationmanager.model.Credential;
import com.malla.registrationmanager.utility.RegistrationMessage;

@Service
public class RegistrationServices {
	
	@Autowired
	RegistrationDao dao;

	public String persist(Credential credential, String pathToTextFile) throws IOException {		
		File file = new File(pathToTextFile.concat("/credentials.txt"));			
		String credentialToWrite = credential.getUsername().concat(", ").concat(credential.getPassword());
		byte dataToWrite[] = credentialToWrite.getBytes();
		FileOutputStream out = new FileOutputStream(file);
		out.write(dataToWrite);
		out.close();
		
		dao.save(credential);
		
		return RegistrationMessage.SUCCESS_RESPONSE;
	}

}
