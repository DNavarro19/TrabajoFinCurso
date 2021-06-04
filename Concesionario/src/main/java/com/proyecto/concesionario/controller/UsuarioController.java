package com.proyecto.concesionario.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.concesionario.dto.Mensaje;
import com.proyecto.concesionario.entity.Motor;
import com.proyecto.concesionario.security.entity.Usuario;
import com.proyecto.concesionario.security.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:8100")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/{nombreUsuario}")
	public ResponseEntity<Usuario> getById(@PathVariable("nombreUsuario") String nombreUsuario) {
		if (!usuarioService.existsByNombreUsuario(nombreUsuario))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Usuario usuario = usuarioService.getByNombreUsuario(nombreUsuario).get();
		return new ResponseEntity(usuario, HttpStatus.OK);
	}
}
