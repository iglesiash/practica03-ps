package es.unican.ps.practica03.business;

import java.util.List;

public interface IUsuario {
	public List<Denuncia> consultReports();
	public List<Estacionamiento> consultEstacionamientos();
	public List<Vehiculo> consultVehicles();
}
