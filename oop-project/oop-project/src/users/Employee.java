package users;

import java.io.Serializable;

import controllers.Controller;
import util.DataBase;
import util.UserTypes;

public class Employee extends User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7951151700468979620L;
	private String department;

	public Employee(int id, String username, String password, String firstName, String lastName, int age, String address, String department, UserTypes userType) {
		super(id, username, password, firstName, lastName, age, address, userType);
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((department == null) ? 0 : department.hashCode());
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
		Employee other = (Employee) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + "Employee [department=" + department + "]";
	}
}
