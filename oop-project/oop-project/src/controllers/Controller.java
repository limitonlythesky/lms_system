package controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import util.DataBase;

public abstract class Controller {
	private DataBase db;
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	Controller(DataBase db) {
		this.db = db;
	}
	
	public abstract void run();

	public DataBase getDb() {
		return db;
	}

	public void setDb(DataBase db) {
		this.db = db;
	}
	
	
}
