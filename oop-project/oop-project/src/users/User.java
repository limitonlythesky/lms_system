package users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

import controllers.Controller;
import controllers.RunController;
import util.DataBase;
import util.UserTypes;

public abstract class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 280124068468303301L;
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	private String address;
	private UserTypes userType;
	
	public User(int id, String username, String password, String firstName, String lastName, int age, String address, UserTypes userType) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
		this.userType = userType;
	}
	
	public static Controller login(DataBase db, BufferedReader reader) throws IOException {
		System.out.println("Enter you username");
		String username = reader.readLine();
		if(username.equals("q")) {
			System.exit(0);
		}
		User user = getUserByUsername(db, username);
		if(user != null) {
			System.out.println("Enter your password");
			String password = reader.readLine();
			if(user.getPassword().equals(password)) {
				System.out.println("Welcome " + user.getUsername());
				return getUserController(user, db);
			} else {
				System.out.println("Wrong password");
			}
		} else {
			System.out.println("User does not exist");
		}
		return null;
	}
	
	public static Controller getUserController(User user, DataBase db) {
		Controller controller = null;
		switch(user.getUserType()) {
			case ADMIN:
				controller = ((Admin)user).getController(db);
				break;
			case TEACHER:
				controller = ((Teacher)user).getController(db);
				break;
			case STUDENT:
				controller = ((Student) user).getController(db);
				break;
			case MANAGER:
				controller = ((Manager)user).getController(db);
				break;
			case TECHSUPPORTGUY :
				controller = ((TechSupportGuy)user).getController(db);
				break;
		}
		return controller;
	}
	
	public static User getUserByUsername(DataBase db, String username) {
		for(User user : db.getUsers()) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	public UserTypes getUserType() {
		return userType;
	}

	public void setUserType(UserTypes userType) {
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + age;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age != other.age)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", age=" + age + ", address=" + address + "]";
	}
	
	
	
}
