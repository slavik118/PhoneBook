package com.sber.controller;

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

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ContactController {

	@Autowired
	private ContactServiceImpl contactService;

	@RequestMapping(value = "/postcontact", method = RequestMethod.POST)
	public void postCustomer(@RequestBody Contact contact) {
		log.debug("Contact: " + contact);
		this.contactService.saveContact(contact);
	}

	@RequestMapping("/findall")
	public Response findAll() {
		return Response.builder()
				.status("Done")
				.data(this.contactService.findAll())
				.build();
	}

	@RequestMapping("/contact/{id}")
	public Response findContactById(@PathVariable("id") long id) {
		log.debug("User id: " + id);		
		return Response.builder()
				.status("Done")
				.data(this.contactService.findContactById(id))
				.build();
	}

	@RequestMapping("/findbylastname")
	public Response findByLastName(@RequestParam("lastName") String lastName) {
		log.debug("User Last Name: " + lastName);		
		return Response.builder()
				.status("Done")
				.data(this.contactService.findByLastName(lastName))
				.build();
	}
}
