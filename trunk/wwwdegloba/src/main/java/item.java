

/*
 *  Classe que implementa el control Richfaces littleshuttle
 */

public class item {

	private String nom;
	private String framework;
	private String iconURI;
	
	public String getIconURI() {
		return iconURI;
	}

	public void setIconURI(String iconURI) {
		this.iconURI = iconURI;
	}

	public item(String nom,String framework,String iconURI) {
		// TODO Auto-generated constructor stub
		this.nom = nom;
		this.framework = framework;
		this.iconURI = iconURI;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getFramework() {
		return framework;
	}

	public void setFramework(String framework) {
		this.framework = framework;
	}

		
	

}
