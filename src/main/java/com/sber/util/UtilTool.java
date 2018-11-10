package com.sber.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sber.model.Contact;

public interface UtilTool {
	 
	 public static <T> List<T> toList(final Iterable<T> iterable) {
		    return StreamSupport.stream(iterable.spliterator(), false)
		                        .collect(Collectors.toList());
		}
	 
	 public static Contact createContact(long id) {
		 return Contact.builder()
				    .id(id)
					.firstName("Fred")
					.lastName("Doe")
					.phone("1 (555) 456-56-56")
					.build();
	    }

}
