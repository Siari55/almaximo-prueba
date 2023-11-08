package com.crud.products.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.products.models.CRelProdSupp;
import com.crud.products.models.Filter;
import com.crud.products.models.Product;
import com.crud.products.services.IProductService;
import com.crud.products.utils.Constants;

@RestController
@RequestMapping(value = "/api/products")
@CrossOrigin(origins="*")
public class ProductController {

	@Autowired
	private IProductService service;
	
	// Listar todos
	@PostMapping("/findall")
	public ResponseEntity<?> getAllProducts(@RequestBody Filter filter) {
		Map<String, Object> msg = new HashMap<>();
		try {
			List<Product> allproducts = service.findall(); 
			
			allproducts = allproducts.stream()
	            .filter(p -> (filter.getCode().equals("")) ? p.getVcode().matches(".*") : p.getVcode().matches(".*" + filter.getCode() + ".*"))
	            .filter(p -> (filter.getIdproductype() == 0) ? p.getIdproducttype() > 0 : p.getIdproducttype() == filter.getIdproductype())
	            .collect(Collectors.toList());			
			msg.put(Constants.RES, allproducts);
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
		} catch (DataAccessException e) {
			msg.put(Constants.MESSAGE, "Error al obtener el listado de productos");
            msg.put(Constants.ERROR, e.getMostSpecificCause().toString());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Buscar por Id
	@GetMapping("/getproduct/{idproduct}")
	public ResponseEntity<?> getById(@PathVariable Long idproduct) {
		Map<String, Object> msg = new HashMap<>();
		try {
			Product product = service.findbyid(idproduct); 
			msg.put(Constants.RES, product);
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
		} catch (DataAccessException e) {
			msg.put(Constants.MESSAGE, "Error al obtener el producto");
            msg.put(Constants.ERROR, e.getMostSpecificCause().toString());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Crear
	@PostMapping("/saveproduct")
	public ResponseEntity<?> save(@RequestBody Product product) {
		
		Map<String, Object> msg = new HashMap<>();
		try {
			Product nuevo = service.createproduct(product);
			msg.put(Constants.RES, product);
			msg.put(Constants.MESSAGE, "Se creó el producto");
			return new ResponseEntity<>(msg, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			msg.put(Constants.MESSAGE, "Error al crear el producto");
            msg.put(Constants.ERROR, e.getMostSpecificCause().toString());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Editar
	@PostMapping("/updateproduct")
	public ResponseEntity<?> update(@RequestBody Product product) {
		Map<String, Object> msg = new HashMap<>();
		try {
			Product nuevo = service.updateproduct(product);
			msg.put(Constants.RES, nuevo);
			msg.put(Constants.MESSAGE, "Se actualizó el producto");
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
		} catch (DataAccessException e) {
			msg.put(Constants.MESSAGE, "Error al crear el producto");
            msg.put(Constants.ERROR, e.getMostSpecificCause().toString());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Eliminar
	@DeleteMapping("/deleteproduct/{idproduct}")
	public ResponseEntity<?> delete(@PathVariable Long idproduct) {
		Map<String, Object> msg = new HashMap<>();
		try {
			service.deleteproduct(idproduct);
			msg.put(Constants.RES, idproduct);
			msg.put(Constants.MESSAGE, "El producto se eliminó");
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
		} catch (DataAccessException e) {
			msg.put(Constants.MESSAGE, "Error al eliminar el producto");
            msg.put(Constants.ERROR, e.getMostSpecificCause().toString());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Obtener los tipos de productos
	@GetMapping("/gettypes")
	public ResponseEntity<?> gettypes() {
		Map<String, Object> msg = new HashMap<>();
		try {
			msg.put(Constants.RES, service.gettypes());
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
		} catch (DataAccessException e) {
			msg.put(Constants.MESSAGE, "Error al consultar los tipos de producto");
            msg.put(Constants.ERROR, e.getMostSpecificCause().toString());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Obtener la información del insumo para desplegar en la edición
	@GetMapping("/getinfoproduct/{idproduct}")
	public ResponseEntity<?> getinfoproduct(@PathVariable Long idproduct) {
		Map<String, Object> msg = new HashMap<>();
		try {
			msg.put(Constants.RES, service.getInfoProduct(idproduct));
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
		} catch (DataAccessException e) {
			msg.put(Constants.MESSAGE, "Error al consultar los tipos de producto");
            msg.put(Constants.ERROR, e.getMostSpecificCause().toString());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Obtener los proveedores por producto
	@GetMapping("/getsuppliersbyproduct/{idproduct}")
	public ResponseEntity<?> getsuppliersbyproduct(@PathVariable Long idproduct) {
		Map<String, Object> msg = new HashMap<>();
		try {
			msg.put(Constants.RES, service.getsuppliersbyproduct(idproduct));
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
		} catch (DataAccessException e) {
			msg.put(Constants.MESSAGE, "Error al consultar los tipos de producto");
            msg.put(Constants.ERROR, e.getMostSpecificCause().toString());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	// Obtener los proveedores por producto
	@GetMapping("/getallsuppliers")
	public ResponseEntity<?> getallsuppliers() {
		Map<String, Object> msg = new HashMap<>();
		try {
			msg.put(Constants.RES, service.getallsuppliers());
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
		} catch (DataAccessException e) {
			msg.put(Constants.MESSAGE, "Error al consultar los tipos de producto");
            msg.put(Constants.ERROR, e.getMostSpecificCause().toString());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	// Eliminar la relación entre un proveedor y un producto
	@DeleteMapping("/deletesupplier/{idprodsupp}")
	public ResponseEntity<?> deletesupplier(@PathVariable Long idprodsupp) {
		Map<String, Object> msg = new HashMap<>();
		try {
			service.deletesupplier(idprodsupp);
			msg.put(Constants.RES, "Se eliminó el proveedor correctamente");
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
		} catch (DataAccessException e) {
			msg.put(Constants.MESSAGE, "Error al consultar los tipos de producto");
            msg.put(Constants.ERROR, e.getMostSpecificCause().toString());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Eliminar la relación entre un proveedor y un producto
	@PostMapping("/createrelprodsupp")
	public ResponseEntity<?> deletesupplier(@RequestBody CRelProdSupp crelprodsupp) {
		Map<String, Object> msg = new HashMap<>();
		try {
			service.createrelprodsupp(crelprodsupp);
			msg.put(Constants.RES, "Se eliminó el proveedor correctamente");
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
		} catch (DataAccessException e) {
			msg.put(Constants.MESSAGE, "Error al consultar los tipos de producto");
            msg.put(Constants.ERROR, e.getMostSpecificCause().toString());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
