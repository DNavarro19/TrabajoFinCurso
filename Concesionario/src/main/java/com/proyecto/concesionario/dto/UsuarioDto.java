package com.proyecto.concesionario.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioDto {

	@NotBlank
	private String nombre;
	@NotBlank
	private String apellidos;
	@NotBlank
	private String dni;
	@NotBlank
	private String nombreUsuario;
	@Email
	private String correo;
	@NotBlank
	private String contraseña;
	@NotNull
	private Integer idRol;

	public UsuarioDto() {
		super();
	}

	public UsuarioDto(@NotBlank String nombre, @NotBlank String apellidos, @NotBlank String dni,
			@NotBlank String nombreUsuario, @Email String correo, @NotBlank String contraseña, @NotNull Integer idRol) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.nombreUsuario = nombreUsuario;
		this.correo = correo;
		this.contraseña = contraseña;
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

}
