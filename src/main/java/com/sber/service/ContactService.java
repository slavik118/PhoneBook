package com.sber.service;

import com.sber.model.Contact;

public interface ContactService {
	Iterable<Contact> findAll();
	
	Contact findContactById(long id);

	Iterable<Contact> findByLastName(String lastName);

	void saveContact(Contact contact);
}
