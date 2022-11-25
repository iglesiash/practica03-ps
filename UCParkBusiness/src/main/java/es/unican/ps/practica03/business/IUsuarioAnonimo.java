package es.unican.ps.practica03.business;

import es.unican.ps.practica03.common.MedioPago;

public interface IUsuarioAnonimo {
	public void register(Vehiculo vehicle, MedioPago paymentMethod);
}
