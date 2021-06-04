package com.proyecto.concesionario.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.proyecto.concesionario.security.entity.Rol;
import com.proyecto.concesionario.security.enums.RolNombre;
import com.proyecto.concesionario.security.service.RolService;

@Component
public class CreateRoles implements CommandLineRunner {

	@Autowired
	RolService rolService;

	@Override
	public void run(String... args) throws Exception {

//		Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
//		Rol rolUser = new Rol(RolNombre.ROLE_USER);
//		rolService.save(rolAdmin);
//		rolService.save(rolUser);

	}
}