package es.unican.ps.practica03.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity @Table(name = "Payment")
public abstract class PaymentMethod implements Serializable {
	@Id @GeneratedValue
	private long id;
	
}
