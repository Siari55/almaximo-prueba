package com.crud.products.models;

public class CRelProdSupp {

	private Long idproduct;
	private Long idsupplier;
	private String vcode;
	private Double dprice;
	
	public CRelProdSupp() {}
	
	public CRelProdSupp(Long idproduct, Long idsupplier, String vcode, Double dprice) {
		super();
		this.idproduct = idproduct;
		this.idsupplier = idsupplier;
		this.vcode = vcode;
		this.dprice = dprice;
	}

	public Long getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(Long idproduct) {
		this.idproduct = idproduct;
	}
	public Long getIdsupplier() {
		return idsupplier;
	}
	public void setIdsupplier(Long idsupplier) {
		this.idsupplier = idsupplier;
	}
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	public Double getDprice() {
		return dprice;
	}
	public void setDprice(Double dprice) {
		this.dprice = dprice;
	}
	@Override
	public String toString() {
		return "CRelProdSupp [idproduct=" + idproduct + ", idsupplier=" + idsupplier + ", vcode=" + vcode + ", dprice="
				+ dprice + "]";
	}
}
