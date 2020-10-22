package org.bhavin.hibernate.demo;

import org.bhavin.hibernate.demo.entity.Course;
import org.bhavin.hibernate.demo.entity.Instructor;
import org.bhavin.hibernate.demo.entity.InstructorDetail;
import org.bhavin.hibernate.demo.entity.Review;
import org.bhavin.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCourseForStudentDemo {

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
			
			// get student from db
			int theId=1;	
			Student tempStudent = session.get(Student.class, theId);
			System.out.println("Student: "+tempStudent);
			
			// create more courses
			System.out.println("\nCreating Courses\n");
			Course tempCourse1 = new Course("Guitar- Tings of the Strings");
			Course tempCourse2 = new Course("Amazing String Work");
			
			//add student to those courses
			System.out.println("Adding students to the course");
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			
			//save the course
			System.out.println("\nSaving the Courses...");
			session.save(tempCourse1);
			session.save(tempCourse2);
			
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
