package com.insacosa.vo;

// 
public class CaracteristicaForm implements java.io.Serializable ,Comparable<Object>
{

	private static final long serialVersionUID = 1L;
	private String key;
	private String nom;
	
	private String keyTipus;
  	
	// Constructor por defecto.
  	public CaracteristicaForm() 
  	{
	    super();
	    
  	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getKeyTipus() {
		return keyTipus;
	}

	public void setKeyTipus(String keyTipus) {
		this.keyTipus = keyTipus;
	}

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		
		// comparem camp a camp
		if (getNom().compareTo(((CaracteristicaForm)o).getNom()) == 0 
				&& getKey() == ((CaracteristicaForm)o).getKey() 
				&& getKeyTipus() == ((CaracteristicaForm)o).getKeyTipus())
		{
			return 0;
		}
		
		
		return -1;
	}



  	
  	
}
