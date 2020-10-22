package org.bhavin.hibernate.demo;

import org.bhavin.hibernate.demo.entity.Course;
import org.bhavin.hibernate.demo.entity.Instructor;
import org.bhavin.hibernate.demo.entity.InstructorDetail;
import org.bhavin.hibernate.demo.entity.Review;
import org.bhavin.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentDemo {

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
			
			//create a course
			Course tempCourse = new Course("Football- How to become a star");
			
			System.out.println("\nSaving course...");
			session.save(tempCourse);
			
			//create students
			Student tempStudent1 = new Student("Bhavin","Ghoghari","bhavin@gmail.com");
			Student tempStudent2 = new Student("Viral","Parmar","viral@gmail.com");
			
			// add students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			//save the students
			System.out.println("\nSaving students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			
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
