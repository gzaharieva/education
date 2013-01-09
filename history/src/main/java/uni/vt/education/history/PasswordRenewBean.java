/*
 * PasswordRenewBean.java
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
import javax.faces.bean.ViewScoped;

/**
 * Controls password restoration. Send an email with a link in order user to reset the password.
 * 
 * @author g.zaharieva
 * 
 */
@ViewScoped
@ManagedBean
public class PasswordRenewBean implements Serializable
{

  private static final long serialVersionUID = -100874172946613913L;

  private static final String SUCCESS = "success";
  private static final String FAILURE = "failure";
  private final String EMAIL_PATTERN =
    "^(([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+([;.](([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+)*$";

  @PostConstruct
  public void postContruct()
  {
  }

  private String iEmail;

  /**
   * @return the email of user
   */
  public String getEmail()
  {
    return iEmail;
  }

  /**
   * @param anEmail the email of user to set
   */
  public void setEmail(String anEmail)
  {
    this.iEmail = anEmail;
  }

  public String getEMAIL_PATTERN()
  {
    return EMAIL_PATTERN;
  }

  /**
   * Send a new password to introduced e-mail.
   * 
   * @return success and redirect user to login page if e-mail is sent successfully, otherwise
   * return error message to user.
   */
  public String passwordRenew()
  {

    return SUCCESS;
  }
}
