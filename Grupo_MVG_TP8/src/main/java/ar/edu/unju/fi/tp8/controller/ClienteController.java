package ar.edu.unju.fi.tp8.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp8.model.Cliente;
//import ar.edu.unju.fi.tp8.model.Cuenta;
import ar.edu.unju.fi.tp8.service.IClienteService;
import ar.edu.unju.fi.tp8.service.ICuentaService;

@Controller
public class ClienteController {
	
	@Autowired
	@Qualifier("clienteServiceMysql")
	private IClienteService clienteService;
	
	@Autowired
	@Qualifier("cuentaServiceMysql")
	private ICuentaService cuentaService;
	
	//@Autowired
	//private Cuenta cuenta;
	
	@GetMapping("/cliente/nuevo")
	public String getNuevoCliente(Model model) {
		model.addAttribute("cliente", clienteService.getCliente());
		//model.addAttribute("cuenta", cuenta);
		return "nuevocliente";
	}
	
	@PostMapping("/cliente/guardar")
	public ModelAndView guardarClientePage(@Valid @ModelAttribute("cliente")Cliente cliente, BindingResult resultadoValidacion) {
		ModelAndView model;
		if(resultadoValidacion.hasErrors()) {
			model = new ModelAndView("nuevocliente");
			return model;
		}
		else {
			model = new ModelAndView("clientes");
			clienteService.guardarCliente(cliente);
			model.addObject("clientes", clienteService.getClientes());
			return model;
		}
		
	}
	
	@GetMapping("/cliente/listado")
	public ModelAndView getListadoPage() {
		ModelAndView model = new ModelAndView("clientes");
		if(clienteService.getClientes() == null)
			clienteService.generarTablaClientes();
		model.addObject("clientes", clienteService.getClientes());
		return model;
	}
	
	@GetMapping("/cliente/editar/{id}")
	public ModelAndView getEditClientePage(@PathVariable(value = "id")Long id) {
		ModelAndView model = new ModelAndView("nuevocliente");
		Optional<Cliente> cliente = clienteService.getClientePorId(id);
		model.addObject("cliente", cliente);
		return model;
	}
	
	@GetMapping("/cliente/eliminar/{id}")
	public ModelAndView getProductoEliminar(@PathVariable(value = "id")Long id) {
		ModelAndView model = new ModelAndView("redirect:/cliente/listado");
		clienteService.eliminarCliente(id);
		return model;
	}
}
