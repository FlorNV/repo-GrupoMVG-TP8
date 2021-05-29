package ar.edu.unju.fi.tp8.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp8.model.Cuenta;
import ar.edu.unju.fi.tp8.repository.ICuentaRepository;
import ar.edu.unju.fi.tp8.service.ICuentaService;

@Service("cuentaServiceMysql")
public class CuentaServiceMysqlImp implements ICuentaService {
	
	@Autowired
	private Cuenta cuenta;
	
	@Autowired
	private ICuentaRepository cuentaRepository;
	
	@Override
	public void guardarCuenta(Cuenta cuenta) {
		cuentaRepository.save(cuenta);
	}

	@Override
	public List<Cuenta> getCuentas() {
		List <Cuenta> cuentas = (List<Cuenta>) cuentaRepository.findAll();
		return cuentas;
	}

	@Override
	public Cuenta getCuenta() {
		return cuenta;
	}

}
