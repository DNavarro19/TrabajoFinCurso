package com.proyecto.concesionario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.concesionario.entity.VehiculoSeminuevo;
import com.proyecto.concesionario.repository.VehiculoSeminuevoRepository;

@Service
@Transactional
public class VehiculoSeminuevoService {

	@Autowired
	VehiculoSeminuevoRepository vehiculoSeminuevoRepository;

	public List<VehiculoSeminuevo> list() {
		return vehiculoSeminuevoRepository.findAll();
	}

	public List<VehiculoSeminuevo> listAlquilables(boolean alquilable) {
		return vehiculoSeminuevoRepository.findAllByAlquilable(alquilable);
	}

	public List<VehiculoSeminuevo> listActivos(boolean activo) {
		return vehiculoSeminuevoRepository.findAllByActivo(activo);
	}

	public Optional<VehiculoSeminuevo> getOne(int id) {
		return vehiculoSeminuevoRepository.findById(id);
	}

	public Optional<VehiculoSeminuevo> getByMatricula(String matricula) {
		return vehiculoSeminuevoRepository.findByMatricula(matricula);
	}

	public void save(VehiculoSeminuevo vehiculoSeminuevo) {
		vehiculoSeminuevoRepository.save(vehiculoSeminuevo);
	}

	public void delete(int id) {
		vehiculoSeminuevoRepository.deleteById(id);
	}

	public boolean existsById(int id) {
		return vehiculoSeminuevoRepository.existsById(id);
	}

	public boolean existsByMatricula(String matricula) {
		return vehiculoSeminuevoRepository.existsByMatricula(matricula);
	}
}
