import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;


public class modalPanels {
	

	private  ArrayList<modalPanel> items = new ArrayList<modalPanel>();
	modalPanel mp;
	accesDB ac;
	FacesContext context;
	ResourceBundle bundle;


	public modalPanels() {
		super();

		context  = FacesContext.getCurrentInstance();
		
		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
		
		omplirPanelModals();
	}


	public List<modalPanel> getItems() {
		
		return this.items;
	}


	public void setItems(ArrayList<modalPanel> items) {
		this.items = items;
	}


	private void omplirPanelModals() {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {

		ac = new accesDB();
		ac.cargarDriver();
		
		stmt = ac.con.createStatement();

        if (stmt.execute("SELECT MODALPANELID FROM modalpanels")) {
            rs = stmt.getResultSet();
   
        }
    	
        while (rs.next())
  		{
        	
    		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
			
	  		mp = new modalPanel();
	  		mp.setId(rs.getInt("MODALPANELID"));
	  		mp.setTitol(bundle.getString("titolPanelModal." + rs.getString("MODALPANELID")));
	  		mp.setDefinicio(bundle.getString("definicioPanelModal." + rs.getString("MODALPANELID")));
			
			this.items.add(mp);
				
  		}

    	ac.con.close();  		
		
		}	catch (SQLException ex) {
			    // handle any errors

			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	
}


