package com.degloba.vo;


// 
public class CaracteristicaForm implements java.io.Serializable ,Comparable<Object>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nom;
	private int idTipus;
  	
	// Constructor por defecto.
  	public CaracteristicaForm() 
  	{
	    super();
	    
  	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getIdTipus() {
		return idTipus;
	}

	public void setIdTipus(int idTipus) {
		this.idTipus = idTipus;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		
		// comparem camp a camp
		if (getNom().compareTo(((CaracteristicaForm)o).getNom()) == 0 
				&& getId() == ((CaracteristicaForm)o).getId() 
				&& getIdTipus() == ((CaracteristicaForm)o).getIdTipus())
		{
			return 0;
		}
		
		
		return -1;
	}



  	
  	
}
