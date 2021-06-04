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
import com.proyecto.concesionario.dto.VentaSeminuevoDto;
import com.proyecto.concesionario.entity.VehiculoSeminuevo;
import com.proyecto.concesionario.entity.VentaSeminuevo;
import com.proyecto.concesionario.security.entity.Usuario;
import com.proyecto.concesionario.security.service.UsuarioService;
import com.proyecto.concesionario.service.VehiculoSeminuevoService;
import com.proyecto.concesionario.service.VentaSeminuevoService;

@RestController
@RequestMapping("/ventaSeminuevo")
@CrossOrigin(origins = "http://localhost:8100")
public class VentaSeminuevoController {

	@Autowired
	VentaSeminuevoService ventaService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	VehiculoSeminuevoService vehiculoService;

	@GetMapping("/lista")
	public ResponseEntity<List<VentaSeminuevo>> list() {
		List<VentaSeminuevo> list = ventaService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<VentaSeminuevo> getById(@PathVariable("id") int id) {
		if (!ventaService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		VentaSeminuevo venta = ventaService.getOne(id).get();
		return new ResponseEntity(venta, HttpStatus.OK);
	}

	@GetMapping("/listByUsuario/{nombreUsuario}")
	public ResponseEntity<VentaSeminuevo> getByIdUsuario(@PathVariable("nombreUsuario") String nombreUsuario) {
		if (!usuarioService.existsByNombreUsuario(nombreUsuario))
			return new ResponseEntity(new Mensaje("El usuario no existe"), HttpStatus.NOT_FOUND);
		Usuario user = usuarioService.getByNombreUsuario(nombreUsuario).get();
		if (!ventaService.existsByUsuario(user))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		List<VentaSeminuevo> list = ventaService.listUsuario(user);
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody VentaSeminuevoDto ventaDto) {
		if (ventaDto.getIdVehiculoSeminuevo() == null || !vehiculoService.existsById(ventaDto.getIdVehiculoSeminuevo()))
			return new ResponseEntity(new Mensaje("El vehiculo no existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(ventaDto.getNombreUsuario())
				|| !usuarioService.existsByNombreUsuario(ventaDto.getNombreUsuario()))
			return new ResponseEntity(new Mensaje("El usuario no existe"), HttpStatus.BAD_REQUEST);
		if (ventaDto.getPrecio() == null || ventaDto.getPrecio() < 0)
			return new ResponseEntity(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
		Usuario user = usuarioService.getByNombreUsuario(ventaDto.getNombreUsuario()).get();
		VehiculoSeminuevo vehiculo = vehiculoService.getOne(ventaDto.getIdVehiculoSeminuevo()).get();
		VentaSeminuevo venta = new VentaSeminuevo(ventaDto.isCancelada(), ventaDto.isFinalizada(),
				ventaDto.getPrecio());
		venta.setUsuario(user);
		venta.setVehiculoSeminuevo(vehiculo);
		ventaService.save(venta);
		return new ResponseEntity(new Mensaje("Venta de seminuevo creada"), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody VentaSeminuevoDto ventaDto) {
		VentaSeminuevo venta = ventaService.getOne(id).get();
		if (venta.isCancelada())
			return new ResponseEntity(new Mensaje("Esta venta ya ha sido cancelada"), HttpStatus.BAD_REQUEST);
		if (venta.isFinalizada())
			return new ResponseEntity(new Mensaje("Esta venta ya ha sido finalizada"), HttpStatus.BAD_REQUEST);

		venta.setCancelada(ventaDto.isCancelada());
		venta.setFinalizada(ventaDto.isFinalizada());
		ventaService.save(venta);
		return new ResponseEntity(new Mensaje("Venta de seminuevo actualizada"), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!ventaService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		ventaService.delete(id);
		return new ResponseEntity(new Mensaje("Venta de seminuevo eliminada"), HttpStatus.OK);
	}

}
