package com.sber.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactControllerTest {
	
	private static final String DOMAIN_URL = "http://localhost:";
	
	 @LocalServerPort
	 private int randomServerPort;
	 
	 @Test
	 public void testFindAllContactsSuccess() throws URISyntaxException
	 {
	     RestTemplate restTemplate = new RestTemplate();
	      
	     final String baseUrl = DOMAIN_URL + randomServerPort + "/findall";
	     URI uri = new URI(baseUrl);
	  
	     ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	      
	     //Verify request succeed
	     assertEquals(200, result.getStatusCodeValue());
	     assertEquals(true, result.getBody().contains("data"));
	 }

}
