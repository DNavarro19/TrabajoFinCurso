package com.proyecto.concesionario.dto;

import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

public class VehiculoSeminuevoDto {

	@NotBlank
	private String matricula;
	@Past
	private Date año;
	@NotBlank
	private String nombre;
	@NotBlank
	private String marca;
	@NotBlank
	private String modelo;
	@Min(65)
	private Integer caballos;
	@NotBlank
	private String combustible;
	@Positive
	private Integer kilometraje;
	@NotNull
	private boolean alquilable;
	@NotNull
	private boolean alquilado;
	@Min(0)
	private Integer precio;
	@NotNull
	private boolean activo;

	public VehiculoSeminuevoDto() {
	}

	public VehiculoSeminuevoDto(@NotBlank String matricula, @Past Date año, @NotBlank String nombre,
			@NotBlank String marca, @NotBlank String modelo, @Min(65) Integer caballos, @NotBlank String combustible,
			@Positive Integer kilometraje, @NotNull boolean alquilable, @NotNull boolean alquilado,
			@Min(0) Integer precio, @NotNull boolean activo) {
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

	public Integer getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(Integer kilometraje) {
		this.kilometraje = kilometraje;
	}

	public boolean isAlquilable() {
		return alquilable;
	}

	public void setAlquilable(boolean alquilable) {
		this.alquilable = alquilable;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}