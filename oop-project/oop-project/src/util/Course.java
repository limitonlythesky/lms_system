package util;

import java.io.Serializable;
import java.util.HashSet;

import users.Student;
import users.Teacher;

public class Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2432988780406704349L;
	private String name;
	private Integer creditNumber;
	private Teacher teacher;
	private HashSet<Student> students;
	
	public Course(String name, Integer creditNumber, Teacher teacher) {
		super();
		this.name = name;
		this.creditNumber = creditNumber;
		this.teacher = teacher;
		this.students = new HashSet<Student>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCreditNumber() {
		return creditNumber;
	}

	public void setCreditNumber(Integer creditNumber) {
		this.creditNumber = creditNumber;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public HashSet<Student> getStudents() {
		return students;
	}

	public void setStudents(HashSet<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + ", creditNumber=" + creditNumber + ", teacher=" + teacher + ", students="
				+ students + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditNumber == null) ? 0 : creditNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((students == null) ? 0 : students.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
		Course other = (Course) obj;
		if (creditNumber == null) {
			if (other.creditNumber != null)
				return false;
		} else if (!creditNumber.equals(other.creditNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (students == null) {
			if (other.students != null)
				return false;
		} else if (!students.equals(other.students))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}
	
	
}
