package com.sber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.junit.Assert;
import org.junit.Test;

import com.sber.util.AssertAnnotations;
import com.sber.util.ReflectTool;


public class ContactTest {
  @Test
  public void typeAnnotations() {
    // assert
    AssertAnnotations.assertType(
        Contact.class, Entity.class, Table.class);
  }
  
  @Test
  public void fieldAnnotations() {
    // assert
    AssertAnnotations.assertField(
    		Contact.class, "id", Id.class, GeneratedValue.class);
    AssertAnnotations.assertField(
    		Contact.class, "firstName", Column.class);
    AssertAnnotations.assertField(
    		Contact.class, "lastName", Column.class);
    AssertAnnotations.assertField(
    		Contact.class, "phone", Column.class);
  }
  
  @Test
  public void entity() {
    // setup
    Entity a
    = ReflectTool.getClassAnnotation(Contact.class, Entity.class);
    // assert
    Assert.assertEquals("", a.name());
  }
  
  @Test
  public void table() {
    // setup
    Table t
    = ReflectTool.getClassAnnotation(Contact.class, Table.class);
    // assert
    Assert.assertEquals("phonebook", t.name());
  }
  
  @Test
  public void id() {
    // setup
    GeneratedValue a
    = ReflectTool.getFieldAnnotation(
    		Contact.class, "id", GeneratedValue.class);
    // assert
    Assert.assertEquals("", a.generator());
    Assert.assertEquals(GenerationType.AUTO, a.strategy());
  }
  
  @Test
  public void firstName() {
    // setup
    Column c
    = ReflectTool.getFieldAnnotation(
    		Contact.class, "firstName", Column.class);
    // assert
    Assert.assertEquals("firstname", c.name());
  }
  
  @Test
  public void lastName() {
    // setup
    Column c
    = ReflectTool.getFieldAnnotation(
    		Contact.class, "lastName", Column.class);
    
    // assert
    Assert.assertEquals("lastname", c.name());
  }
  
  @Test
  public void phone() {
    // setup
	  Column c
    = ReflectTool.getFieldAnnotation(
    		Contact.class, "phone", Column.class);
    // assert
    Assert.assertEquals("phone", c.name());
  }
}
