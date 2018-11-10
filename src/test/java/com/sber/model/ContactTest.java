package com.sber.model;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.sber.util.AssertAnnotations;
import com.sber.util.ReflectTool;
import com.sber.util.UtilTool;


public class ContactTest {
  
  /**
   * Test class type.
   */
	@Test
  public void testTypeAnnotations() {
    AssertAnnotations.assertType(
        Contact.class, Entity.class, Table.class);
  }
  
  /**
   *  Test class fields.
  */
  @Test
  public void testFieldAnnotations() {
    AssertAnnotations.assertField(
    		Contact.class, "id", Id.class, GeneratedValue.class);
    AssertAnnotations.assertField(
    		Contact.class, "firstName", Column.class);
    AssertAnnotations.assertField(
    		Contact.class, "lastName", Column.class);
    AssertAnnotations.assertField(
    		Contact.class, "phone", Column.class);
  }
  
  /**
   * Test entity.
   */
  @Test
  public void testEntity() {
    final Entity a
    = ReflectTool.getClassAnnotation(Contact.class, Entity.class);
    assertEquals("", a.name());
  }
  
  /**
   * Test table name.
   */
  @Test
  public void testTable() {
    final Table t
    = ReflectTool.getClassAnnotation(Contact.class, Table.class);
    assertEquals("phonebook", t.name());
  }
  
  /**
   * Test id field.
   */
  @Test
  public void testId() {
    final GeneratedValue a
    = ReflectTool.getFieldAnnotation(
    		Contact.class, "id", GeneratedValue.class);
    assertEquals("", a.generator());
    assertEquals(GenerationType.AUTO, a.strategy());
  }
  
  /**
   * Test First Name field.
   */
  @Test
  public void testFirstName() {
    final Column c
    = ReflectTool.getFieldAnnotation(
    		Contact.class, "firstName", Column.class);
    assertEquals("firstname", c.name());
  }
  
  /**
   * Test Last Name field.
   */
  @Test
  public void testLastName() {
    final Column c
    = ReflectTool.getFieldAnnotation(
    		Contact.class, "lastName", Column.class);
    assertEquals("lastname", c.name());
  }
  
  /**
   * Test phone field.
   */
  @Test
  public void testPhone() {
	  final Column c
    = ReflectTool.getFieldAnnotation(
    		Contact.class, "phone", Column.class);
    assertEquals("phone", c.name());
  }
  
  /**
   * Test contact builder.
   */
  @Test
  public void testPersonBuilder() {
      final long expectedId = new Random().nextLong();
      final Contact fromBuilder = UtilTool.createContact(expectedId);
      assertEquals(expectedId, fromBuilder.getId());

  }
  
  /**
   * Test contact constructor.
   */
  @Test
  public void testPersonConstructor() {
      final long expectedId = new Random().nextLong();
      final Contact fromNoArgConstructor = new Contact();
      fromNoArgConstructor.setId(expectedId);
      assertEquals(expectedId, fromNoArgConstructor.getId());
  }
  
  /**
   * Test toString() method.
   */
  @Test
  public void testToString() {
	  final long expectedId = new Random().nextLong();
	  final Contact contact = UtilTool.createContact(expectedId);
	  String expected = String.format("Contact(id=%d, firstName=Fred, lastName=Doe, phone=1 (555) 456-56-56)", expectedId);
	  assertEquals(expected, contact.toString());
  }
  
}
