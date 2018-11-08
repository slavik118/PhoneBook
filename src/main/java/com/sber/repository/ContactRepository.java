package com.sber.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sber.model.Contact;

/**
 * Contact repository
 */
@Repository
@Transactional
public interface ContactRepository extends CrudRepository<Contact, Long> {
	List<Contact> findByLastName(String lastName);
}