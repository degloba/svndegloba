package com.insacosa.vo;

import java.sql.Timestamp;

// Clase Value Object de la que todas las vistas deber?an descender.
public class BaseVO implements java.io.Serializable 
{
	private int id;
  	private Timestamp timeCreated = null;
  	private String description;
  	private String name;

	// Constructor por defecto.
  	public BaseVO() 
  	{
	    super();
	    setTimeCreated(new Timestamp(System.currentTimeMillis()));
  	}

	// Constructor a partir de los datos.
  	public BaseVO(int id, String name, String desc) 
  	{
    	this();
    	
    	this.id = id;
    	this.name = name;
    	this.description = desc;
  	}

  	public void setName(String name) 
  	{
	    this.name = name;
  	}
  	
  	public void setTimeCreated(Timestamp now) 
  	{
	    timeCreated = now;
  	}
  	
  	public void setDescription(String description) 
  	{
	    this.description = description;
  	}
  	
  	public void setId(int id) 
  	{
	    this.id = id;
  	}
  	
  	public String getName() 
  	{
	    return name;
  	}
	
  	public String getDescription() 
  	{
	    return description;
  	}
  	
  	public int getId() 
  	{
	    return id;
  	}
  	
  	public Timestamp getTimeCreated() 
  	{
	    return timeCreated;
  	}
  	
}
