

public class ToolBarItem {
	
private String iconURI;
private String nom;
private String framework;
private String descripcio;

public ToolBarItem() {
	// TODO Auto-generated constructor stub
	super();
}

public ToolBarItem(String nom, String icon, String framework) {
	setNom(nom);
	setIconURI(icon);
	setFramework(framework);
}

public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}

public String getIconURI() {
	return iconURI;
}

public void setIconURI(String iconURI) {
	this.iconURI = iconURI;
}

public String getDescripcio() {
	return descripcio;
}

public void setDescripcio(String descripcio) {
	this.descripcio = descripcio;
}

public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((iconURI == null) ? 0 : iconURI.hashCode());
	result = prime * result + ((nom == null) ? 0 : nom.hashCode());
	return result;
}

public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	final ToolBarItem other = (ToolBarItem) obj;
	if (iconURI == null) {
		if (other.iconURI != null)
			return false;
	} else if (!iconURI.equals(other.iconURI))
		return false;
	if (nom == null) {
		if (other.nom != null)
			return false;
	} else if (!nom.equals(other.nom))
		return false;
	return true;
}


public String getFramework() {
	return framework;
}

public void setFramework(String framework) {
	this.framework = framework;
}




}
