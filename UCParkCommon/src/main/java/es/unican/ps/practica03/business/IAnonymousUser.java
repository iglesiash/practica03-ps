package es.unican.ps.practica03.business;

import es.unican.ps.practica03.model.PaymentMethod;
import es.unican.ps.practica03.model.User;

public interface IAnonymousUser {
	public User login(User user);
	public User register(User user, PaymentMethod paymentMethod);
}
