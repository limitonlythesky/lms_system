package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

import users.Admin;
import users.Manager;
import users.Student;
import users.Teacher;
import users.TeacherRole;
import users.TechSupportGuy;
import users.User;
import util.DataBase;
import util.UserTypes;

public class AdminController extends Controller{

	public AdminController(DataBase db) {
		super(db);
	}

	public void run() {
		System.out.println("Admin menu:\n"
				+ "1.Add user\n"
				+ "2.Update user\n"
				+ "3.Remove user\n"
				+ "4.Exit");
		String ans;
		try {
			ans = reader.readLine();
			switch(ans) {
			case "1":
				addUser(getDb(), reader);
				break;
			case "2":
				updateUser(getDb(), reader);
				break;
			case "3":
				removeUser(getDb(), reader);
				break;
			case "4":
				break;
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addUser(DataBase db, BufferedReader reader) throws IOException {
		int id = (new Random()).nextInt(10000000);
		System.out.println("Enter username");
		String userName = reader.readLine();
		System.out.println("Enter password");
		String password = reader.readLine();
		System.out.println("Enter first name");
		String firstName = reader.readLine();
		System.out.println("Enter last name");
		String lastName = reader.readLine();
		System.out.println("Enter age");
		int age = Integer.parseInt(reader.readLine());
		System.out.println("Enter address");
		String address = reader.readLine();
		System.out.println("Choose user type: \n"
				+ "1.ADMIN\n"
				+ "2.TEACHER\n"
				+ "3.MANAGER\n"
				+ "4.STUDENT\n"
				+ "5.TECHSUPPORTGUY\n");
		String ans = reader.readLine();
				
		UserTypes type = null;
		String department = "";
		User user = null;
		switch(ans) {
		case "1":
			type = UserTypes.ADMIN;
			System.out.println("Enter your department");
			department = reader.readLine();
			user = new Admin(id, userName, password, firstName, lastName, age, address, department, type);
			break;
		case "2":
			type = UserTypes.TEACHER;
			System.out.println("Enter your department");
			department = reader.readLine();
			System.out.println("Choose teacher role: \n"
					+ "1.TUTOR\n"
					+ "2.SENIOR_LECTOR\n"
					+ "3.LECTOR\n"
					+ "4.PROFESSOR\n");
			String teacherRole = reader.readLine();
			TeacherRole role = null;
			switch(teacherRole) {
			case "1":
				role = TeacherRole.TUTOR;
				break;
			case "2":
				role = TeacherRole.SENIOR_LECTOR;
				break;
			case "3":
				role = TeacherRole.LECTOR;
				break;
			case "4":
				role = TeacherRole.PROFESSOR;
				break;
			}
			user = new Teacher(id, userName, password, firstName, lastName,age, address, department, role, type);
			break;
		case "3":
			type = UserTypes.MANAGER;
			System.out.println("Enter your department");
			department = reader.readLine();
			user = new Manager(id, userName, password, firstName, lastName, age, address, department, type);
			break;
		case "4":
			type = UserTypes.STUDENT;
			System.out.println("Enter specialty");
			String specialty = reader.readLine();
			user = new Student(id, userName, password, firstName, lastName, age, address, specialty, 1, type);
			break;
		case "5":
			type = UserTypes.TECHSUPPORTGUY;
			System.out.println("Enter your department");
			department = reader.readLine();
			user = new TechSupportGuy(id, userName, password, firstName, lastName, age, address, department, type);
			break;
		}
		
		db.addUser(user);
		System.out.println(user.toString());
	}
	
	
	public void updateUser(DataBase db, BufferedReader reader) throws NumberFormatException, IOException {
		System.out.println("Choose user from the list by id\n");
		for(User user : db.getUsers()) {
			System.out.println(user.getId() + " " + user.toString());
		}
		int id = Integer.parseInt(reader.readLine());
		User user = db.getUserById(id);
		System.out.println("Choose what you want to change:"
				+ "1.userName\n"
				+ "2.password\n"
				+ "3.firstName\n"
				+ "4.lastName\n"
				+ "5.address");
		String ans = reader.readLine();
		db.getUsers().remove(user);
		switch(ans) {
			case "1":
				String userName = reader.readLine();
				user.setUsername(userName);
				break;
			case "2":
				String password = reader.readLine();
				user.setPassword(password);
				break;
			case "3":
				String firstName = reader.readLine();
				user.setFirstName(firstName);
				break;
			case "4":
				String lastName = reader.readLine();
				user.setLastName(lastName);
				break;
			case "5":
				String address = reader.readLine();
				user.setAddress(address);
		}
		db.getUsers().add(user);
	}
	
	public void removeUser(DataBase db, BufferedReader reader) throws NumberFormatException, IOException {
		System.out.println("Choose user from the list\n");
		for(User user : db.getUsers()) {
			System.out.println(user.getId() + " " + user.toString());
		}
		System.out.println("Choose user by id");
		int id = Integer.parseInt(reader.readLine());
		User user = db.getUserById(id);
		db.getUsers().remove(user);
		System.out.println("User " + user.getFirstName() + " was removed" );
	}
	
	

}
