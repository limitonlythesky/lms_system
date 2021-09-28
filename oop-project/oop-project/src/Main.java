import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import controllers.Controller;
import users.Admin;
import users.Student;
import users.User;
import util.Course;
import util.DataBase;
import util.Message;
import util.UserTypes;

public class Main {
	
	public static void main(String [] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Admin admin = new Admin(1, "admin", "1234", "test", "test",123, "test", "test", UserTypes.ADMIN);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		DataBase db = DataBase.getInstance();

		db.retrieveData();
		
		db.addUser(admin);
		while(true) {
			System.out.println("Welcome. Please enter your username or press q to quit");
			
			Controller controller = User.login(db, reader);
			if(controller != null) {
				controller.run();
				db.saveData();
			}
		}
	}
}
