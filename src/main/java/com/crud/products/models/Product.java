package com.crud.products.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblproducts")
public class Product implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idproduct;
	private Long idproducttype;
	private String vcode;
	private String vname;
	private Double dprice;
	private Boolean bstatus;
	private Date dcreate;
	private Date dupdate;
	
	// CONSTRUCTOR vacio
	public Product() {}
	
	public Product(Long idproduct, Long idproducttype, String vcode, String vname, Double dprice) {
		this.idproduct = idproduct;
		this.idproducttype = idproducttype;
		this.vcode = vcode;
		this.vname = vname;
		this.dprice = dprice;
	}
	
	// GETTERS Y SETTERS
	public Long getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(Long idproduct) {
		this.idproduct = idproduct;
	}
	public Long getIdproducttype() {
		return idproducttype;
	}
	public void setIdproducttype(Long idproducttype) {
		this.idproducttype = idproducttype;
	}
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public Double getDprice() {
		return dprice;
	}
	public void setDprice(Double dprice) {
		this.dprice = dprice;
	}
	public Boolean getBstatus() {
		return bstatus;
	}

	public void setBstatus(Boolean bstatus) {
		this.bstatus = bstatus;
	}

	public Date getDcreate() {
		return dcreate;
	}

	public void setDcreate(Date dcreate) {
		this.dcreate = dcreate;
	}

	public Date getDupdate() {
		return dupdate;
	}

	public void setDupdate(Date dupdate) {
		this.dupdate = dupdate;
	}

	// TOSTRING
	@Override
	public String toString() {
		return "Product [idproduct=" + idproduct + ", idproducttype=" + idproducttype + ", vcode=" + vcode + ", vname="
				+ vname + ", dprice=" + dprice + "]";
	}
	
}
