package es.unican.ps.web;

import java.io.Serializable;

import es.unican.ps.practica03.business.IAnonymousUserRemote;
import es.unican.ps.practica03.model.User;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class UserBean implements Serializable {

	@EJB
	private IAnonymousUserRemote anonymousUserRemote;
	
	private String email;
	private String password;
	private User user;
	
	public UserBean() { }
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		user = new User(email, password);
		if (anonymousUserRemote.login(user) == null) {
			return "register.xhtml";
		}
		return "login.xhtml";
	}
	
	public String register() {
		return "login.xhtml";
	} 
}
