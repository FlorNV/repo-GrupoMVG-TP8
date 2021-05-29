package ar.edu.unju.fi.tp8.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.tp8.model.Cliente;

public interface IClienteService {

	public void generarTablaClientes();
	public void guardarCliente(Cliente cliente);
	public List<Cliente> getClientes();
	public Cliente getCliente();
	
	public Cliente getClientePorDni(int nroDocumento);
	public Optional<Cliente> getClientePorId(Long id);
	public void eliminarCliente(Long id);
	
}
