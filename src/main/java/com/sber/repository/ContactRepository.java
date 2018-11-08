package com.sber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sber.model.Contact;

/**
 * Contact repository
 */
@Repository
@Transactional
public interface ContactRepository extends JpaRepository<Contact, Long> {
	List<Contact> findByLastName(String lastName);
}