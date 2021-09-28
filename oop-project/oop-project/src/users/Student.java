package users;

import java.io.Serializable;
import java.util.HashMap;

import controllers.Controller;
import controllers.RunController;
import controllers.StudentController;
import util.Course;
import util.DataBase;
import util.Grade;
import util.UserTypes;

public class Student extends User implements Serializable, RunController{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4443805682515913155L;
	private String specialty;
	private int yearOfStudy;
	private HashMap<Course, Grade> grades = new HashMap<Course, Grade>();;
	
	public Student(int id, String username, String password, String firstName, String lastName, int age,
			String address, String specialty, int yearOfStudy, UserTypes userType) {
		super(id, username, password, firstName, lastName, age, address, userType);
		this.yearOfStudy = yearOfStudy;
		this.grades = new HashMap<Course, Grade>();
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public int getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public HashMap<Course, Grade> getGrades() {
		return grades;
	}

	public void setGrades(HashMap<Course, Grade> grades) {
		this.grades = grades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grades == null) ? 0 : grades.hashCode());
		result = prime * result + ((specialty == null) ? 0 : specialty.hashCode());
		result = prime * result + yearOfStudy;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (grades == null) {
			if (other.grades != null)
				return false;
		} else if (!grades.equals(other.grades))
			return false;
		if (specialty == null) {
			if (other.specialty != null)
				return false;
		} else if (!specialty.equals(other.specialty))
			return false;
		if (yearOfStudy != other.yearOfStudy)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + "Student [specialty=" + specialty + ", yearOfStudy=" + yearOfStudy + ", grades=" + grades + "]";
	}

	@Override
	public Controller getController(DataBase db) {
		return new StudentController(db);
	}	
	
}
