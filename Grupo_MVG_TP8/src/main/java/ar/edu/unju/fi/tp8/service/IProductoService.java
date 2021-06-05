package ar.edu.unju.fi.tp8.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.tp8.model.Producto;

public interface IProductoService {
	
	public void generarTablaProductos();
	public void agregarProducto(Producto producto);
	public List<Producto> getProductos();
	public Producto consultarUltimo();

	public Optional<Producto> getProductoPorId(Long id);
	public void eliminarProducto(Long id);
}
