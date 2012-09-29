package com.degloba.image;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;


public class images
{

    
  	// Referencia de conexi?n a una BD con JDBC.
	Connection con = null;
	
  /*-------------------------
   *   Get the Blob image
   *------------------------*/
  public byte[] getPhoto (Connection con,int iNumPhoto)
       throws Exception, SQLException
  {

    String req = "" ;
    Blob img ;
    byte[] imgData = null ;
    

	
    init();
    
    Statement stmt = con.createStatement ();
  
    
    // Query
    req = "Select imatge From producto Where id = " + iNumPhoto ;
   
    ResultSet rset  = stmt.executeQuery ( req );
   
    while (rset.next ())
    {   
      img = rset.getBlob(1);
      imgData = img.getBytes(1,(int)img.length());
    }   
   
    rset.close();
    stmt.close();
   
    return imgData ;

  }
 
  
  

	/**
 	 * Abre la conexi?n con la BD.
 	 **/
	private void init()
	{
		
		// Aqu? debe ir la carga del driver y la conexi?n
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			//con = DriverManager.getConnection("jdbc:mysql://localhost/insacosa_carrito", "insacosa_root", "tivoli00");
			
			Class.forName("org.gjt.mm.mysql.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost/insacosa_carrito","insacosa_root","tivoli00");
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
}  