package ar.edu.unju.fi.tp8.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp8.model.Producto;
import ar.edu.unju.fi.tp8.service.IProductoService;
import ar.edu.unju.fi.tp8.util.TablaProductos;

@Service("productoUtilService")
public class ProductoServiceImp implements IProductoService{

	private static final Log LOGGER = LogFactory.getLog(ProductoServiceImp.class);
	private List<Producto> productos = new ArrayList<Producto>();

	@Override
	public void generarTablaProductos() {
		productos = TablaProductos.registrosProductos;
		LOGGER.info("METHOD: generarTablaClientes ---- Se genero la tabla de productos");
	}
	
	@Override
	public void agregarProducto(Producto producto) {
		if(productos == null) {
			generarTablaProductos();
		}
		productos.add(producto);
		LOGGER.info("METHOD: agregarProducto ---- Se agrego un objeto producto en la lista: "+productos.get(productos.size()-1));
		
	}

	@Override
	public List<Producto> getProductos() {
		return productos;
	}
	
	@Override
	public Producto consultarUltimo() {
		
		Producto ultimo = productos.get(productos.size()-1);
		LOGGER.info("METHOD: consultarUltimo ---- Se devuelve un objeto producto de la lista: "+ultimo);
		return ultimo;
		
	}

	@Override
	public Optional<Producto> getProductoPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarProducto(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}
