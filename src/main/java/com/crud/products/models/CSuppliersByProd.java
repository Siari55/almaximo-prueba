package com.crud.products.models;

public class CSuppliersByProd {

	private Long idprodsupp;
	private String vcodeprod;
	private String vname;
	private String vcodesupp;
	private Double dprice;
	
	public CSuppliersByProd() {}
	
	public CSuppliersByProd(Long idprodsupp, String vcodeprod, String vname, String vcodesupp, Double dprice) {
		this.idprodsupp = idprodsupp;
		this.vcodeprod = vcodeprod;
		this.vname = vname;
		this.vcodesupp = vcodesupp;
		this.dprice = dprice;
	}

	public Long getIdprodsupp() {
		return idprodsupp;
	}
	public void setIdprodsupp(Long idprodsupp) {
		this.idprodsupp = idprodsupp;
	}
	public String getVcodeprod() {
		return vcodeprod;
	}
	public void setVcodeprod(String vcodeprod) {
		this.vcodeprod = vcodeprod;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getVcodesupp() {
		return vcodesupp;
	}
	public void setVcodesupp(String vcodesupp) {
		this.vcodesupp = vcodesupp;
	}
	public Double getDprice() {
		return dprice;
	}
	public void setDprice(Double dprice) {
		this.dprice = dprice;
	}
	@Override
	public String toString() {
		return "CSupplierByProd [idprodsupp=" + idprodsupp + ", vcodeprod=" + vcodeprod + ", vname=" + vname
				+ ", vcodesupp=" + vcodesupp + ", dprice=" + dprice + "]";
	}
}
