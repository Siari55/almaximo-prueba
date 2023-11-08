package com.crud.products.services;

import java.util.List;

import com.crud.products.models.CProductEdit;
import com.crud.products.models.CRelProdSupp;
import com.crud.products.models.CSuppliersByProd;
import com.crud.products.models.Product;
import com.crud.products.models.ProductType;
import com.crud.products.models.Supplier;

public interface IProductService {

	// Obtener todos los productos
	public abstract List<Product> findall();
	
	// Crear producto nuevo
	public abstract Product createproduct(Product product);
	
	// Actualizar producto
	public abstract Product updateproduct(Product product);
	
	// Eliminar producto
	public abstract void deleteproduct(Long idproduct);
	
	// Obtener un producto
	public abstract Product findbyid(Long idproduct);
	
	// Obtener los tipos de producto (Select)
	public abstract List<ProductType> gettypes();
	
	// Obtener la info personalizada del producto
	public abstract List<CProductEdit> getInfoProduct(Long idproduct);
	
	// Obtener los proveedores de cada producto
	public abstract List<CSuppliersByProd> getsuppliersbyproduct(Long idproduct);
	
	// Obtener los proveedores (Select)
	public abstract List<Supplier> getallsuppliers();
	
	// Eliminar la relación entre el proveedor y el producto
	public abstract void deletesupplier(Long idprodsupp);
	
	// Crear relación entre proveedor y producto
	public abstract void createrelprodsupp(CRelProdSupp crelprodsupp);
}
