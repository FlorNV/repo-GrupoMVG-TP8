package ar.edu.unju.fi.tp8.service;

import java.util.List;

import ar.edu.unju.fi.tp8.model.Cuenta;

public interface ICuentaService {
	public void guardarCuenta(Cuenta cuenta);
	public List<Cuenta> getCuentas();
	
	public Cuenta getCuenta();

}
