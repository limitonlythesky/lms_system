package util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Vector;

import users.Manager;
import users.Student;
import users.Teacher;
import users.TechSupportGuy;
import users.User;

public class DataBase {
	private static HashSet<User> users = new HashSet<User>();
	private static HashSet<Course> courses = new HashSet<Course>();
	private static HashSet<Message> messages = new HashSet<Message>();
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DataBase database = new DataBase();
	
	private DataBase() {}
	
	public static DataBase getInstance(){
		return database;
	}
	
	public static HashSet<User> getUsers() {
		return users;
	}
	
	public static HashSet<Course> getCourses() {
		return courses;
	}

	public static HashSet<Message> getMessages() {
		return messages;
	}
	
	public static void setUsers(HashSet<User> newUsers) {
		users = newUsers;
	}
	
	public static void setCourses(HashSet<Course> newCourses) {
		courses = newCourses;
	}
	
	public static void setMessage(HashSet<Message> newMessages) {
		messages = newMessages;
	}
	
	public static void addCourse(Course course) {
		courses.add(course);
	}
	
	public static void addUser(User user) {
		users.add(user);
	}
	
	public static void addMessage(Message message) {
		messages.add(message);
	}
	
	public User getUserByUsername(String username) {
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	public void viewStudents() {
		for(User user : users) {
			if(user instanceof Student) {
				System.out.println((Student)user);
			}
		}
	}
	
	public void viewTeachers() {
		for(User user : users) {
			if( user instanceof Teacher) {
				System.out.println((Teacher)user);
			}
		}
	}
	
	public void viewStudent() {
		for(User user : users) {
			if(user instanceof Student) {
				System.out.println((Student)user);
			}
		}
	}
	public void viewManagers() {
		for(User user : users) {
			if(user instanceof Manager) {
				System.out.println((Manager)user);
			}
		}
	}
	
	public void viewItGuys() {
		for(User user : users) {
			if( user instanceof TechSupportGuy) {
				System.out.println((TechSupportGuy)user);
			}
		}
	}
	public void viewCourses() {
		for(Course course : courses) {
			System.out.println(course);
		}
	}
	
	public void viewAllMessage() {
		for(Message message : messages) {
			System.out.println(message);
		}
	}
	
	public void addCourse() throws IOException {
		System.out.println("Enter the course name");
		String courseName = reader.readLine();
		System.out.println("Enter the number of credits");
		Integer nOfCredits = Integer.parseInt(reader.readLine());
		System.out.println("Choose teacher by id");
		viewTeachers();
		int id = Integer.parseInt(reader.readLine());
		Teacher teacher = (Teacher)getUserById(id);
		addCourse(new Course(courseName, nOfCredits, teacher));
	}
	
	public void deleteCourse() throws IOException {
		System.out.println("Choose course by name");
		viewCourses();
		String name = reader.readLine();
		Course course = getCourseWithName(name);
		courses.remove(course);
	}
	
	public void updateCourse() throws IOException {
		System.out.println("Choose the course by name");
		viewCourses();
		String name = reader.readLine();
		Course course = getCourseWithName(name);
		courses.remove(course);
		System.out.println("Enter the field that needs be changed:\n"
				+ "1.courseName\n"
				+ "2.nOfCredits\n"
				+ "3.teacher\n");
		String ans = reader.readLine();
		switch(ans) {
			case "1":
				System.out.println("Enter a new name");
				String courseName = reader.readLine();
				course.setName(courseName);
				break;
			case "2":
				System.out.println("Etner a number of credits");
				int nOfCredits = Integer.parseInt(reader.readLine());
				course.setCreditNumber(nOfCredits);
				break;
			case "3":
				System.out.println("Choose teacher by id");
				viewTeachers();
				int id = Integer.parseInt(reader.readLine());
				Teacher teacher = (Teacher)getUserById(id);
				course.setTeacher(teacher);
				break;
		}
		addCourse(course);
	}
	
	public void manageCourses() throws IOException {
		System.out.println("Courses menu:\n"
				+ "1. View Courses\n"
				+ "2. Add Course\n"
				+ "3. Delete course\n"
				+ "4. Update Course\n");
		String ans = reader.readLine();
		switch(ans) {
			case "1":
				viewCourses();
				break;
			case "2":
				addCourse();
				break;
			case "3":
				deleteCourse();
				break;
			case "4":
				updateCourse();
				break;
		}
	}
	
	public static Message getMessageBySenderId(int id) {
		for(Message message : messages) {
			if(message.getSenderId() == id) {
				return message;
			}
		}
		return null;
	}
	
	public static Vector<Message> getMessagesByReceiverId(int id) {
		Vector<Message> messages = new Vector<Message>();
		for(Message message : messages) {
			if(message.getReceiverId() == id) {
				messages.add(message);
			}
		}
		return messages;
	}
	
	public User getUserById(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public Course getCourseWithName(String name) {
		for(Course course : courses) {
			if(course.getName().equals(name)) {
				return course;
			}
		}
		return null;
	}
	
	public void saveData() throws FileNotFoundException, IOException {
		final ObjectOutputStream outCourses = new ObjectOutputStream(
		        new BufferedOutputStream(new FileOutputStream("courses.ser")));
		final ObjectOutputStream outUsers = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("users.ser")));
		final ObjectOutputStream outMessages = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("messages.ser")));
		    try {
		      outCourses.writeObject(courses);
		      outUsers.writeObject(users);
		      outMessages.writeObject(messages);
		    } finally {
		      outCourses.close();
		      outUsers.close();
		      outMessages.close();
		    }
	}
	
	public static void retrieveData() throws FileNotFoundException, IOException, ClassNotFoundException {
		@SuppressWarnings("resource")
		final ObjectInputStream inCourses = new ObjectInputStream(
		        new BufferedInputStream(new FileInputStream("courses.ser")));
		final ObjectInputStream inUsers = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("users.ser")));
		final ObjectInputStream inMessages = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("messages.ser")));
		
		users = (HashSet<User>) inUsers.readObject();
		courses = (HashSet<Course>) inCourses.readObject();
		messages = (HashSet<Message>) inMessages.readObject();
	}
}
