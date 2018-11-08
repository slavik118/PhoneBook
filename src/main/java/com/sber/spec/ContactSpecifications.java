package com.sber.spec;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.sber.model.Contact;
import com.sber.model.Contact_;


public class ContactSpecifications {
	
	public static Specification<Contact> hasFirstNameLike(final String firstname) {
		return new Specification<Contact>() {
			public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Contact_.firstName), firstname + "%");
			}
		};
	}

	public static Specification<Contact> hasLastNameLike(final String lastname) {
		return new Specification<Contact>() {
			public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Contact_.lastName), lastname + "%");
			}
		};
	}
}
