package com.proyecto.concesionario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.concesionario.entity.Venta;
import com.proyecto.concesionario.repository.VentaRepository;
import com.proyecto.concesionario.security.entity.Usuario;

@Service
@Transactional
public class VentaService {

	@Autowired
	VentaRepository ventaRepository;

	public List<Venta> list() {
		return ventaRepository.findAll();
	}

	public Optional<Venta> getOne(int id) {
		return ventaRepository.findById(id);
	}

	public List<Venta> listByUsuario(Usuario usuario) {
		return ventaRepository.findAllByUsuario(usuario);
	}

	public void save(Venta vehiculo) {
		ventaRepository.save(vehiculo);
	}

	public void delete(int id) {
		ventaRepository.deleteById(id);
	}

	public boolean existsById(int id) {
		return ventaRepository.existsById(id);
	}

	public boolean existsByUsuario(Usuario usuario) {
		return ventaRepository.existsByUsuario(usuario);
	}
}
