package com.proyecto.concesionario.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class MotorDto {

	@NotBlank
	private String nombre;
	@Min(0)
	private Integer precio;
	@Min(65)
	private Integer caballos;
	@NotBlank
	private String combustible;

	public MotorDto() {
	}

	public MotorDto(@NotBlank String nombre, @Min(0) Integer precio, @Min(65) Integer caballos,
			@NotBlank String combustible) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.caballos = caballos;
		this.combustible = combustible;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Integer getCaballos() {
		return caballos;
	}

	public void setCaballos(Integer caballos) {
		this.caballos = caballos;
	}

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

}