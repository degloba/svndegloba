

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


public class jboss {

	
	private List<tecnologia> tecnologies;
	private Connection con = null;
	
	
	
	public jboss() {
		// TODO Auto-generated constructor stub
		
		omplirTecnologies();
	}
	

	public List<tecnologia> getTecnologies() {
		return tecnologies;
	}

	public void setTecnologies(List<tecnologia> tecnologies) {
		this.tecnologies = tecnologies;
	}

	
	private void cargarDriver()
  	{
  		
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.gjt.mm.mysql.Driver");  //funciona el de enredados
			//con = DriverManager.getConnection("jdbc:mysql://localhost/degloba_carrito?user=degloba_root&password=tivoli00");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/degloba_carrito","degloba_root","tivoli00");
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
  	
  	
  	private void omplirTecnologies() {
		Statement stmt = null;
		ResultSet rs = null;


		tecnologies = new ArrayList<tecnologia>();
		
		
		cargarDriver();
		
		try {
			
    	stmt = con.createStatement();
    	
        if (stmt.execute("SELECT * FROM arquitecturajboss")) {
            rs = stmt.getResultSet();
        }
    	
		while (rs.next())
  		{
			tecnologies.add(new tecnologia(rs.getString("tecnologia"),rs.getString("descripcio")));
  		}
  		
  		// guardem a una variable de sessio els frameworks
  		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("tecnologies", tecnologies);
		
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		

  	}
	
	
}
