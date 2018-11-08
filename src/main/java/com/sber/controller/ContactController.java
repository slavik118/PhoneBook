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


@RestController
public class ContactController {

	@Autowired
	ContactServiceImpl contactService;

	@RequestMapping(value = "/postcontact", method = RequestMethod.POST)
	public void postCustomer(@RequestBody Contact contact) {
		contactService.saveContact(contact);
	}

	@RequestMapping("/findall")
	public Response findAll() {
		return new Response("Done", contactService.findAll());
	}

	@RequestMapping("/contact/{id}")
	public Response findContactById(@PathVariable("id") long id) {
		return new Response("Done", contactService.findContactById(id));
	}

	@RequestMapping("/findbylastname")
	public Response findByLastName(@RequestParam("lastName") String lastName) {
		return new Response("Done", contactService.findByLastName(lastName));
	}
}
