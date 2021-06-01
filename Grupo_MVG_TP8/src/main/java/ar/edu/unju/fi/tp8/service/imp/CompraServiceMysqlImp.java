package ar.edu.unju.fi.tp8.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp8.model.Compra;
import ar.edu.unju.fi.tp8.repository.ICompraRepository;
import ar.edu.unju.fi.tp8.service.ICompraService;

@Service("compraServiceMysql")
public class CompraServiceMysqlImp implements ICompraService {
	
	@Autowired
	private ICompraRepository compraRepository;

	@Override
	public void generarTablaCompras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardarCompra(Compra compra) {
		compraRepository.save(compra);
		
	}

	@Override
	public List<Compra> getCompras() {
		List<Compra> compras = (List<Compra>) compraRepository.findAll();
		return compras;
	}

	@Override
	public Compra consultarUltimaCompra() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Compra> getCompraPorId(Long id) {
		Optional<Compra> compra = compraRepository.findById(id);
		return compra;
	}
	
	@Override
	public void eliminarCompra(Long id) {
		compraRepository.deleteById(id);
	}

	@Override
	public List<Compra> consultarCompras(String nombreProducto, double monto) {
		List<Compra> compras = new ArrayList<Compra>();
		if(!nombreProducto.isEmpty() && monto>=0)
			compras = compraRepository.findByProductoNombreAndTotalGreaterThanEqual(nombreProducto, monto);
		else
			if(nombreProducto.isEmpty() && monto>=0)
				compras = compraRepository.findByTotalGreaterThanEqual(monto);
		return compras;
	}

}
