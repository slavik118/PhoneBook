package com.sber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sber.model.Contact;
import com.sber.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Transactional(readOnly = true)
	 public Iterable<Contact> findAll() {
	     return this.contactRepository.findAll();
	 }
	 
	@Transactional(readOnly = true)
	 public Contact findContactById(long id) {
	     return this.contactRepository.findOne(id);
	 }
	 
	 @Transactional(readOnly = true)
	 public Iterable<Contact> findByLastName(String lastName){
		 return this.contactRepository.findByLastName(lastName);
	 }	 

	 @Transactional
	 public void saveContact(Contact contact){
		  this.contactRepository.save(contact);
	 }

}
