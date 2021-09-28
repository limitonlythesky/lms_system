package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import users.Student;
import users.Teacher;
import util.Course;
import util.DataBase;
import util.File;
import util.Grade;

public class StudentController extends Controller{

	public StudentController(DataBase db) {
		super(db);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("Choose an option:\n"
				+ "1.registerForCourse\n"
				+ "2.view courses files\n"
				+ "3.view Courses info\n"
				+ "4.view Transcripts");
		String ans;
		try {
			ans = reader.readLine();
			switch(ans) {
			case "1":
				registerForCourse();
				break;
			case "2":
				viewFiles();
				break;
			case "3":
				viewCoursesInfo();
				break;
			case "4":
				viewTranscripts();
				break;
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void registerForCourse() throws IOException {
		for(Course course : getDb().getCourses()) {
			System.out.println(course);
		}
		System.out.println("Choose course by course name");
		String name = reader.readLine();
		Course course = getDb().getCourseWithName(name);
		System.out.println("Enter your username");
		String username = reader.readLine();
		Student student = (Student) getDb().getUserByUsername(username);
		getDb().getUsers().remove(student);
		Grade startGrade = new Grade("", 0.0);
		student.getGrades().put(course, startGrade);
		getDb().getUsers().add(student);
	}
	
	public void viewCoursesInfo() {
		for(Course course : getDb().getCourses()) {
			System.out.println(course);
		}
	}
	
	public void viewTranscripts() throws IOException {
		System.out.println("Enter you username");
		String username = reader.readLine();
		Student student = (Student) getDb().getUserByUsername(username);
		for(Map.Entry<Course, Grade> entry : student.getGrades().entrySet()) {
			System.out.println(entry.getKey().getName() + " - " + entry.getValue().getTextGrade());
		}
	}
	
	public void viewFiles() throws IOException {
		System.out.println("Enter your username");
		String username = reader.readLine();
		Student student = (Student) getDb().getUserByUsername(username);
		for(Map.Entry<Course, Grade> entry : student.getGrades().entrySet()) {
			Teacher teacher = entry.getKey().getTeacher();
			for(File file : teacher.getFiles()) {
				System.out.println(file);
			}
		}
	}
	
}
