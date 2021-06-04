package com.proyecto.concesionario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.concesionario.entity.Modelo;
import com.proyecto.concesionario.repository.ModeloRepository;

@Service
@Transactional
public class ModeloService {

	@Autowired
	ModeloRepository modeloRepository;

	public List<Modelo> list() {
		return modeloRepository.findAll();
	}

	public Optional<Modelo> getOne(int id) {
		return modeloRepository.findById(id);
	}

	public Optional<Modelo> getByNombre(String nombre) {
		return modeloRepository.findByNombre(nombre);
	}

	public void save(Modelo modelo) {
		modeloRepository.save(modelo);
	}

	public void delete(int id) {
		modeloRepository.deleteById(id);
	}

	public boolean existsById(int id) {
		return modeloRepository.existsById(id);
	}

	public boolean existsByNombre(String nombre) {
		return modeloRepository.existsByNombre(nombre);
	}
}
