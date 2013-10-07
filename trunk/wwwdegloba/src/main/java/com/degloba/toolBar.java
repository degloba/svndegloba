package com.degloba;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;

import javax.faces.context.FacesContext;


public class toolBar {
	
	private List<ToolBarItem> freeItems;
	private List<ToolBarItem> toolBarItems;

	private Connection con = null;
	
	FacesContext context; 
	
	
	public toolBar() {
		omplirFrameworks();
	}
	
	
  	private void cargarDriver()
  	{
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.gjt.mm.mysql.Driver");  //funciona el de enredados
			con = DriverManager.getConnection("jdbc:mysql://localhost/degloba_carrito?user=degloba_root&password=tivoli00");
    	}
		catch (SQLException se)
		{
			System.err.println("Se ha producido un error al abrir la conexi?n de BD.");  
            System.err.println(se.getMessage());  
		}
		catch (java.lang.ClassNotFoundException s) 
		{ 
			System.out.println("No se encuentra la clase "+ s.toString());
		}
  	}
  	
  	
  	
  	private void omplirFrameworks() {
		Statement stmt = null;
		ResultSet rs = null;
		ToolBarItem item;

		freeItems = new ArrayList<ToolBarItem>();
		
		cargarDriver();
		
		try {
			
    	stmt = con.createStatement();
    	
        if (stmt.execute("SELECT * FROM frameworks")) {
            rs = stmt.getResultSet();
        }
    	
		while (rs.next())
  		{
			item=new ToolBarItem();
			item.setNom(rs.getString("nom"));
			item.setIconURI(rs.getString("icon"));
			item.setFramework(rs.getString("tecnologia"));
			item.setDescripcio(rs.getString("descripcio"));
			freeItems.add(item);
  		}
  		
  		// guardem a una variable de sessio els frameworks
  		//FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("items", toolBarItems);
		
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
  	}

	public List<ToolBarItem> getFreeItems() {
			return freeItems;
	}

	public void setFreeItems(List<ToolBarItem> freeItems) {
		this.freeItems = freeItems;
		}

	public List<ToolBarItem> getToolBarItems() {
		return toolBarItems;
	}

	public void setToolBarItems(List<ToolBarItem> toolBarItems) {
		
		if (!toolBarItems.isEmpty()) {
			
			}
		this.toolBarItems = toolBarItems;
	}

}
