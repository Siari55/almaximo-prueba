package com.crud.products.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="catproducttypes")
public class ProductType implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtype;
	private String vname;
	private String vdescription;
	private Boolean bstatus;
	private Date dcreate;
	private Date dupdate;
	
	// CONSTRUCTOR vacio
	public ProductType() {}
	
	public ProductType(Long idtype, String vname, String vdescription, Boolean bstatus, Date dcreate, Date dupdate) {
		this.idtype = idtype;
		this.vname = vname;
		this.vdescription = vdescription;
		this.bstatus = bstatus;
		this.dcreate = dcreate;
		this.dupdate = dupdate;
	}

	// GETTERS Y SETTERS
	public Long getIdtype() {
		return idtype;
	}
	public void setIdtype(Long idtype) {
		this.idtype = idtype;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getVdescription() {
		return vdescription;
	}
	public void setVdescription(String vdescription) {
		this.vdescription = vdescription;
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
		return "ProductType [idtype=" + idtype + ", vname=" + vname + ", vdescription=" + vdescription + "]";
	}
}
