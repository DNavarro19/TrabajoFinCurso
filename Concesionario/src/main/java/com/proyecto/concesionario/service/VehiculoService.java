package com.proyecto.concesionario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.concesionario.entity.Vehiculo;
import com.proyecto.concesionario.repository.VehiculoRepository;

@Service
@Transactional
public class VehiculoService {

    @Autowired
    VehiculoRepository vehiculoRepository;

	public List<Vehiculo> list(){
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> getOne(int id){
        return vehiculoRepository.findById(id);
    }

    public Optional<Vehiculo> getByNombre(String nombre){
        return vehiculoRepository.findByNombre(nombre);
    }

    public void  save(Vehiculo vehiculo){
    	vehiculoRepository.save(vehiculo);
    }

    public void delete(int id){
    	vehiculoRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return vehiculoRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return vehiculoRepository.existsByNombre(nombre);
    }
}
