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
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.BindingProvider;

import de.sepp.bidding.service.InterfaceCallException_Exception;
import de.sepp.bidding.service.SessionHandlingWS;
import de.sepp.bidding.service.SessionHandlingWSService;

/**
 * Controls the login process and stores the logged in user
 * 
 * @author a.vitkinov
 */
@SessionScoped
@ManagedBean
public class LoginBean implements Serializable
{

  private static final long serialVersionUID = 6704436797880373164L;
  private static final String SUCCESSFUL_LOGIN = "success";
  private static final String LOGOUT = "logout";
  public static final String SESSION_ID = "SESSION_ID";

  private String iSessionID;
  private String iUsername;
  private String iPassword;
  private SessionHandlingWS iPort;

  @PostConstruct
  public void postContruct()
  {
    iPort = new SessionHandlingWSService().getSessionHandlingWSPort();
  }

  /**
   * Get username
   * 
   * @return the Username
   */
  public String getUsername()
  {
    return iUsername;
  }

  /**
   * Set username
   * 
   * @param aUsername the Username to set
   */
  public void setUsername(String aUsername)
  {
    this.iUsername = aUsername;
  }

  /**
   * Get password
   * 
   * @return the Password
   */
  public String getPassword()
  {
    return iPassword;
  }

  /**
   * Set password
   * 
   * @param aPassword the iPassword to set
   */
  public void setPassword(String aPassword)
  {
    this.iPassword = aPassword;
  }

  /**
   * The user logs into the system. He has access to all functionality after log in.
   * 
   * @return "successfulLogin" if the user has entered valid username and password or
   * <code>null</code> otherwise
   */
  public String login()
  {
    String tResult;

    Map<String, Object> tRequestContext = ((BindingProvider) iPort).getRequestContext();
    tRequestContext.put(BindingProvider.USERNAME_PROPERTY, "FDLWSUser");
    tRequestContext.put(BindingProvider.PASSWORD_PROPERTY, "init");
    try
    {
      iSessionID = iPort.login(iUsername, iPassword);
      tResult = SUCCESSFUL_LOGIN;
    }
    catch (InterfaceCallException_Exception e)
    {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login fail", ""));
      tResult = "";
    }

    return tResult;
  }

  /**
   * User logs out from the system and goes to the Bidding Home page
   * 
   * @return Logout the application
   */
  public String logout()
  {
    ExternalContext tExternalContext = FacesContext.getCurrentInstance().getExternalContext();
    if (SESSION_ID != null && SESSION_ID.isEmpty())
    {
      iSessionID = tExternalContext.getSessionMap().get(SESSION_ID).toString();
      // iPort.logout(iSessionID);
    }
    final HttpServletRequest tRequest = (HttpServletRequest) tExternalContext.getRequest();
    tRequest.getSession(false).invalidate();

    return LOGOUT;
  }
}
