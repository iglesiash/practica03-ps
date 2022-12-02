package es.unican.ps.practica03.business;

import es.unican.ps.practica03.common.*;

public interface IDenuncia {
	public void reportEstacionamiento(Vehiculo vehicle, String description, double price);
	public void voidReport(Denuncia report);
}
