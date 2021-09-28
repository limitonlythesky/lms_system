package controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import users.Student;
import users.Teacher;
import users.User;
import util.Course;
import util.DataBase;
import util.File;
import util.Grade;
import util.Message;
import util.Order;
import util.OrderStatus;

public class TeacherController extends Controller {

	public TeacherController(DataBase db) {
		super(db);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("Choose an option:\n"
				+ "1.put marks\n"
				+ "2.send order to it\n"
				+ "3.Talk to managers\n"
				+ "4.generate report\n"
				+ "5.manage Courses\n"
				+ "6.add file\n");
		String ans;
		try {
			ans = reader.readLine();
			switch(ans) {
			case "1":
				putMarks();
				break;
			case "2":
				sendOrderToIt();
				break;
			case "3":
				talkToManagers();
				break;
			case "4":
				generateReport();
				break;
			case "5":
				getDb().manageCourses();
				break;
			case "6":
				addFiles();
				break;
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void putMarks() throws IOException {
		System.out.println("Write username");
		String teacherUsername = reader.readLine();
		Teacher teacher = (Teacher) getDb().getUserByUsername(teacherUsername);
		getDb().viewStudent();
		System.out.println("Write student username");
		String username = reader.readLine();
		Student student = (Student) getDb().getUserByUsername(username);
		getDb().getUsers().remove(student);
		for(Map.Entry<Course, Grade> entry : student.getGrades().entrySet()) {
			if(entry.getKey().getTeacher().equals(teacher)) {
				System.out.println(entry.getKey());
			}
		}
		System.out.println("Enter a course name");
		String courseName = reader.readLine();
		Course course = getDb().getCourseWithName(courseName);
		student.getGrades().remove(course);
		System.out.println("Write course text grade");
		String textGrade = reader.readLine();
		System.out.println("Write course gpa");
		Double gpa = Double.parseDouble(reader.readLine());
		Grade grade = new Grade(textGrade, gpa);
		student.getGrades().put(course, grade);
		getDb().getUsers().add(student);
	}
	
	public void addFiles() throws IOException {
		System.out.println("Enter your username");
		String username = reader.readLine();
		Teacher teacher = (Teacher) getDb().getUserByUsername(username);
		getDb().getUsers().remove(teacher);
		System.out.println("Write name for you file");
		String name = reader.readLine();
		System.out.println("Write file text");
		String text = reader.readLine();
		File file = new File(name, text);
		teacher.getFiles().add(file);
		getDb().getUsers().add(teacher);
	}
	
	public void generateReport() throws NumberFormatException, IOException {
		System.out.println("Enter your id");
		int id = Integer.parseInt(reader.readLine());
		Teacher teacher = (Teacher)getDb().getUserById(id);
		PrintWriter writer = new PrintWriter(teacher.getUsername() + "_" + teacher.getId() + ".txt", "UTF-8");
		for(Course course : getDb().getCourses()) {
			if(course.getTeacher().equals(teacher)) {
				writer.println(course.toString());
			}
		}
		writer.close();
	}
	
	public void sendOrderToIt() throws NumberFormatException, IOException {
		int orderId = (new Random()).nextInt(1000000);
		getDb().viewItGuys();
		System.out.println("Choose it guy by id");
		int recieverId = Integer.parseInt(reader.readLine());
		System.out.println("Enter your id");
		int senderId = Integer.parseInt(reader.readLine());
		String time = (new Date()).toString();
		System.out.println("Enter your message");
		String text = reader.readLine();
		Order order = new Order(orderId, senderId, recieverId, time, text, OrderStatus.NEW);
		getDb().addMessage(order);
	}
	
	public void talkToManagers() throws NumberFormatException, IOException {
		viewMessageFromManagers();
		sendMessage();
	}

	
	public void sendMessage() throws NumberFormatException, IOException {
		System.out.println("Choose a target manager by id");
		getDb().viewManagers();
		int recieverId = Integer.parseInt(reader.readLine());
		System.out.println("Enter your id");
		int senderId = Integer.parseInt(reader.readLine());
		String time = (new Date()).toString();
		System.out.println("Enter message text");
		String text = reader.readLine();
		getDb().addMessage(new Message(senderId, recieverId, time, text));
	}
	
	public void viewMessageFromManagers() throws NumberFormatException, IOException {
		System.out.println("Choose Manager by id:\n");
		getDb().viewManagers();
		int id = Integer.parseInt(reader.readLine());
		for(Message message : getDb().getMessagesByReceiverId(id)) {
			System.out.println(message);
		}
	}
}
