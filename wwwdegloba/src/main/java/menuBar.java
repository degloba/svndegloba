

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class menuBar {
	
	private  ArrayList<dropDownMenu> dropDownMenus = new ArrayList<dropDownMenu>();
	dropDownMenu dd;
	accesDB ac;
	FacesContext context;
	ResourceBundle bundle;

	public menuBar() {
		super();
		// TODO Auto-generated constructor stub
		context  = FacesContext.getCurrentInstance();		
		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
		
		omplirMenuBar();
	}


	public List<dropDownMenu> getDropDownMenus() {
		return this.dropDownMenus;
	}


	public void setDropDownMenus(ArrayList<dropDownMenu> dropDownMenus) {
		this.dropDownMenus = dropDownMenus;
	}


	private void omplirMenuBar() {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		
			ac = new accesDB();
			ac.cargarDriver();
			
			stmt = ac.con.createStatement();
	    	
	        if (stmt.execute("SELECT DISTINCT ABREV1 FROM menubar")) {
	            rs = stmt.getResultSet();
	        }
	    	
        
	        while (rs.next())
	  		{
	        	
	    		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
		  		dd = new dropDownMenu();
		  	 		
		  		dd.setNom(bundle.getString("titolMenubar." + rs.getString("ABREV1")));
	  			
		  		cargarMenuItems(rs.getString("ABREV1"));
				
				this.dropDownMenus.add(dd);
					
	  		}
 		}	catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	
	public void cargarMenuItems(String nomDropDownMenu)
	{
		
		Statement stmt = null;
		ResultSet rs = null;
				
		try {
		
		stmt = ac.con.createStatement();
    	
		// Recorrem tots els menuitems donat un nom de menu (dropdownmenu)
        rs =stmt.executeQuery("SELECT DISTINCT ABREV2 FROM menubar WHERE ABREV1 = '" + nomDropDownMenu + "'");
        
        ArrayList<String> llistaMenuItems = new ArrayList<String>();
        while (rs.next())
        {
        	llistaMenuItems.add(rs.getString("ABREV2"));
        }
        
        rs.close();
        
        Iterator it =llistaMenuItems.listIterator();
        
        while (it.hasNext())
  		{	
    		menuItem mi= new menuItem();
    		
    		String menuItem = it.next().toString();
    		mi.setNom2(bundle.getString("menuItem." + menuItem));
    		//mi.setId(rs.getInt("IDMENUITEM"));
    		mi.setAbrev2(menuItem);
    		
    		mi.setEsFulla(false);
    		
    	    rs = stmt.executeQuery("SELECT IDMENUITEM, ABREV2,ABREV3 FROM menubar WHERE ABREV1 = '" + nomDropDownMenu + "' AND ABREV2 = '" + menuItem + "'");
    	    
    	    Boolean teSubmenu = false; // si té submenu o no
    	    menuItem mi2 = null;
    	    
    	    ArrayList<menuItem> llistaSubMenuItems = new ArrayList<menuItem>();
    	    while (rs.next())
	  		{
    	        if(rs.getString("ABREV3").compareTo("") !=0) //SUBMENU
    	        {
	    	       	mi2= new menuItem();
	    	       	mi2.setId(new Integer(rs.getString("IDMENUITEM")));
	    	   		mi2.setNom2(bundle.getString("menuItem." + rs.getString("ABREV2")));
	    	   		mi2.setNom3(bundle.getString("menuItem." + rs.getString("ABREV3")));
	    	   		mi2.setAbrev2(rs.getString("ABREV2"));
	    	   		mi2.setAbrev3(rs.getString("ABREV3"));
	    	    		
	    	   		//mi2.setId(rs.getInt("IDMENUITEM"));
	    	    		
	    	       	mi2.menuItems.add(mi2);
	    	        	
	    	       	llistaSubMenuItems.add(mi2);
	    	        	
	    	       	teSubmenu=true;
    	        }
	    		else
	    		{
	    			mi.setEsFulla(true);
	    			dd.menuItems.add(mi);
	    		}
    	        
	  		}  //while
    	    
    	    if (teSubmenu)  // té SubMenu
    	    {
    	    	mi2.setMenuItems(llistaSubMenuItems);
    	    	dd.menuItems.add(mi2);
    	    }
    			
  		} //while
		
		}	catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			
		} finally
		{
				
		}
  		
	}
	
}


