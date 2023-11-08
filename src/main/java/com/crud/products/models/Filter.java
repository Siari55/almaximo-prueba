package com.crud.products.models;

public class Filter {

	private String code;
	private int idproductype;
	
	public Filter() {}
	
	public Filter(String code, int idproductype) {
		this.code = code;
		this.idproductype = idproductype;
	}
	
	public String getCode() {
		return code;
	}
	public void setClave(String clave) {
		this.code = clave;
	}
	public int getIdproductype() {
		return idproductype;
	}
	public void setIdproductype(int idproductype) {
		this.idproductype = idproductype;
	}

	@Override
	public String toString() {
		return "Filter [clave=" + code + ", idproductype=" + idproductype + "]";
	}
}
