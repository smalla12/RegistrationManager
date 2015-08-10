package com.malla.registrationmanager.services;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

import com.malla.registrationmanager.dao.RegistrationDao;
import com.malla.registrationmanager.model.Credential;
import com.malla.registrationmanager.utility.RegistrationMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:/application-context.xml"})
public class RegistrationServicesTest {
	
	@Autowired
	@InjectMocks
	RegistrationServices service;
	
	@Autowired
	MockServletContext context;
	
	@Mock
	RegistrationDao dao;
	
	String pathToFile;
	Credential credential;
	
	@Before
	public void createCredential(){
        MockitoAnnotations.initMocks(this);

		pathToFile = context.getRealPath("/WEB-INF/output");
		
		credential = new Credential();
		credential.setUsername("sandeep");
		credential.setPassword("Malla234");
		
		doNothing().when(dao).save(eq(credential));
		
	}

	@Test
	public void shouldReturnSuccessMessage() {
		try {
			String message = service.persist(credential, pathToFile);
			assertThat(message).isEqualTo(RegistrationMessage.SUCCESS_RESPONSE);
		} catch (IOException e) {
			fail("IO Exception occured.");
		}
	}
	
	@Test
	public void shouldActuallyWriteToFile(){
		File file = new File(pathToFile.concat("/credentials.txt"));		

		try{
			credential.setPassword("MALLA5642asd");
			service.persist(credential, pathToFile);
			
			FileInputStream in = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = reader.readLine();
			reader.close();

			assertThat(line).isNotNull();		 
			assertThat(line).contains(credential.getUsername());
			assertThat(line).contains(credential.getPassword());
		}catch (IOException e) {
			fail("IO Exception occured.");
		}
	}
	
	@After
	public void clearTextFile(){
		File file = new File(pathToFile.concat("/credentials.txt"));		

		FileOutputStream out;
		try {
			out = new FileOutputStream(file);
			try {
				out.write(new String().getBytes());
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
