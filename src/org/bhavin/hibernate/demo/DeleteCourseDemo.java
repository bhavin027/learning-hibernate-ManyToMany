package org.bhavin.hibernate.demo;

import org.bhavin.hibernate.demo.entity.Course;
import org.bhavin.hibernate.demo.entity.Instructor;
import org.bhavin.hibernate.demo.entity.InstructorDetail;
import org.bhavin.hibernate.demo.entity.Review;
import org.bhavin.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		//use the session object to save java object
		try {
			
			//start transaction
			session.beginTransaction();
			
			//get the course
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			
			//delete the course
			System.out.println("Deleting course..."+tempCourse);
			session.delete(tempCourse);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
