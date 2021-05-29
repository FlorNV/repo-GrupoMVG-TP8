package ar.edu.unju.fi.tp8.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

//import ar.edu.unju.fi.tp8.model.Cliente;
import ar.edu.unju.fi.tp8.model.Cuenta;
import ar.edu.unju.fi.tp8.service.IClienteService;
import ar.edu.unju.fi.tp8.service.ICuentaService;

@Controller
public class CuentaController {
	
	@Autowired
	private Cuenta cuenta;
	
	@Autowired
	@Qualifier("cuentaServiceMysql")
	private ICuentaService cuentaService;
	
	@Autowired
	@Qualifier("clienteServiceMysql")
	private IClienteService clienteService;
	
	@GetMapping("/cuenta/nueva")
	public String getCuentaNuevaPage(Model model) {
		model.addAttribute(cuenta);
		return "nuevacuenta";
	}
	
	@PostMapping("/cuenta/guardar")
	public ModelAndView guardarCuentaPage(@ModelAttribute("cuenta") Cuenta cuenta) {
		ModelAndView model = new ModelAndView("cuentas");
		//Cliente cliente = clienteService.getClientePorDocumento(cuenta.getCliente().getNroDocumento());
		//cuenta.setCliente(cliente);
		cuentaService.guardarCuenta(cuenta);
		model.addObject("cuentas", cuentaService.getCuentas());
		return model;
	}
	
	@GetMapping("/cuenta/listado")
	public ModelAndView getListadoCuentaPage() {
		ModelAndView model = new ModelAndView("cuentas");
		model.addObject("cuentas", cuentaService.getCuentas());
		return model;
	}
}
