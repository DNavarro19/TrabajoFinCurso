package com.proyecto.concesionario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.concesionario.entity.Alquiler;
import com.proyecto.concesionario.repository.AlquilerRepository;
import com.proyecto.concesionario.security.entity.Usuario;

@Service
@Transactional
public class AlquilerService {

	@Autowired
	AlquilerRepository alquilerRepository;

	public List<Alquiler> list() {
		return alquilerRepository.findAll();
	}

	public Optional<Alquiler> getOne(int id) {
		return alquilerRepository.findById(id);
	}

	public List<Alquiler> listByUsuario(Usuario usuario) {
		return alquilerRepository.findAllByUsuario(usuario);
	}

	public void save(Alquiler vehiculo) {
		alquilerRepository.save(vehiculo);
	}

	public void delete(int id) {
		alquilerRepository.deleteById(id);
	}

	public boolean existsById(int id) {
		return alquilerRepository.existsById(id);
	}

	public boolean existsByUsuario(Usuario usuario) {
		return alquilerRepository.existsByUsuario(usuario);
	}
}
