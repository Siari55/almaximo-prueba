package com.crud.products.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.products.dao.ProductDao;
import com.crud.products.models.CProductEdit;
import com.crud.products.models.CRelProdSupp;
import com.crud.products.models.CSuppliersByProd;
import com.crud.products.models.Product;
import com.crud.products.models.ProductType;
import com.crud.products.models.Supplier;
import com.crud.products.services.IProductService;

@Service
public class ProductService implements IProductService {

private final EntityManager entityManager;
	
	public ProductService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Autowired
	private ProductDao dao;

	@Override
	@Transactional(readOnly=true)
	public List<Product> findall() {
		return (List<Product>) dao.findByBstatus(true);
	}

	@Override
	@Transactional(readOnly=false)
	public Product createproduct(Product product) {
		return dao.save(product);
	}

	@Override
	@Transactional(readOnly=false)
	public Product updateproduct(Product product) {
		return dao.save(product);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteproduct(Long idproduct) {
		Product nuevo = findbyid(idproduct);
		nuevo.setBstatus(false);
		dao.save(nuevo);
	}

	@Override
	@Transactional(readOnly=true)
	public Product findbyid(Long idproduct) {
		return dao.findById(idproduct).orElse(null);
	}
	
	/******************* CUSTOM *******************/
	
	@Override
	@Transactional(readOnly=false)
	public List<ProductType> gettypes(){
		StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("sp_getproducttypes");
        sp.execute();
        
        List<Object[]> results = sp.getResultList();

        
        return results.stream().map(p -> new ProductType(
        	Long.parseLong(p[0].toString()),
        	p[1].toString(), 
        	p[2].toString(), 
        	Boolean.parseBoolean(p[3].toString()), 
        	(p[4] == null) ? null : getDate(p[4].toString()), 
        	(p[5] == null) ? null : getDate(p[5].toString())
        )).collect(Collectors.toList());
	}
	
	public Date getDate(String d) {
		try {
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            Date fecha = formato.parse(d);
            return fecha;
        } catch (Exception e) {
            System.out.println("Error al convertir la cadena a Date: " + e.getMessage());
            return null;
        }
	}

	@Override
	public List<CProductEdit> getInfoProduct(Long idproduct) {
		StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("sp_getproductedit");
		
		// Parametros de entrada (Configuración)
        sp.registerStoredProcedureParameter("INPUT_PROCEDURE_PARAMETER_IDPRODUCT", Long.class, ParameterMode.IN);
        
        // Configuramos el valor de entrada
        sp.setParameter("INPUT_PROCEDURE_PARAMETER_IDPRODUCT", idproduct);
		
        sp.execute();
        
        List<Object[]> results = sp.getResultList();
        
        return results.stream().map(p -> new CProductEdit(
        	Long.parseLong(p[0].toString()),
        	p[1].toString(), 
        	p[2].toString(), 
        	Boolean.parseBoolean(p[3].toString()), 
        	Long.parseLong(p[4].toString()),
        	p[5].toString(),
        	Double.parseDouble(p[6].toString())
        )).collect(Collectors.toList());
	}

	@Override
	public List<CSuppliersByProd> getsuppliersbyproduct(Long idproduct) {
		StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("sp_getsuppliersbyid");
		
		// Parametros de entrada (Configuración)
        sp.registerStoredProcedureParameter("INPUT_PROCEDURE_PARAMETER_IDPRODUCT", Long.class, ParameterMode.IN);
        
        // Configuramos el valor de entrada
        sp.setParameter("INPUT_PROCEDURE_PARAMETER_IDPRODUCT", idproduct);
		
        sp.execute();
        
        List<Object[]> results = sp.getResultList();
        
        return results.stream().map(p -> new CSuppliersByProd (
        	Long.parseLong(p[0].toString()),
        	p[1].toString(),
        	p[2].toString(),
        	p[3].toString(),
        	Double.parseDouble(p[4].toString())
        )).collect(Collectors.toList());
	}

	@Override
	public List<Supplier> getallsuppliers() {
		StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("sp_getallsuppliers");
		
        sp.execute();
        
        List<Object[]> results = sp.getResultList();
        
        return results.stream().map(p -> new Supplier (
        	Long.parseLong(p[0].toString()),
        	p[1].toString(),
        	Boolean.parseBoolean(p[2].toString()),
        	(p[3] == null) ? null : getDate(p[3].toString()), 
        	(p[4] == null) ? null : getDate(p[4].toString())
        )).collect(Collectors.toList());
	}

	@Override
	public void deletesupplier(Long idprodsupp) {
		StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("sp_changesupplierstatus");
		
		// Parametros de entrada (Configuración)
        sp.registerStoredProcedureParameter("INPUT_PROCEDURE_PARAMETER_IDPRODSUPP", Long.class, ParameterMode.IN);
        
        // Configuramos el valor de entrada
        sp.setParameter("INPUT_PROCEDURE_PARAMETER_IDPRODSUPP", idprodsupp);
		
        sp.execute();
	}

	@Override
	public void createrelprodsupp(CRelProdSupp crelprodsupp) {
		StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("sp_createrelprodsupp");
		
		// Parametros de entrada (Configuración)
        sp.registerStoredProcedureParameter("INPUT_PROCEDURE_PARAMETER_IDPRODUCT", Long.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("INPUT_PROCEDURE_PARAMETER_IDSUPPLIER", Long.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("INPUT_PROCEDURE_PARAMETER_VCODE", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("INPUT_PROCEDURE_PARAMETER_DPRICE", Double.class, ParameterMode.IN);
        
        // Configuramos el valor de entrada
        sp.setParameter("INPUT_PROCEDURE_PARAMETER_IDPRODUCT", crelprodsupp.getIdproduct());
        sp.setParameter("INPUT_PROCEDURE_PARAMETER_IDSUPPLIER", crelprodsupp.getIdsupplier());
        sp.setParameter("INPUT_PROCEDURE_PARAMETER_VCODE", crelprodsupp.getVcode());
        sp.setParameter("INPUT_PROCEDURE_PARAMETER_DPRICE", crelprodsupp.getDprice());
		
        sp.execute();
	}
	
}
