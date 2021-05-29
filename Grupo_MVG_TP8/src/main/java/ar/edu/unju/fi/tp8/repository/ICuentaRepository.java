package ar.edu.unju.fi.tp8.repository;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tp8.model.Cuenta;

public interface ICuentaRepository extends CrudRepository<Cuenta, Long>{
	
	//public Cuenta findById(Long id);
	
}
