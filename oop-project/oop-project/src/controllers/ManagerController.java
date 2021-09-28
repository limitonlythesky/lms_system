package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import users.Student;
import users.Teacher;
import users.User;
import util.Course;
import util.DataBase;
import util.Message;

public class ManagerController extends Controller{

	public ManagerController(DataBase db) {
		super(db);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("Manager menu:\n"
				+ "1.view Students\n"
				+ "2.view Teachers\n"
				+ "3.Manage Courses\n"
				+ "4.Send Message to teacher\n"
				+ "5.View message from teachers\n"
				+ "6.View all messages\n"
				+ "7.Exit\n");
		String ans;
		try {
			ans = reader.readLine();
			switch(ans) {
			case "1":
				getDb().viewStudents();
				break;
			case "2":
				getDb().viewTeachers();
				break;
			case "3":
				getDb().manageCourses();
				break;
			case "4":
				sendMessage();
				break;
			case "5":
				viewMessageFromTeachers();
				break;
			case "6":
				getDb().viewAllMessage();
				break;
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	public void viewMessageFromTeachers() throws NumberFormatException, IOException {
		System.out.println("Choose teaher by id:\n");
		getDb().viewTeachers();
		int id = Integer.parseInt(reader.readLine());
		for(Message message : getDb().getMessagesByReceiverId(id)) {
			System.out.println(message);
		}
	}
	
	public void sendMessage() throws NumberFormatException, IOException {
		System.out.println("Choose a target teacher by id");
		getDb().viewTeachers();
		int recieverId = Integer.parseInt(reader.readLine());
		System.out.println("Enter your id");
		int senderId = Integer.parseInt(reader.readLine());
		String time = (new Date()).toString();
		System.out.println("Enter message text");
		String text = reader.readLine();
		getDb().addMessage(new Message(senderId, recieverId, time, text));
	}
	
}
