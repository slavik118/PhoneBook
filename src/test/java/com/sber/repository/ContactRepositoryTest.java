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
public class ContactRepositoryTest {
	
    @Autowired 
    private ContactRepository contactRepository;
    
    private Contact contact;
	
	@Before
	public void setup() {
		this.contact = saveContact();
	}
    
	@After
	public void cleanup() {
		this.contactRepository.delete(contact);
	}
		
	private Contact saveContact() {
		Contact contact = new Contact("Fred", "Doe", "1 (555) 456-56-56");
		Contact savedContact = this.contactRepository.save(contact);
		assertNotNull(savedContact);
		return savedContact;
	}
	
	@Test
    public void testFindAll() {
    	List<Contact> contacts =  UtilTool.toList(this.contactRepository.findAll());      
        assertThat(contacts, not(IsEmptyCollection.empty()));
    }
	
	@Test
	public void findByPrimaryKey() {
		Contact foundContact = this.contactRepository.findOne(contact.getId());
		assertNotNull(foundContact);
		assertEquals(contact.getFirstName(), foundContact.getFirstName());
		assertEquals(contact.getLastName(), foundContact.getLastName());
		assertEquals(contact.getPhone(), foundContact.getPhone());
	}

	@Test
	public void findByLastName() {
		List<Contact> contacts = this.contactRepository.findByLastName(contact.getLastName());
		assertNotNull(contacts);
		assertThat(contacts, hasItem(Matchers.<Contact>hasProperty("lastName", equalTo(contact.getLastName()))));
	}
	
	@Test
	public void findByFirstNameSpec() {
		List<Contact> contacts = this.contactRepository.findAll(hasFirstNameLike(contact.getFirstName()));
		assertNotNull(contacts);
		assertThat(contacts, hasItem(Matchers.<Contact>hasProperty("firstName", equalTo(contact.getFirstName()))));
	}
	
	@Test
	public void findByFirstNameAndLastNameLike() {
		List<Contact> customers = this.contactRepository.findAll(where(hasFirstNameLike("Fre")).and(hasLastNameLike("Do")));
		assertNotNull(customers);
		assertThat(customers, hasItem(Matchers.<Contact>hasProperty("firstName", equalTo(contact.getFirstName()))));
		assertThat(customers, hasItem(Matchers.<Contact>hasProperty("lastName", equalTo(contact.getLastName()))));
	}
	
	@Test
	public void findWithPageable() {
		PageRequest pageRequest = new PageRequest(0, 1);
		Page<Contact> pageOne = this.contactRepository.findAll(pageRequest);
		assertNotNull(pageOne);
		assertEquals(1, pageOne.getSize());
		
		for (Contact contact : pageOne.getContent()) {
			System.out.println("Contact: "+contact);
		}
	}
}
