package com.sber.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel( Contact.class )
public class Contact_ {
    public static volatile SingularAttribute<Contact, Long> id;
    public static volatile SingularAttribute<Contact, String> firstName;
    public static volatile SingularAttribute<Contact, String> lastName;
    public static volatile SingularAttribute<Contact, String> phone;
}
