
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

public class accesDB {
	
	Connection con =null;
	ResultSet Rs=null;	
	
	public void cargarDriver()
  	{
		  		
		try
		{
			//com.mysql.jdbc.NamedPipeSocketFactory npf = new com.mysql.jdbc.NamedPipeSocketFactory();
			//npf.connect(".", 3306, null);
						
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.gjt.mm.mysql.Driver");  //funciona el de enredados
			//con = DriverManager.getConnection("jdbc:mysql://localhost/degloba_carrito?user=degloba_root&password=tivoli00");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/degloba_carrito","root","tivoli00");
			 	
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
