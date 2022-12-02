package es.unican.ps.practica03.business;

public interface IVehiculo {
	public Vehiculo registerVehiculo(Vehiculo vehicle);
	public Vehiculo deleteVehiculo(String matricula);
}
