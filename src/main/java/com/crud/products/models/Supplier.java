package com.crud.products.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblsuppliers")
public class Supplier implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idsupplier;
	private String vname;
	private Boolean bstatus;
	private Date dcreate;
	private Date dupdate;
	
	// CONSTRUCTOR vacio
	public Supplier() {}
	
	public Supplier(Long idsupplier, String vname, Boolean bstatus, Date dcreate, Date dupdate) {
		this.idsupplier = idsupplier;
		this.vname = vname;
		this.bstatus = bstatus;
		this.dcreate = dcreate;
		this.dupdate = dupdate;
	}

	// GETTERS Y SETTERS
	public Long getIdsupplier() {
		return idsupplier;
	}
	public void setIdsupplier(Long idsupplier) {
		this.idsupplier = idsupplier;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
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
		return "Supplier [idsupplier=" + idsupplier + ", vname=" + vname + "]";
	}
}
