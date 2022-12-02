package es.unican.ps.practica03.business;

public interface IDenuncia {
	public void reportEstacionamiento(Vehiculo vehicle, String description, double price);
	public void voidReport(Denuncia report);
}
