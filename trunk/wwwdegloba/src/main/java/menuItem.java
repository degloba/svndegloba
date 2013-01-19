

import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

public class menuItem {
	
	private  String nom2 = "1.1";  // NOM del nivell2 del menu
	private  String nom3 = "1.1";  // NOM del nivell3 del menu
	private  String abrev2 = "1.1";  // ABREV del nivell2 del menu
	private  String abrev3 = "1.1";  // ABREV del nivell3 del menu
	
	String definicioCurrent;
	String menuItemSeleccionat;
	int id;
	private Boolean esFulla;
	public ArrayList<menuItem>  menuItems = new ArrayList<menuItem>();
	
	FacesContext context; 
	 
	/*
	 * Propietats i Metodes auxiliars
	 */

	public menuItem() {
			super();

			context = FacesContext.getCurrentInstance();
			ResourceBundle bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale());
	}
	
	public menuItem(String nom2, String nom3) {
		this.nom2=nom2;
		this.nom3=nom3;
	}
  	
  	public String getDefinicioCurrent() {
		return definicioCurrent;
	}

	public void setCurrent(String definicioCurrent) {
		this.definicioCurrent = definicioCurrent;
	}

	public String getMenuItemSeleccionat() {
		return menuItemSeleccionat;
	}

	public void setMenuItemSeleccionat(String menuItemSeleccionat) {
		this.menuItemSeleccionat = menuItemSeleccionat;
	}

	public String getNom2() {

		return nom2;
	}

	public void setNom2(String nom2) {
		this.nom2 = nom2;
	}

	
	public String getNom3() {
		return nom3;
	}

	public void setNom3(String nom3) {
		this.nom3 = nom3;
	}
	
	public String getAbrev2() {
		return abrev2;
	}

	public void setAbrev2(String abrev2) {
		this.abrev2 = abrev2;
	}

	public String getAbrev3() {
		return abrev3;
	}

	public void setAbrev3(String abrev3) {
		this.abrev3 = abrev3;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
		
	public Boolean getEsFulla() {
		return esFulla;
	}

	public void setEsFulla(Boolean esFulla) {
		this.esFulla = esFulla;
	}

	
	public  ArrayList<menuItem> getMenuItems() {
		return this.menuItems;
	}

	public void setMenuItems(ArrayList<menuItem> menuItems) {
		this.menuItems = menuItems;
	}
	
	public String doAccio()
	{
		String opcio=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("opcio");
		ResourceBundle bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
		
	 	definicioCurrent=bundle.getString("definicio." + opcio);
	 	menuItemSeleccionat=bundle.getString("menuItem." + opcio);
	 	
	 	return "no_contactar";
		
	}
	
}


