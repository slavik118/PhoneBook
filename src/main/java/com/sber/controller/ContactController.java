package com.sber.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sber.model.Contact;
import com.sber.service.ContactServiceImpl;
import com.sber.util.Response;

@RestController
public class ContactController {

	private static final Logger log = LoggerFactory.getLogger(ContactController.class);
	
	@Autowired
	private ContactServiceImpl contactService;

	@RequestMapping(value = "/postcontact", method = RequestMethod.POST)
	public void postCustomer(@RequestBody Contact contact) {
		log.debug("Contact: " + contact);
		this.contactService.saveContact(contact);
	}

	@RequestMapping("/findall")
	public Response findAll() {
		return new Response("Done", this.contactService.findAll());
	}

	@RequestMapping("/contact/{id}")
	public Response findContactById(@PathVariable("id") long id) {
		log.debug("User id: " + id);
		return new Response("Done", this.contactService.findContactById(id));
	}

	@RequestMapping("/findbylastname")
	public Response findByLastName(@RequestParam("lastName") String lastName) {
		log.debug("User Last Name: " + lastName);
		return new Response("Done", this.contactService.findByLastName(lastName));
	}
}
