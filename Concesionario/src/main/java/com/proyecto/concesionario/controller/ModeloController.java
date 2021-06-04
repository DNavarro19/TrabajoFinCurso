package com.proyecto.concesionario.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.concesionario.dto.Mensaje;
import com.proyecto.concesionario.dto.ModeloDto;
import com.proyecto.concesionario.entity.Modelo;
import com.proyecto.concesionario.service.ModeloService;

@RestController
@RequestMapping("/modelo")
@CrossOrigin(origins = "http://localhost:8100")
public class ModeloController {

	@Autowired
	ModeloService modeloService;

	@GetMapping("/lista")
	public ResponseEntity<List<Modelo>> list() {
		List<Modelo> list = modeloService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<Modelo> getById(@PathVariable("id") int id) {
		if (!modeloService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Modelo modelo = modeloService.getOne(id).get();
		return new ResponseEntity(modelo, HttpStatus.OK);
	}

	@GetMapping("/detailname/{nombre}")
	public ResponseEntity<Modelo> getByNombre(@PathVariable("nombre") String nombre) {
		if (!modeloService.existsByNombre(nombre))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Modelo modelo = modeloService.getByNombre(nombre).get();
		return new ResponseEntity(modelo, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ModeloDto modeloDto) {
		if (StringUtils.isBlank(modeloDto.getNombre()))
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (modeloDto.getPrecio() == null || modeloDto.getPrecio() < 0)
			return new ResponseEntity(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
		if (modeloService.existsByNombre(modeloDto.getNombre()))
			return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		Modelo modelo = new Modelo(modeloDto.getNombre(), modeloDto.getPrecio());
		modeloService.save(modelo);
		return new ResponseEntity(new Mensaje("Modelo creado"), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ModeloDto modeloDto) {
		if (!modeloService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(modeloDto.getNombre()))
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (modeloService.existsByNombre(modeloDto.getNombre())
				&& modeloService.getByNombre(modeloDto.getNombre()).get().getId() != id)
			return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (modeloDto.getPrecio() == null || modeloDto.getPrecio() < 0)
			return new ResponseEntity(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

		Modelo modelo = modeloService.getOne(id).get();
		modelo.setNombre(modeloDto.getNombre());
		modelo.setPrecio(modeloDto.getPrecio());
		modeloService.save(modelo);
		return new ResponseEntity(new Mensaje("Modelo actualizado"), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!modeloService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		modeloService.delete(id);
		return new ResponseEntity(new Mensaje("Modelo eliminado"), HttpStatus.OK);
	}

}
