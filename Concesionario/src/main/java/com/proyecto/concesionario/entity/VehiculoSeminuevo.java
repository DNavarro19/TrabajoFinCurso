package com.proyecto.concesionario.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VehiculoSeminuevo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String matricula;
	private Date año;
	private String nombre;
	private String marca;
	private String modelo;
	private int caballos;
	private String combustible;
	private int kilometraje;
	private boolean alquilable;
	private boolean alquilado;
	private int precio;
	private boolean activo;

	public VehiculoSeminuevo(String matricula, Date año, String nombre, String marca, String modelo, int caballos,
			String combustible, int kilometraje, boolean alquilable, boolean alquilado, int precio, boolean activo) {
		super();
		this.matricula = matricula;
		this.año = año;
		this.nombre = nombre;
		this.marca = marca;
		this.modelo = modelo;
		this.caballos = caballos;
		this.combustible = combustible;
		this.kilometraje = kilometraje;
		this.alquilable = alquilable;
		this.alquilado = alquilado;
		this.precio = precio;
		this.activo = activo;
	}

	public VehiculoSeminuevo() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAlquilado() {
		return alquilado;
	}

	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getAño() {
		return año;
	}

	public void setAño(Date año) {
		this.año = año;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getCaballos() {
		return caballos;
	}

	public void setCaballos(int caballos) {
		this.caballos = caballos;
	}

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	public int getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(int kilometraje) {
		this.kilometraje = kilometraje;
	}

	public boolean isAlquilable() {
		return alquilable;
	}

	public void setAlquilable(boolean alquilable) {
		this.alquilable = alquilable;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
