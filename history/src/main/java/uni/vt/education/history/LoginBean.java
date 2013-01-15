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
import java.sql.SQLException;

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
	public static final String SESSION_ID = "SESSION_ID";

	private UserDAO userDAO;

	private User user;
	private boolean logged;

	@PostConstruct
	public void postContruct() {
		user = new User();
		logged = false;
		userDAO = new UserDAO();
		System.out.println("LoginBean init");
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
	 * The user logs into the system. He has access to all functionality after
	 * log in.
	 * 
	 * @return "successfulLogin" if the user has entered valid username and
	 *         password or <code>null</code> otherwise
	 */
	public String login() {
		System.out.println("Login: " + user.getEmail());
		String tResult = null;
		User tempUser = userDAO.getUser(user.getEmail(), user.getPassword());

		if (tempUser != null) {
			user = tempUser;
			setLogged(true);
		}

		return tResult;
	}

	public String register() {
		String tResult = null;
		
		try {
			userDAO.addUser(user);
			tResult = "index";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		setLogged(false);
		return null;
	}
}
