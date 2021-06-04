package com.proyecto.concesionario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.concesionario.entity.VentaSeminuevo;
import com.proyecto.concesionario.repository.VentaSeminuevoRepository;
import com.proyecto.concesionario.security.entity.Usuario;

@Service
@Transactional
public class VentaSeminuevoService {

	@Autowired
	VentaSeminuevoRepository ventaSeminuevoRepository;

	public List<VentaSeminuevo> list() {
		return ventaSeminuevoRepository.findAll();
	}

	public List<VentaSeminuevo> listUsuario(Usuario usuario) {
		return ventaSeminuevoRepository.findAllByUsuario(usuario);
	}

	public Optional<VentaSeminuevo> getOne(int id) {
		return ventaSeminuevoRepository.findById(id);
	}

	public void save(VentaSeminuevo vehiculo) {
		ventaSeminuevoRepository.save(vehiculo);
	}

	public void delete(int id) {
		ventaSeminuevoRepository.deleteById(id);
	}

	public boolean existsById(int id) {
		return ventaSeminuevoRepository.existsById(id);
	}

	public boolean existsByUsuario(Usuario usuario) {
		return ventaSeminuevoRepository.existsByUsuario(usuario);
	}
}
