package com.sber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sber.model.Contact;
import com.sber.service.ContactServiceImpl;
import com.sber.util.Response;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "/")
@Slf4j
public class ContactController {
	
	private static final String DONE_STATUS_MESSAGE = "Done";

	@Autowired
	private ContactServiceImpl contactService;

	@PostMapping(path = "/postcontact", consumes = "application/json", produces = "application/json")
	public void postContact(@RequestBody Contact contact) {
		log.debug("Contact: " + contact);
		this.contactService.saveContact(contact);
	}

	@GetMapping(path = "/findall", produces = "application/json")
	public Response findAll() {
		return Response.builder()
				.status(DONE_STATUS_MESSAGE)
				.data(this.contactService.findAll())
				.build();
	}

	@GetMapping(path = "/contact/{id}", produces = "application/json")
	public Response findContactById(@PathVariable("id") long id) {
		log.debug("User id: " + id);		
		return Response.builder()
				.status(DONE_STATUS_MESSAGE)
				.data(this.contactService.findContactById(id))
				.build();
	}

	@GetMapping(path = "/findbylastname", produces = "application/json")
	public Response findByLastName(@RequestParam(value = "lastName", required = true) String lastName) {
		log.debug("User Last Name: " + lastName);		
		return Response.builder()
				.status(DONE_STATUS_MESSAGE)
				.data(this.contactService.findByLastName(lastName))
				.build();
	}
}
