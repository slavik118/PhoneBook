package com.sber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sber.model.Contact;
import com.sber.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepository repository;
	
	 @Override
	 public Iterable<Contact> findAll() {
	     return repository.findAll();
	 }
	 
	 @Override
	 public Contact findContactById(long id) {
	     return repository.findOne(id);
	 }
	 
	 @Override
	 public Iterable<Contact> findByLastName(String lastName){
		 return repository.findByLastName(lastName);
	 }
	 
	 
	 @Override
	 public void saveContact(Contact contact){
		  repository.save(contact);
	 }

}
