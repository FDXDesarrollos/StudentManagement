package com.fdxdesarrollos.studentManagement.model;

public class Login {
	private String user = "";
	private String pass = "";
	
	public Login() {
		super();
	}	
	
	public Login(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "Login [user=" + user + ", pass=" + pass + "]";
	}
	
}
