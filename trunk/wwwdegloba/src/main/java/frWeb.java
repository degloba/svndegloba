

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


public class frWeb {

	
	private List<framework> frameworks;
	private List<javax.faces.model.SelectItem> nomFrameworks;
	private Connection con = null;
	
	
	
	public frWeb() {
		// TODO Auto-generated constructor stub
		
		frWebs();
	}
	

	public List<framework> getFrameworks() {
		return frameworks;
	}

	public void setFrameworks(List<framework> frameworks) {
		this.frameworks = frameworks;
	}
	
	public List<javax.faces.model.SelectItem> getNomFrameworks() {
		return nomFrameworks;
	}
	
	public void setNomFrameworks(List<javax.faces.model.SelectItem> nomFrameworks) {
		this.nomFrameworks = nomFrameworks;
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
  	
  	
  	private void frWebs() {
		Statement stmt = null;
		ResultSet rs = null;
		

		frameworks = new ArrayList<framework>();
		nomFrameworks = new ArrayList<javax.faces.model.SelectItem>();
		
		
		cargarDriver();
		
		try {
			
    	stmt = con.createStatement();
    	
        if (stmt.execute("SELECT * FROM Frameworks")) {
            rs = stmt.getResultSet();
        }
    	
		while (rs.next())
  		{
			frameworks.add(new framework(rs.getString("nom"), rs.getString("tecnologia"),rs.getString("descripcio"), rs.getString("idTipus")));
			nomFrameworks.add(new javax.faces.model.SelectItem(rs.getString("nom")));
  		}
  		
  		// guardem a una variable de sessio els frameworks
  		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("frameworks", frameworks);
		
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		

  	}
	
	
}
