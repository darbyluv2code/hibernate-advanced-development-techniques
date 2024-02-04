package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Status;
import com.luv2code.hibernate.demo.entity.User;

public class CreateUserDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(User.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// create the objects			
			
			 User student1 = new User("Mary", "Public", "paul@luv2code.com",Status.ACTIVE);
			 User student2 = new User("John", "Doe", "john@luv2code.com",Status.INACTIVE);
			 			 
			 
			// start a transaction
			session.beginTransaction();
			
	
			// save the student object
			System.out.println("Saving the users...");
			session.persist(student1);
			session.persist(student2);
			
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





