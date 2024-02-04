package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Address;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentAddressDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.addAnnotatedClass(Address.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// create the objects			
			 Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");
			 Address homeAddress = new Address("Some Street 123", "12345", "Some City");
			
			 //2nd example:  Overriding embedded attributes
			 Address billingAddress = new Address("Some Billing Street 123", "67890", "Some Billing City");
			
			 tempStudent.setAddress(homeAddress);
			 
			 tempStudent.setBillingAddress(billingAddress);
			// start a transaction
			session.beginTransaction();
			
	
			// save the student object
			System.out.println("Saving the student and address...");
			session.persist(tempStudent);	
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			
			// add clean up code
			session.close();
			
			factory.close();
		}
	}

}





