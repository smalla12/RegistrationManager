package com.malla.registrationmanager.utility;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static com.malla.registrationmanager.utility.RegistrationMessage.*;

import com.malla.registrationmanager.model.Credential;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/application-context.xml"})
public class RegistrationHelperTest {

	@Autowired
	RegistrationHelper helper;
	
	Credential credential;
	
	@Before
	public void createCredential(){
		credential = new Credential();
	}
	
	@Test
	public void shouldReturnNullForValidCredentials() {
		credential.setUsername("sandeep");
		credential.setPassword("Malla123");
		assertNull(helper.validate(credential));
	}
	
	@Test
	public void shouldReturnNullForAllUpperCaseUserName() {
		credential.setUsername("SANDEEP");
		credential.setPassword("Malla123");
		assertNull(helper.validate(credential));
	}
	
	@Test
	public void shouldReturnUserNameRequired(){
		credential.setPassword("Malla123");
		assertThat(helper.validate(credential)).isEqualTo(UNAME_REQUIRED);
	}
	
	@Test
	public void shouldReturnPasswordRequired(){
		credential.setUsername("sandeep");
		assertThat(helper.validate(credential)).isEqualTo(PWD_REQUIRED);
	}
	
	@Test
	public void shouldReturnUserNameLengthShouldBeAtLeastFive(){
		credential.setUsername("sand");
		credential.setPassword("Malla123");
		assertThat(helper.validate(credential)).isEqualTo(UNAME_LENGTH);
	}
	
	@Test
	public void shouldReturnPasswordLengthShouldBeAtLeastEight(){
		credential.setUsername("sandeep");
		credential.setPassword("Malla12");
		assertThat(helper.validate(credential)).isEqualTo(PWD_LENGTH);
	}
	
	@Test
	public void shouldReturnUserNameShouldOnlyContainAlphanumeric(){
		credential.setUsername("sand!@#");
		credential.setPassword("Malla123");
		assertThat(helper.validate(credential)).isEqualTo(UNAME_ALPHANUM);
	}
	
	@Test
	public void shouldReturnPasswordShouldHaveAtLeastOneNumber(){
		credential.setUsername("sandeep");
		credential.setPassword("Mallaasdfasf");
		assertThat(helper.validate(credential)).isEqualTo(PWD_REGEX);
	}
	
	@Test
	public void shouldReturnPasswordShouldHaveAtLeastOneUpperCase(){
		credential.setUsername("sandeep");
		credential.setPassword("123allaasdfasf");
		assertThat(helper.validate(credential)).isEqualTo(PWD_REGEX);
	}

	@Test
	public void shouldReturnPasswordShouldHaveAtLeastOneLowerCase(){
		credential.setUsername("sandeep");
		credential.setPassword("MASDFKL123LK");
		assertThat(helper.validate(credential)).isEqualTo(PWD_REGEX);
	}
}
