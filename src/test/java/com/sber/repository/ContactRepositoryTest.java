package com.sber.repository;

import org.hamcrest.Matchers;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sber.model.Contact;
import com.sber.repository.ContactRepository;
import com.sber.util.UtilTool;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import static com.sber.spec.ContactSpecifications.hasFirstNameLike;
import static com.sber.spec.ContactSpecifications.hasLastNameLike;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j
public class ContactRepositoryTest {
	
    @Autowired 
    private ContactRepository contactRepository;
    
    private Contact contact;
	
    /**
	   * Test setup.
	   */
	@Before
	public void setup() {
		this.contact = saveContact();
	}
    
	 /**
	   * Test clean up.
	   */
	@After
	public void cleanup() {
		this.contactRepository.delete(contact);
	}
		
	 /**
	   * Save contact.
	   */
	private Contact saveContact() {		
		final Contact contact = Contact.builder()
				.firstName("Fred")
				.lastName("Doe")
				.phone("1 (555) 456-56-56")
				.build();
		final Contact savedContact = this.contactRepository.save(contact);
		assertNotNull(savedContact);
		return savedContact;
	}
	
	 /**
	   * Test findAll method.
	   */
	@Test
    public void testFindAll() {
    	final List<Contact> contacts =  UtilTool.toList(this.contactRepository.findAll());      
        assertThat(contacts, not(IsEmptyCollection.empty()));
    }
	
	 /**
	   * Test findByPrimaryKey method.
	   */
	@Test
	public void testFindByPrimaryKey() {
		final Contact foundContact = this.contactRepository.findOne(contact.getId());
		assertNotNull(foundContact);
		assertEquals(contact.getFirstName(), foundContact.getFirstName());
		assertEquals(contact.getLastName(), foundContact.getLastName());
		assertEquals(contact.getPhone(), foundContact.getPhone());
	}

	 /**
	   * Test findByLastName method.
	   */
	@Test
	public void testFindByLastName() {
		final List<Contact> contacts = this.contactRepository.findByLastName(contact.getLastName());
		assertNotNull(contacts);
		assertThat(contacts, hasItem(Matchers.<Contact>hasProperty("lastName", equalTo(contact.getLastName()))));
	}
	
	 /**
	   * Test findByFirstNameSpec method.
	   */
	@Test
	public void testFindByFirstNameSpec() {
		final List<Contact> contacts = this.contactRepository.findAll(hasFirstNameLike(contact.getFirstName()));
		assertNotNull(contacts);
		assertThat(contacts, hasItem(Matchers.<Contact>hasProperty("firstName", equalTo(contact.getFirstName()))));
	}
	
	 /**
	   * Test findByFirstNameAndLastNameLike method.
	   */
	@Test
	public void testFindByFirstNameAndLastNameLike() {
		final List<Contact> customers = this.contactRepository.findAll(where(hasFirstNameLike("Fre")).and(hasLastNameLike("Do")));
		assertNotNull(customers);
		assertThat(customers, hasItem(Matchers.<Contact>hasProperty("firstName", equalTo(contact.getFirstName()))));
		assertThat(customers, hasItem(Matchers.<Contact>hasProperty("lastName", equalTo(contact.getLastName()))));
	}
	
	 /**
	   * Test pagination method.
	   */
	@Test
	public void testFindWithPageable() {
		final PageRequest pageRequest = new PageRequest(0, 1);
		final Page<Contact> pageOne = this.contactRepository.findAll(pageRequest);
		assertNotNull(pageOne);
		assertEquals(1, pageOne.getSize());
		
		for (final Contact contact : pageOne.getContent()) {
			log.debug("Contact: " + contact);
		}
	}
	
	
}
