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

import ar.edu.unju.fi.tp8.model.Producto;
import ar.edu.unju.fi.tp8.service.IProductoService;

@Controller
public class ProductoController {

	@Autowired
	private Producto producto;
	
	@Autowired
	@Qualifier("productoServiceMysql")
	private IProductoService productoService;
	
	@GetMapping("/producto/nuevo")
	public String getProductoPage(Model model) {
		model.addAttribute(producto);
		return "nuevoproducto";
	}
	
	@PostMapping("/producto/guardar")
	public ModelAndView guardarProducto(@Valid @ModelAttribute("producto") Producto producto, BindingResult resultadoValidacion) {
		ModelAndView model;
		if(resultadoValidacion.hasErrors()) {
			model = new ModelAndView("nuevoproducto");
			return model;
		}
		else {
			productoService.agregarProducto(producto);
			model = new ModelAndView("resultado");
			return model;
		}
	}
	
	@GetMapping("/producto/listado")
	public ModelAndView getListadoPage() {
		ModelAndView model = new ModelAndView("productos");
		if(productoService.getProductos() == null)
			productoService.generarTablaProductos();
		model.addObject("productos", productoService.getProductos());
		return model;
	}
	
	@GetMapping("/producto/ultimo")
	public String getUltimoProductoPage(Model model) {
		model.addAttribute(productoService.consultarUltimo());
		return "ultimoproducto";
	}
	
	@GetMapping("/producto/editar/{id}")
	public ModelAndView getProductoEditPage(@PathVariable(value = "id") Long id) {
		ModelAndView modelView = new ModelAndView("nuevoproducto");
		Optional<Producto> producto = productoService.getProductoPorId(id);
		modelView.addObject("producto",producto);
		return modelView;
	}
	
	@GetMapping("/producto/eliminar/{id}")
	public ModelAndView getProductoEliminar(@PathVariable(value = "id") Long id){
		ModelAndView modelView = new ModelAndView("redirect:/producto/listado");
		productoService.eliminarProducto(id);
		return modelView;
	}
	
}
