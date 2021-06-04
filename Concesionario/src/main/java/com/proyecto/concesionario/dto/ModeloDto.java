package com.proyecto.concesionario.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ModeloDto {

	@NotBlank
	private String nombre;
	@Min(0)
	private Integer precio;

	public ModeloDto() {
	}

	public ModeloDto(@NotBlank String nombre, @Min(0) Integer precio) {
		this.nombre = nombre;
		this.precio = precio;
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
}