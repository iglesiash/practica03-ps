package es.unican.ps.practica03.business;

import es.unican.ps.practica03.common.*;

public interface IEstacionamiento {

	public Estacionamiento consultEstacionamiento(String matricula);
	public void registerEstacionamiento(Vehiculo vehicle);
	public Estacionamiento extendParkingTime(String idEstacionamiento, int minutes);
	public void finishEstacionamiento(String idEstacionamiento);
}
