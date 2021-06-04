package com.proyecto.concesionario.controller;

import java.util.ArrayList;
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
import com.proyecto.concesionario.dto.VehiculoDto;
import com.proyecto.concesionario.dto.VehiculoSeminuevoDto;
import com.proyecto.concesionario.entity.Vehiculo;
import com.proyecto.concesionario.entity.VehiculoSeminuevo;
import com.proyecto.concesionario.service.VehiculoSeminuevoService;
import com.proyecto.concesionario.service.VehiculoService;

@RestController
@RequestMapping("/vehiculoSeminuevo")
@CrossOrigin(origins = "http://localhost:8100")
public class VehiculoSeminuevoController {

	@Autowired
	VehiculoSeminuevoService vehiculoSeminuevoService;

	@GetMapping("/lista")
	public ResponseEntity<List<VehiculoSeminuevo>> list() {
		List<VehiculoSeminuevo> list = vehiculoSeminuevoService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@GetMapping("/listaAlquilables")
	public ResponseEntity<List<VehiculoSeminuevo>> listAlquilables() {
		List<VehiculoSeminuevo> listAux = vehiculoSeminuevoService.listAlquilables(true);
		List<VehiculoSeminuevo> list = new ArrayList<>();
		for (VehiculoSeminuevo vehiculoSeminuevo : listAux) {
			if (!vehiculoSeminuevo.isAlquilado()) {
				list.add(vehiculoSeminuevo);
			}
		}
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<VehiculoSeminuevo> getById(@PathVariable("id") int id) {
		if (!vehiculoSeminuevoService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		VehiculoSeminuevo vehiculo = vehiculoSeminuevoService.getOne(id).get();
		return new ResponseEntity(vehiculo, HttpStatus.OK);
	}

	@GetMapping("/detailmatricula/{matricula}")
	public ResponseEntity<VehiculoSeminuevo> getByNombre(@PathVariable("matricula") String matricula) {
		if (!vehiculoSeminuevoService.existsByMatricula(matricula))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		VehiculoSeminuevo vehiculo = vehiculoSeminuevoService.getByMatricula(matricula).get();
		return new ResponseEntity(vehiculo, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody VehiculoSeminuevoDto vehiculoSeminuevoDto) {
		if (StringUtils.isBlank(vehiculoSeminuevoDto.getNombre()))
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(vehiculoSeminuevoDto.getMarca()))
			return new ResponseEntity(new Mensaje("La marca es obligatoria"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(vehiculoSeminuevoDto.getModelo()))
			return new ResponseEntity(new Mensaje("El modelo es obligatorio"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(vehiculoSeminuevoDto.getCombustible()))
			return new ResponseEntity(new Mensaje("El tipo de combustible es obligatorio"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(vehiculoSeminuevoDto.getMatricula()))
			return new ResponseEntity(new Mensaje("La matricula es obligatorio"), HttpStatus.BAD_REQUEST);
		if (vehiculoSeminuevoDto.getPrecio() == null || vehiculoSeminuevoDto.getPrecio() < 0)
			return new ResponseEntity(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
		if (vehiculoSeminuevoService.existsByMatricula(vehiculoSeminuevoDto.getMatricula()))
			return new ResponseEntity(new Mensaje("Esa matricula ya existe"), HttpStatus.BAD_REQUEST);
		if (vehiculoSeminuevoDto.getAño() == null)
			return new ResponseEntity(new Mensaje("El año de compra es obligatorio"), HttpStatus.BAD_REQUEST);
		if (vehiculoSeminuevoDto.getKilometraje() == null || vehiculoSeminuevoDto.getKilometraje() < 0)
			return new ResponseEntity(new Mensaje("El kilometraje debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
		if (vehiculoSeminuevoDto.getCaballos() == null || vehiculoSeminuevoDto.getCaballos() < 65)
			return new ResponseEntity(new Mensaje("Los caballos deben ser mayor que 65"), HttpStatus.BAD_REQUEST);
		VehiculoSeminuevo vehiculo = new VehiculoSeminuevo(vehiculoSeminuevoDto.getMatricula(),
				vehiculoSeminuevoDto.getAño(), vehiculoSeminuevoDto.getNombre(), vehiculoSeminuevoDto.getMarca(),
				vehiculoSeminuevoDto.getModelo(), vehiculoSeminuevoDto.getCaballos(),
				vehiculoSeminuevoDto.getCombustible(), vehiculoSeminuevoDto.getKilometraje(),
				vehiculoSeminuevoDto.isAlquilable(), vehiculoSeminuevoDto.isAlquilado(),
				vehiculoSeminuevoDto.getPrecio(), vehiculoSeminuevoDto.isActivo());
		vehiculoSeminuevoService.save(vehiculo);
		return new ResponseEntity(new Mensaje("Vehiculo creado"), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id,
			@RequestBody VehiculoSeminuevoDto vehiculoSeminuevoDto) {
		if (!vehiculoSeminuevoService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(vehiculoSeminuevoDto.getNombre()))
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(vehiculoSeminuevoDto.getMarca()))
			return new ResponseEntity(new Mensaje("La marca es obligatoria"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(vehiculoSeminuevoDto.getModelo()))
			return new ResponseEntity(new Mensaje("El modelo es obligatorio"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(vehiculoSeminuevoDto.getCombustible()))
			return new ResponseEntity(new Mensaje("El tipo de combustible es obligatorio"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(vehiculoSeminuevoDto.getMatricula()))
			return new ResponseEntity(new Mensaje("La matricula es obligatorio"), HttpStatus.BAD_REQUEST);
		if (vehiculoSeminuevoDto.getPrecio() == null || vehiculoSeminuevoDto.getPrecio() < 0)
			return new ResponseEntity(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
		if (vehiculoSeminuevoService.existsByMatricula(vehiculoSeminuevoDto.getMatricula()))
			return new ResponseEntity(new Mensaje("Esa matricula ya existe"), HttpStatus.BAD_REQUEST);
		if (vehiculoSeminuevoDto.getAño() == null)
			return new ResponseEntity(new Mensaje("El año de compra es obligatorio"), HttpStatus.BAD_REQUEST);
		if (vehiculoSeminuevoDto.getKilometraje() == null || vehiculoSeminuevoDto.getKilometraje() < 0)
			return new ResponseEntity(new Mensaje("El kilometraje debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
		if (vehiculoSeminuevoDto.getCaballos() == null || vehiculoSeminuevoDto.getCaballos() < 65)
			return new ResponseEntity(new Mensaje("Los caballos deben ser mayor que 65"), HttpStatus.BAD_REQUEST);
		if (vehiculoSeminuevoService.existsByMatricula(vehiculoSeminuevoDto.getMatricula())
				&& vehiculoSeminuevoService.getByMatricula(vehiculoSeminuevoDto.getMatricula()).get().getId() != id)
			return new ResponseEntity(new Mensaje("Esa matricula ya existe"), HttpStatus.BAD_REQUEST);

		VehiculoSeminuevo vehiculo = vehiculoSeminuevoService.getOne(id).get();
		vehiculo.setNombre(vehiculoSeminuevoDto.getNombre());
		vehiculo.setPrecio(vehiculoSeminuevoDto.getPrecio());
		vehiculo.setMatricula(vehiculoSeminuevoDto.getMatricula());
		vehiculo.setCombustible(vehiculoSeminuevoDto.getCombustible());
		vehiculo.setCaballos(vehiculoSeminuevoDto.getCaballos());
		vehiculo.setKilometraje(vehiculoSeminuevoDto.getKilometraje());
		vehiculo.setMarca(vehiculoSeminuevoDto.getMarca());
		vehiculo.setModelo(vehiculoSeminuevoDto.getModelo());
		vehiculo.setActivo(vehiculoSeminuevoDto.isActivo());
		vehiculo.setAlquilable(vehiculoSeminuevoDto.isAlquilable());
		vehiculo.setAlquilado(vehiculoSeminuevoDto.isAlquilado());
		vehiculoSeminuevoService.save(vehiculo);
		return new ResponseEntity(new Mensaje("Vehiculo actualizado"), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!vehiculoSeminuevoService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		vehiculoSeminuevoService.delete(id);
		return new ResponseEntity(new Mensaje("Vehiculo eliminado"), HttpStatus.OK);
	}

}
