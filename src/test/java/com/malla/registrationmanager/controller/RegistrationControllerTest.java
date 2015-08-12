package com.malla.registrationmanager.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockServletContext;

import com.malla.registrationmanager.model.Credential;
import com.malla.registrationmanager.model.RegistrationResponse;
import com.malla.registrationmanager.services.RegistrationServices;
import com.malla.registrationmanager.utility.RegistrationHelper;
import com.malla.registrationmanager.utility.RegistrationMessage;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

	@InjectMocks
	RegistrationController controller;
	
	@Mock
	RegistrationHelper helper;
	
	@Mock
	RegistrationServices services;
	
	@Mock
	MockServletContext context;
	
	@Mock
	Credential credential;
	
	RegistrationResponse response;
	
	@Before
	public void createCredential() throws IOException{		
		when(helper.validate(eq(credential))).thenReturn(null);
		when(context.getRealPath(anyString())).thenReturn("/Users/sandeepmalla/Documents/Assignment/RegistrationManager/src/main/webapp/WEB-INF/output");
		when(services.persist(eq(credential), anyString())).thenReturn(RegistrationMessage.SUCCESS_RESPONSE);
	}
	
	@Test
	public void shouldRegisterWithSuccessMessage() {
		response = controller.register(credential);
		assertThat(response).isNotNull();
		assertThat(response.getMessage()).isEqualTo(RegistrationMessage.SUCCESS_RESPONSE);
		assertThat(response.isSuccess()).isTrue();
	}
	
	@Test
	public void shouldRespondWithErrorMessage(){
		when(helper.validate(eq(credential))).thenReturn(RegistrationMessage.PWD_LENGTH);
		response = controller.register(credential);
		assertThat(response).isNotNull();
		assertThat(response.getMessage()).isEqualTo(RegistrationMessage.PWD_LENGTH);
		assertThat(response.isSuccess()).isFalse();
	}
	
	@Test
	public void shouldCatchException() throws IOException{
		when(services.persist(eq(credential), anyString())).thenThrow(new IOException("IO Exception occured"));
		response = controller.register(credential);
		assertThat(response).isNotNull();
		assertThat(response.getMessage()).isEqualTo("IO Exception occured");
		assertThat(response.isSuccess()).isFalse();
	}

}
