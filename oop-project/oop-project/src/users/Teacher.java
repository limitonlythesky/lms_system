package users;

import java.io.Serializable;
import java.util.Vector;

import controllers.Controller;
import controllers.RunController;
import controllers.TeacherController;
import util.DataBase;
import util.File;
import util.Message;
import util.UserTypes;

public class Teacher extends Employee implements Serializable, RunController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1551540640458711310L;
	private TeacherRole teacherRole;
	public Vector<File> files = new Vector<File>();

	public Teacher(int id, String username, String password, String firstName, String lastName, int age, String address,
			String department, TeacherRole teacherRole, UserTypes userType) {
		super(id, username, password, firstName, lastName, age, address, department, userType);
		this.teacherRole = teacherRole;
	}

	
	public Vector<File> getFiles() {
		return files;
	}


	public void setFiles(Vector<File> files) {
		this.files = files;
	}


	public TeacherRole getTeacherRole() {
		return teacherRole;
	}

	public void setTeacherRole(TeacherRole teacherRole) {
		this.teacherRole = teacherRole;
	}

	@Override
	public String toString() {
		return super.toString() + " Teacher [teacherRole=" + teacherRole + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((teacherRole == null) ? 0 : teacherRole.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		if (teacherRole != other.teacherRole)
			return false;
		return true;
	}

	@Override
	public Controller getController(DataBase db) {
		return new TeacherController(db);
	}
	
}
