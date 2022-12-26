package es.unican.ps.practica03.business;

import es.unican.ps.practica03.model.*;

public interface IAnonymousUser {
	public void register(User user, PaymentMethod paymentMethod);
}
