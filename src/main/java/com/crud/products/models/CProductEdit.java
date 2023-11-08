package com.crud.products.models;

public class CProductEdit {

	private Long idproduct;
	private String vcode;
	private String vname;
	private Boolean bstatus;
	private Long idtype;
	private String vdescription;
	private Double dprice;
	
	public CProductEdit(Long idproduct, String vcode, String vname, Boolean bstatus, Long idtype, String vdescription, Double dprice) {
		this.idproduct = idproduct;
		this.vcode = vcode;
		this.vname = vname;
		this.bstatus = bstatus;
		this.idtype = idtype;
		this.vdescription = vdescription;
		this.dprice = dprice;
	}
	
	public Long getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(Long idproduct) {
		this.idproduct = idproduct;
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
	public Boolean getBstatus() {
		return bstatus;
	}
	public void setBstatus(Boolean bstatus) {
		this.bstatus = bstatus;
	}
	public Long getIdtype() {
		return idtype;
	}

	public void setIdtype(Long idtype) {
		this.idtype = idtype;
	}

	public String getVdescription() {
		return vdescription;
	}
	public void setVdescription(String vdescription) {
		this.vdescription = vdescription;
	}

	public Double getDprice() {
		return dprice;
	}

	public void setDprice(Double dprice) {
		this.dprice = dprice;
	}

	@Override
	public String toString() {
		return "CProductEdit [idproduct=" + idproduct + ", vcode=" + vcode + ", vname=" + vname + ", bstatus=" + bstatus
				+ ", idtype=" + idtype + ", vdescription=" + vdescription + ", dprice=" + dprice + "]";
	}
}
