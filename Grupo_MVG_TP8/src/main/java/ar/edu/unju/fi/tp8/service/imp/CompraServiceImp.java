package ar.edu.unju.fi.tp8.service.imp;


import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp8.model.Compra;
import ar.edu.unju.fi.tp8.service.ICompraService;
import ar.edu.unju.fi.tp8.util.TablaCompras;

@Service("compraUtilService")
public class CompraServiceImp implements ICompraService {

	private static final Log LOGGER = LogFactory.getLog(CompraServiceImp.class);
	private List<Compra> compras;
	
	@Override
	public void generarTablaCompras() {
		compras = TablaCompras.registrosCompras;
		LOGGER.info("METHOD: generarTablaCompras ---- Se genero la tabla de compras");

	}

	@Override
	public void guardarCompra(Compra compra) {
		if(compras == null)
			generarTablaCompras();
		compras.add(compra);
		LOGGER.info("METHOD: guardarCompra ---- Se agrego un objeto compra en la lista: " + compra);

	}

	@Override
	public List<Compra> getCompras() {
		return compras;
	}

	@Override
	public Compra consultarUltimaCompra() {
		Compra ultimo = compras.get(compras.size()-1);
		LOGGER.info("METHOD: consultarUltimaCompra ---- Se devuelve un objeto compra de la lista: "+ultimo);
		return ultimo;
	}

	@Override
	public void eliminarCompra(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Compra> getCompraPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
