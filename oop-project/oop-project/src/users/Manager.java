package users;

import java.io.Serializable;

import controllers.Controller;
import controllers.ManagerController;
import controllers.RunController;
import util.DataBase;
import util.UserTypes;

public class Manager extends Employee implements Serializable, RunController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -543201900065333012L;

	public Manager(int id, String username, String password, String firstName, String lastName, int age, String address,
			String department, UserTypes userType) {
		super(id, username, password, firstName, lastName, age, address, department, userType);
	}

	@Override
	public Controller getController(DataBase db) {
		return new ManagerController(db);
	}

}
