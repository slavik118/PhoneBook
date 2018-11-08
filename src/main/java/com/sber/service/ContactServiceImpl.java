package com.sber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sber.model.Contact;
import com.sber.repository.ContactRepository;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	 public Iterable<Contact> findAll() {
	     return this.contactRepository.findAll();
	 }
	 
	 public Contact findContactById(long id) {
	     return this.contactRepository.findOne(id);
	 }
	 
	 public Iterable<Contact> findByLastName(String lastName){
		 return this.contactRepository.findByLastName(lastName);
	 }	 

	 public void saveContact(Contact contact){
		  this.contactRepository.save(contact);
	 }

}
