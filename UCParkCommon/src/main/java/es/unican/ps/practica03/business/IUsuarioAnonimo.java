package es.unican.ps.practica03.business;

import es.unican.ps.practica03.model.MedioPago;

public interface IUsuarioAnonimo {
	public void register(Vehiculo vehicle, MedioPago paymentMethod);
}
