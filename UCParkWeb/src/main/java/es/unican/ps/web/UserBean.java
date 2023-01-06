package es.unican.ps.web;

import com.google.inject.Inject;

import es.unican.ps.practica03.business.IAnonymousUserRemote;
import es.unican.ps.practica03.model.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UserBean {

	@Inject
	private IAnonymousUserRemote userManagement;
	
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
		if (userManagement.login(user) == null) {
			return "register.xhtml";
		}
		return "index.xhtml";
	}
}
