package users;

import java.io.Serializable;

import controllers.AdminController;
import controllers.Controller;
import controllers.RunController;
import util.DataBase;
import util.UserTypes;

public class Admin extends Employee implements Serializable, RunController{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8239281137299230180L;

	public Admin(int id, String username, String password, String firstName, String lastName, int age, String address,
			String department, UserTypes userType) {
		super(id, username, password, firstName, lastName, age, address, department, userType);
	}

	@Override
	public Controller getController(DataBase db) {
		return new AdminController(db);
	}
}
