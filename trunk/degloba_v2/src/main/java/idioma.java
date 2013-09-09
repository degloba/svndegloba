

import java.util.Locale;

import javax.faces.context.FacesContext;


public class idioma {
	
	Locale ca;
	Locale es;
	Locale en;
	
	Locale currentLocale; 
	FacesContext context;

	
	 Boolean  espanolEnabled=true;   // idioma seleccionat
	 
	 
		public Boolean getEspanolEnabled() {
			return espanolEnabled;
		}

		public void setEspanolEnabled(Boolean espanolEnabled) {
			this.espanolEnabled = espanolEnabled;
		}
		
		
		
		public void cambiarIdioma()
		{

			espanolEnabled=!espanolEnabled;
		
		context = FacesContext.getCurrentInstance();
		currentLocale = context.getViewRoot().getLocale();
		
		String llengua=currentLocale.getLanguage();
		
		if (llengua == "en")
		{
			 context.getViewRoot().setLocale(es);
		}
	   else
	   {
			context.getViewRoot().setLocale(en);
		}

}		

		
		// Constructor
		public idioma() {
			super();
			

		// Crem tants Locales com suporti la Web			
			 ca= new Locale("ca");
			 es= new Locale("es");
			 en= new Locale("en");
			 
			context = FacesContext.getCurrentInstance();
			currentLocale = context.getViewRoot().getLocale();
		}
		
		

		public Locale getCurrentLocale() {
			return currentLocale;
		}

		public void setCurrentLocale(Locale currentLocale) {
			this.currentLocale = currentLocale;
		}
		
		
		
		
}
