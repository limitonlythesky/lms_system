package users;

import java.io.Serializable;

import controllers.Controller;
import controllers.RunController;
import controllers.TechSupportGuyController;
import util.DataBase;
import util.UserTypes;

public class TechSupportGuy extends Employee implements Serializable, RunController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9146968291993582018L;

	public TechSupportGuy(int id, String username, String password, String firstName, String lastName, int age,
			String address, String department, UserTypes userType) {
		super(id, username, password, firstName, lastName, age, address, department, userType);
	}

	@Override
	public Controller getController(DataBase db) {
		// TODO Auto-generated method stub
		return new TechSupportGuyController(db);
	}

}
