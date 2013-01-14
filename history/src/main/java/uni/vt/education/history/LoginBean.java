/*
 * LoginBean.java
 *
 * $Id$
 *
 * Copyright (c) 2001-2010 Administration Intelligence AG,
 * Steinbachtal 2b, 97082 Wuerzburg, Germany.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Administration Intelligence AG ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Administration Intelligence.
 */
package uni.vt.education.history;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import uni.vt.education.history.db.UserDAO;
import uni.vt.education.history.model.User;

/**
 * Controls the login process and stores the logged in user
 * 
 * @author a.vitkinov
 */
@SessionScoped
@ManagedBean
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 6704436797880373164L;
	private static final String SUCCESSFUL_LOGIN = "success";
	private static final String LOGOUT = "logout";
	public static final String SESSION_ID = "SESSION_ID";

    private UserDAO userDAO;

	private User user;
	private boolean logged;
	private String email;
	private String password;

	@PostConstruct
	public void postContruct() {
		user = new User();
		logged = false;
		 userDAO = new UserDAO();
		 System.out.println("Contructor - Login Bean");
	}

	public User getUser() {
		
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		System.out.println("set email");
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * The user logs into the system. He has access to all functionality after
	 * log in.
	 * 
	 * @return "successfulLogin" if the user has entered valid username and
	 *         password or <code>null</code> otherwise
	 */
	public String login() {
		String tResult = null;
//		MySQLAccess access = new MySQLAccess();
//		User tempUser = access.login(user);
//		if(tempUser.getName() != null){
//			user = tempUser;
//			setLogged(true);
//		}
		System.out.println("Login");
		User newuser = userDAO.getUser(user.getEmail(), user.getPassword());
		if(newuser != null){
			System.out.println(" logged in");
		}
		return tResult;
	}

	/**
	 * User logs out from the system and goes to the Bidding Home page
	 * 
	 * @return Logout the application
	 */
	public String logout() {
		ExternalContext tExternalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		
		final HttpServletRequest tRequest = (HttpServletRequest) tExternalContext
				.getRequest();
		tRequest.getSession(false).invalidate();

		return LOGOUT;
	}
}
