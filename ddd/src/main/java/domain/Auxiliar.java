package domain;

import domain.support.BaseAggregateRoot;

public abstract class Auxiliar extends BaseAggregateRoot {

	private String descripcio;
	private String DescCurta;
	private String codi;
	
	
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	public String getDescCurta() {
		return DescCurta;
	}
	public void setDescCurta(String descCurta) {
		DescCurta = descCurta;
	}
	public String getCodi() {
		return codi;
	}
	public void setCodi(String codi) {
		this.codi = codi;
	}
	
	
}
