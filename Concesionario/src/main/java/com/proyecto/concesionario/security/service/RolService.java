package com.proyecto.concesionario.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.concesionario.security.entity.Rol;
import com.proyecto.concesionario.security.enums.RolNombre;
import com.proyecto.concesionario.security.repository.RolRepository;

import java.util.Optional;

@Service
@Transactional
public class RolService {

	@Autowired
	RolRepository rolRepository;

	public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
		return rolRepository.findByRolNombre(rolNombre);
	}

	public void save(Rol rol) {
		rolRepository.save(rol);
	}
}