package com.insacosa.vo;

import javax.inject.Inject;

import com.insacosa.filtre.InventoryFiltreItem;
import com.insacosa.filtre.InventoryFiltreList;
import com.insacosa.filtre.InventoryItem;


/*----------------------------*/
/* java.util                  */
/*----------------------------*/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.el.ValueExpression;

// JSF
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

// Finders,...
import com.insacosa.presentation.*;


// JPA - Persistencia          
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

// UI - Richfaces
import org.richfaces.component.SortOrder;
import org.richfaces.component.UIDataTable;
import org.richfaces.component.UIExtendedDataTable;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.degloba.persistencia.JPA.EMF;
import com.degloba.persistencia.JPA.PersistenceService;
/*----------------------------*/
/* Utilitats                  */
/*----------------------------*/
import com.degloba.utils.Cadenes;

import com.insacosa.utils.FilterBeanInmobles;
import com.insacosa.utils.FormHBM;
import com.insacosa.utils.HtmlDinamic;
import com.insacosa.Inmobles.webui.InmobleCaractItemDto;
import com.insacosa.Inmobles.webui.InmobleItemDto;
import com.insacosa.Inmobles.webui.UsuariItemDto;
import com.degloba.utils.Utils;
import com.insacosa.controladorMSG.ChatBean;


import com.google.appengine.api.datastore.Key;
import com.google.common.collect.Maps;

// IOC - Guice
/*import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.modules.BillingModule;*/

// SERVEIS APLICACIO
import com.insacosa.Inmobles.application.services.CaracteristiquesApplicationService;
import com.insacosa.Inmobles.application.services.InmoblesApplicationService;
import com.insacosa.Inmobles.application.services.UsuarisAplicationService;


import com.insacosa.dataModels_JPA.JPADataModel;

import com.insacosa.Inmobles.domain.Caracteristiques;
import com.insacosa.Inmobles.domain.Ciutats;
import com.insacosa.Inmobles.domain.Fotos;
import com.insacosa.Inmobles.domain.InmobleCaract;
import com.insacosa.Inmobles.domain.Inmobles;
import com.insacosa.Inmobles.domain.Provincies;
import com.insacosa.Inmobles.domain.Solicituds;
import com.insacosa.Inmobles.domain.Tipus;
import com.insacosa.Inmobles.domain.Usuaris;
import com.insacosa.Inmobles.domain.ValuesCaracteristiques;
import com.insacosa.dragdrop.DragDropBeanCaract;


@ManagedBean(name = "inmoblesForm")
@SessionScoped
public class InmobleForm  implements Serializable
{
	
	
	// FinderS (lectura)
	//---------------------
	 
    @Inject
    private SolicitudsFinder solicitudsFinder;
    @Inject
    private TipusFinder tipusFinder;
    @Inject
    private InmoblesFinder inmoblesFinder;
    @Inject
    private CiutatsFinder ciutatsFinder;
    @Inject
    private UsuarisFinder usuarisFinder;   
    @Inject
    private CaracteristiquesFinder caracteristiquesFinder;

    
    
	// SERVEIS D'APLICACIO
	//---------------------
	
	CaracteristiquesApplicationService<?> caracteristiquesService;
	InmoblesApplicationService<?> inmoblesService;
	UsuarisAplicationService<?, ?> usuarisService;
    
	
	private static final long serialVersionUID = 1L;
	
	//********************************
	// Propietats Fixes Inmoble (VO)
	//********************************
	private String key;
  	private String nom; // referencia
  	private String adreca;
	private Key keyTipus;  // PIS
	private String idTipus = "1";  // PIS
	
	private Short numero,planta;	
	private String puerta;
	private Short metres = 0;
	private long preu;


	private String smallImageURL;

	private String venedor;  
	private List<FotoForm> fotos = new ArrayList<FotoForm>();
	private int numSol;  // numero solicituds de l'inmoble

		
	//*********************
	// propietats auxiliars
	//*********************
  	private String currentInmobleIndex; 
  	private int page = 1; //paginacio

  	private boolean visitat = true; // per indicar que la informacio de l'inmoble ha modificat i encara no l'hem revisat
	private boolean nou = true; //per saber si es insertable
	private boolean modificable = false;  // per saber si es modificable
	private boolean cercable = false;  //quan estic cercant
	
	
  	//*********************
	// llistes/consultes
	//*********************
	private List<InmobleForm> inmoblesBuscats = new ArrayList<InmobleForm>();
	private List<Inmobles> inmoblesVenedor; // inicialitzo a null
	private List<InmobleCaractItemDto> inmoblesVenedorCaract = new ArrayList<InmobleCaractItemDto>(); // inicialitzo 
	private List<Inmobles> inmoblesSolicitatsPerComprador; // inmobles solicitats per un possible comprador
	private List<UserForm> compradors = new ArrayList<UserForm>(); // llista de compradors/solicitants de l'inmoble
	private List<Usuaris> solicitantsInmobleVenedor = new ArrayList<Usuaris>();
	
	
	/*----------------------------------------------------*/
	/* Filtre ràpid (ex: tipus, habitacions, banys,...) --*/
	/*----------------------------------------------------*/
	private List<InventoryItem> allInventoryItems = null;
    private List<InventoryFiltreList> InventoryFiltreLists = null;
	 
	
	FacesContext facesContext;
	
	/*---------------------------------------------------------------*/
	/* Contenidors pagina JSF dinamica - formulari entrada, datatable*/
	/*---------------------------------------------------------------*/
	private HtmlPanelGroup containerControlsDinamics; // contenidor on hi posem els UIComponents generats dinamicament
	private HtmlPanelGroup containerHtmlDataTableFS; // contenidor on hi posem la datatable dinamica segon columnes a visibilitzar
	private HtmlPanelGroup containerHtmlDataTableVenedor; // contenidor on hi posem la datatable dinamica inmobles Venedor

	
	
	/* extendedDatatable */
	private Collection<UserForm> selection; 
	
	
	// visibilitats de columnes de taula en funcio del que s'ha seleccionat
	// <id_caract,visibilitat>
	private static Map<Long, Boolean> columnesVisibles = new HashMap<Long, Boolean>();  
							 static { 
								 columnesVisibles.put(73L, true); 
								 columnesVisibles.put(74L, true); 
								 columnesVisibles.put(75L, true); 
							    }
							 
	// indica la operacio numerica (<= , = , >,...) sobre la columna
	// <idCaracteristica,operacio>
	private Map<String, String> columnesOperacions = new HashMap<String, String>();  
	
	// Valors de les caracteristiques dels controls UI d'entrada
	// <idCaracteristica,valor>
	private Map<String, Object> valorsCaracts = Maps.newHashMap();

	// l'objecte InmobleCaract (row de la taula) seleccionat
	private InmobleCaract currentInmobleCaract;
	
	
	/*-------------------------------------------------------*/
	/* DATAMODEL + JPA + HIBERNATE + RICHFACES               */ 
	/*-------------------------------------------------------*/


	static private Boolean cambiDades = false;
	
	

	
	
	public Boolean getCambiDades() {
		return cambiDades;
	}

	public void setCambiDades(Boolean cambiDades) {
		InmobleForm.cambiDades = cambiDades;
	}


		private static final class InmobleFormDataModel extends JPADataModel<InmobleCaract> {  
	    	
	    	public InmobleFormDataModel(EntityManager entityManager,
					Class<InmobleCaract> entityClass) {
				super(entityManager, entityClass);
				// TODO Auto-generated constructor stub
			}

				@Override
				protected Object getId(InmobleCaract t) {
					// TODO Auto-generated method stub
					return t.getKeyInmoble();
				}

			/*private InmobleFormDataModel(EntityManager entityManager) {             
	    		super(entityManager, Inmobles.class);        
	    		}   
	    	*/ 
	    
        } 
	    
    private Map<String, SortOrder> sortOrders = Maps.newHashMapWithExpectedSize(1);
    
    /*--------------------------------------------------------------*/
    /* Valors dels filtres datatable. <idcaracteristica,valor>      */
    /*--------------------------------------------------------------*/
	private Map<String, Object> filterValues = Maps.newHashMap();           
	private String sortProperty; 
	
		
	public void toggleSort() {   
		
		for (Entry<String, SortOrder> entry : sortOrders.entrySet()) {             
			SortOrder newOrder;                           
			if (entry.getKey().equals(sortProperty)) {                 
				if (entry.getValue() == SortOrder.ascending) 
				{                     
					newOrder = SortOrder.descending;                 
					} 
				else {                     
					newOrder = SortOrder.ascending;                 
				}             } 
			else {                 
				newOrder = SortOrder.unsorted;             
			}                           
	entry.setValue(newOrder);         
	}     
		}       
	
	

	public InmobleCaract getCurrentInmobleCaract() {
		return currentInmobleCaract;
	}


	public void setCurrentInmobleCaract(InmobleCaract currentInmobleCaract) {
		this.currentInmobleCaract = currentInmobleCaract;
	}


	public Object getDataModel() {         
		return new InmobleFormDataModel(EMF.lookupEntityManager(),null);     
		}
	
	
	public String getSortProperty() {
		return sortProperty;
	}


	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}


	public Map<String, Object> getFilterValues() {
		return filterValues;
	}

	
	public void setFilterValues(Map<String, Object> filterValues) {
		this.filterValues = filterValues;
	}

	public Map<String, SortOrder> getSortOrders() {
		return sortOrders;
	}


	public void setSortOrders(Map<String, SortOrder> sortOrders) {
		this.sortOrders = sortOrders;
	}

	/*-------------------------------------------------------*/
	/*-------Fi DATAMODEL + JPA + HIBERNATE + RICHFACES------*/
	/*-------------------------------------------------------*/
	
	


	public Map<Long, Boolean> getColumnesVisibles() {
		return columnesVisibles;
	}


	public Map<String, String> getColumnesOperacions() {
		return columnesOperacions;
	}

	public void setColumnesOperacions(Map<String, String> columnesOperacions) {
		this.columnesOperacions = columnesOperacions;
	}


	public HtmlPanelGroup getContainerHtmlDataTableFS() {
		return this.containerHtmlDataTableFS;
		
	}


	public void setContainerHtmlDataTableFS(HtmlPanelGroup containerHtmlDataTableFS) {
		
		if (containerHtmlDataTableFS.getChildren().isEmpty())   // la primera vegada dins el HtmlPanelGroup no hi
															  // ha cap datatable i per tant la construim
		{
		
			
			 
		    Tipus tipus = new Tipus();
		    //tipus.setKey(keyTipus);
		    //tipus.setKey(keyTipus);
		    
		    List<Caracteristiques> lc = caracteristiquesFinder.caractTipus(tipus,1, true);
		 
			
			UIDataTable datatable = HtmlDinamic.buildDatatableFS(
					"dyn_taulaInmobles",
					"#{inmobleForm.dataModel}",
					lc,
					columnesVisibles, 
					10,
					"odd-row, even-row",
					"stable",
					"datatable");
			
				
			containerHtmlDataTableFS.getChildren().add(datatable);
		}
			
		this.containerHtmlDataTableFS = containerHtmlDataTableFS;
	}

	
	
	
	
	public HtmlPanelGroup getContainerHtmlDataTableVenedor() {
		return this.containerHtmlDataTableVenedor;
	}


	public void setContainerHtmlDataTableVenedor(HtmlPanelGroup containerHtmlDataTableVenedor) {
		
		// la primera vegada dins el HtmlPanelGroup no hi ha cap datatable i per tant la construim
		if (containerHtmlDataTableVenedor.getChildren().isEmpty())   
		{	
			 
		    Tipus tipus = new Tipus();
		    //tipus.setKey(keyTipus);
		    
		    List<Caracteristiques> lc = caracteristiquesFinder.caractTipus(tipus,1, true);  // caracteristiques per tipus d'inmoble , INCLOU LES CARACT COMUNES I NO BOOLEANES
		    
			UIDataTable datatable = HtmlDinamic.buildDatatable(
							"dyn_taulaInmoblesVenedor",
							"#{inmobleForm.inmoblesVenedorCaract}",
							lc,
							columnesVisibles, 
							10,
							"odd-row, even-row",
							"stable",
							"datatable");
			
			containerHtmlDataTableVenedor.getChildren().add(datatable);
		}

		this.containerHtmlDataTableVenedor = containerHtmlDataTableVenedor;
	}


	public HtmlPanelGroup getContainerControlsDinamics() {
		return this.containerControlsDinamics;
	}

	
	public void setContainerControlsDinamics(HtmlPanelGroup containerControlsDinamics) {
	
		// la primera vegada dins el HtmlPanelGroup no hi
		// ha cap control dinamic i per tant el construim. Aquest depen del tipus d'inmboble
		if (containerControlsDinamics.getChildren().isEmpty())   
		{	
			buildControlsDinamics(containerControlsDinamics, this.idTipus); 
		}
		
		this.containerControlsDinamics = containerControlsDinamics;

	}



	// Constructor por defecto.
  	public InmobleForm() 
  	{	
		
  		
		/* IOC = Spring */
		   ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/configurationContext.xml");
		   BeanFactory factory = context;
		   
		   inmoblesService = (InmoblesApplicationService<?>) factory
			        .getBean("inmoblesApplicationService");
		   
		   caracteristiquesService = (CaracteristiquesApplicationService<?>) factory
			        .getBean("caracteristiquesApplicationService");
		   
		   
		   
  		
  		
        sortOrders.put("nom", SortOrder.unsorted);
        //sortOrders.put("surname", SortOrder.unsorted);
        //sortOrders.put("email", SortOrder.unsorted);
  	}
  	
  	
	public void clearFiltreInmoble()
	{
		facesContext = FacesContext.getCurrentInstance(); 
		FilterBeanInmobles filtreBeanInmobles = (FilterBeanInmobles) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{filterBeanInmobles}", FilterBeanInmobles.class);
		
		// Modifiquem la provincia  i localitat dels corresponents filtres 
		filtreBeanInmobles.setLocalitatFilter("967");
		filtreBeanInmobles.setProvinciaFilter("8");
		filtreBeanInmobles.setMetresFilter(0);
		filtreBeanInmobles.setPreuFilter(0);
		/*filtreBeanInmobles.setTipusFilter(1);*/
	}

	
	
	/*
	 * Si volem "carregar" un inmoble, per exemple de la llista
	 * d'inmobles del venedor
	 */
	public void editarInmoble() {
		
		setNou(true);
		setModificable(true);

		String keyInmoble = getKey();

		Inmobles inmoble = inmoblesFinder.detallInmoble(keyInmoble);
		
		// Assignem els valors del formulari (inmoble)
		setKey(inmoble.getInmobleKey());
	    setNom(inmoble.getNom());
	    setAdreca(inmoble.getAdreca());
	   
	    Provincies prov = new Provincies();
	    prov.setProvinciaKey(inmoble.getProvincies().getProvinciaKey()); 

	    Ciutats ciut = new Ciutats();
	    ciut.setCiutatKey(inmoble.getCiutats().getCiutatKey());  
	    
	    //setKeyTipus(inmoble.getTipus().getKey()); 
	    
	    setNumero(inmoble.getNumero());
	    setPlanta(inmoble.getPlanta());
	    setPuerta(inmoble.getPuerta());
	    setMetres(inmoble.getMetres());
	    setPreu(inmoble.getPreu());
		
		//setVenedor(inmoble.getUsuaris().getUsuariKey() ); //
	    setVisitat(inmoble.isVisitat());
	    
	    
	    // carreguem les fotos de l'inmoble
	    // --------------------------------
		Set<Fotos> fotos = inmoble.getFotoses();
		List<FotoForm> fotosForm = new ArrayList<FotoForm>();
		
		// Llista pels pujats
		ArrayList<UploadedImage> uploadedImages = new ArrayList<UploadedImage>();
		
		Iterator<?> it = fotos.iterator();
		while (it.hasNext())
		{
			FotoForm fotoForm = new FotoForm();
			
			Fotos foto = (Fotos) it.next();
			
			//foto.setKey(foto.getKey());
			//foto.setImatge(foto.getImatge());
			foto.setDescripcio(foto.getDescripcio());
			//foto.setKeyInmoble(foto.getInmobles().getInmobleKey());
			
			fotosForm.add(fotoForm);
			
			// carreguem en el control de imatges pujades
			UploadedImage uploadedImage = new UploadedImage();
			//uploadedImage.setData(foto.getImatge());
			//uploadedImage.setLength(foto.getImatge().length);
			uploadedImage.setName(foto.getDescripcio());
			
			uploadedImages.add(uploadedImage);
			
		}
		setFotos(fotosForm);
		
		// actualitzem la llista de fitxers pujats
		FileUploadBean fileUploadBean = (FileUploadBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{fileUploadBean}", FileUploadBean.class);
		fileUploadBean.setFiles(uploadedImages);
		
		
		// carreguem les caracteristiques seleccionades de l'inmoble
		// --------------------------------------------------------
		Set<Caracteristiques> caracteristiquesInmoble = inmoble.getCaracteristiqueses();
		
		DragDropBeanCaract dragDropBean = (DragDropBeanCaract) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{dragDropBean}", DragDropBeanCaract.class);
		
		dragDropBean.getTarget().clear();
		cambiaCaracteristiquesNoSel(getKeyTipus()); // inicialitzo la llista en funcio del tipus d'inmoble
		
		it = caracteristiquesInmoble.iterator();
		while (it.hasNext())
		{
			CaracteristicaForm caracteristicaForm = new CaracteristicaForm();
			
			Caracteristiques caracteristicaInmoble = (Caracteristiques) it.next();
			caracteristicaForm.setKey(caracteristicaInmoble.getCaracteristicaKey());
			//caracteristicaForm.setKeyTipus(caracteristicaInmoble.getTipus().getTipusKey());
			caracteristicaForm.setNom(caracteristicaInmoble.getNom());
			
			if (caracteristicaInmoble.getControl() == 0)  // NOMES LES CARACTERISTIQUES BOOLEANES
			{
				// afegim a la llista de caracteristiques seleccionades
				dragDropBean.getTarget().add(caracteristicaInmoble);  
				
				// eliminem l'objecte de la llista de caracteristiques No Seleccionades
				// que coincideixi les seves propietats amb el del objecte que si es troba
				// a la llista de caracteristiques Seleccionade
				dragDropBean.getSource().remove(existeixElement(caracteristicaInmoble, dragDropBean.getSource()));  // eliminem de la llista de l'esquerra
			}
			
		}	
	}
	
	
	
	/**************************************************
	 * Metodes : Punt de vista del possible comprador
	 **************************************************/
	 
	
	/*
	 * llista tots els inmobles solicitats per un determinat comprador (mirar LOPD)
	 */
	public List<Inmobles> getInmoblesSolicitatsPerComprador()throws Exception
	{
		 synchronized (this) {
			 
			 if (inmoblesSolicitatsPerComprador == null) {
				 
	           	List<Inmobles> inmoblesSolicitatsPerComprador = new ArrayList<Inmobles>();
	    				
				if (!compradors.isEmpty()) // en el cas inicial que no hi ha cap seleccionat
				{
					inmoblesSolicitatsPerComprador = inmoblesFinder.inmoblesSolicitatsPerUsuari(new Long(1));
										
				}
			 }
		 }
				
		return inmoblesSolicitatsPerComprador;
   }
	
	
  	/*
  	 * 
  	 */
	public Inmobles getDetallInmoble(String keyInmoble) {		    
		
		Inmobles inmoble = inmoblesFinder.detallInmoble(keyInmoble);
	    
	    return inmoble;
	      	
	}	
  	
	/*
	 * Un posible comprador selecciona (s'interessa) per algun/s inmoble)
	 */
	public void solicitarInmobles()
	{
		// Recuperem l'usuari (comprador) de sessio
		facesContext = FacesContext.getCurrentInstance(); 
		UserForm compradorForm = (UserForm) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{userForm}", UserForm.class);
		
		String keyInmoble = currentInmobleIndex;
		
		Solicituds solicitud = new Solicituds();
				
		// Construim objecte Usuari i objecte Inmoble
		Usuaris usuari = new Usuaris();
		
		usuari.setUsuariKey(compradorForm.getGuid() );
		
		Inmobles inmoble = new Inmobles();
		inmoble.setInmobleKey(keyInmoble);
		
		solicitud.setInmoble(inmoble);
		solicitud.setUsuari(usuari);
		
		inmoblesService.solicitarInmobles(solicitud);
		
		
		// Notificacio time-real JMS
		ChatBean ch = new ChatBean();
		ch.solicitudInmoble();
		
		
	}
	
	
	/*
	 * Solicitants (UserForm) d'un inmoble del venedor en sessio
	 */
	public List<Usuaris> getSolicitantsInmobleVenedor()
	{
		synchronized (this) { 
			
			if (solicitantsInmobleVenedor == null)
			{
			
				try
				{	
					Inmobles inmoble = new Inmobles();
					
					solicitantsInmobleVenedor = usuarisFinder.solicitantsInmoble(inmoble);
					
					setSolicitantsInmobleVenedor(solicitantsInmobleVenedor);
				}
				catch(Exception ex)
				{
				
				}
			}
		}
			
		return solicitantsInmobleVenedor;

	}
	
	
	
	public void setSolicitantsInmobleVenedor(
			List<Usuaris> solicitantsInmobleVenedor2) {
		this.solicitantsInmobleVenedor = solicitantsInmobleVenedor2;
	}

	public void esborrarInmobles()throws Exception 
	{

		String keyInmoble = currentInmobleIndex;
			
		InmobleItemDto inmoble = new InmobleItemDto();
		inmoble.setInmobleKey(keyInmoble);
		
		inmoble = inmoblesService.esborrarInmoble(keyInmoble);
	
	}
	  	
	public void modificarInmobles()throws Exception 
	{		
		String keyInmoble = currentInmobleIndex;
		
		InmobleCaract inmobleSeleccionat = currentInmobleCaract;
					
	
		Map<Key,String> valorsCaract = inmobleSeleccionat.getCaractInmobles();
		
		Iterator<?> itValorsCaract = valorsCaract.entrySet().iterator();
		while (itValorsCaract.hasNext())
		{
			
			Map.Entry<String, String> caracteristica = (Map.Entry<String, String>)itValorsCaract.next();
			
			try {
				
				// NOMES MODIFIQUEM SI EL (keyInmoble,IDCARACTERISTICA) EXISTEIX
				inmoblesService.modificarValorCaract(caracteristica.getKey(), keyInmoble, String.valueOf(caracteristica.getValue()));
				
			}
			catch(Exception ex)
			{
				
			}
		}
		
		
		//inmoblesService.modificarInmoble(inmoble);
	
		
	}
	  	
	public String afegirInmoble() 
	{
		setNou(false); 
		setModificable(false);

		Inmobles inmoble = new Inmobles();
		
		inmoble.setNom(this.getNom());
		inmoble.setAdreca(this.getAdreca());
		
		Ciutats ciutat = new Ciutats();
		//ciutat.setKey()
		//inmoble.setCiutats(ciutats);
		//inmoble.setProvincies(provincies);
		inmoble.setPreu(preu);
		
		inmoblesService.afegirInmoble(inmoble);
		
		
		Set<Entry<String, Object>> keysValors = valorsCaracts.entrySet();
		
		Iterator<Entry<String, Object>> it = keysValors.iterator();
		
		while (it.hasNext())
		{
			Entry<String,Object> cv = (Entry<String, Object>) it.next();
		
			ValuesCaracteristiques vc = new ValuesCaracteristiques();
			
			vc.setInmobles(inmoble); // 1
			vc.setValue(String.valueOf(cv.getValue()));  // 2 convertim un Objecte (Integer, String,..) a String
			
			Caracteristiques c = new Caracteristiques();
			c.setCaracteristicaKey(cv.getKey());
			
			vc.setCaracteristiques(c);  // 3
			/*	
			ValuesCaracteristiquesId vcId = new ValuesCaracteristiquesId();
			vcId.setKeycaracteristica(cv.getKey());
			vcId.setKeyinmoble(inmoble.getKey());
			vc.setKey(vcId);  // 4
			
			r.afegirValorCaract(vc);
			*/
			
		}
		
		
		setNou(true);
		
	    return "Success";
	
	}

	
	
	
	
	/*
	 * Llista de inmobles publicats per un venedor
	 */
	public List<Inmobles> getInmoblesVenedor() {
		
		synchronized (this) {
          	
			
				List<Inmobles> inmoblesVenedor = new ArrayList<Inmobles>();
    	
				InmobleItemDto inmoble = null;
				
				facesContext = FacesContext.getCurrentInstance(); 
				UserForm userForm = (UserForm) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{userForm}", UserForm.class);				
				
				UsuariItemDto usuari = new UsuariItemDto();

				usuari.setUsuariKey(userForm.getGuid());
				inmoblesVenedor = inmoblesFinder.inmoblesVenedor(usuari);
	
				
			    setInmoblesVenedor(inmoblesVenedor);
        
        }
            
		return inmoblesVenedor;
	}
	
	
	
	/*
	 * extendedDatatable
	 * Seleccionem un/s usuaris comprador d'una llista multiseleccio
	 */
    public void selectionListener(AjaxBehaviorEvent event) { 
    	
    	
    	UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();         
    	Object originalKey = dataTable.getRowKey();
    	
    	compradors.clear();         
    	for (Object selectionKey : selection) {             
    		dataTable.setRowKey(selectionKey);  
    		
    		if (dataTable.isRowAvailable()) {   
    			
    			compradors.add((UserForm) dataTable.getRowData());
    			             
    		}         
    	}         
    dataTable.setRowKey(originalKey);     
    } 
	
	
	


	
	/********************************************
	 * 
	 * getter - setters
	 * 
	 ********************************************/
	
    
    
    
    
	public void setInmoblesVenedor(List<Inmobles> inmoblesVenedor2) {
		this.inmoblesVenedor = inmoblesVenedor;
	}

	
	public void setInmoblesVenedorCaract(List<InmobleCaractItemDto> inmoblesVenedorCaract) {
		this.inmoblesVenedorCaract = inmoblesVenedorCaract;
	}


	public void setInmoblesSolicitatsPerComprador(
			List<Inmobles> inmoblesSolicitatsPerComprador) {
		this.inmoblesSolicitatsPerComprador = inmoblesSolicitatsPerComprador;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String id) {
		this.key = key;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdreca() {
		return adreca;
	}

	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}


	public String getSmallImageURL() 
  	{
	    return smallImageURL;
  	}

	public void setSmallImageURL(String newSmallImageURL) 
  	{
	    smallImageURL = newSmallImageURL;
  	}

  	
  	public Key getKeyTipus() {
		return keyTipus;
	}

	public void setKeyTipus(Key keyTipus) {
		this.keyTipus = keyTipus;
		
		facesContext = FacesContext.getCurrentInstance(); 
		FilterBeanInmobles filtreBeanInmobles = (FilterBeanInmobles) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{filterBeanInmobles}", FilterBeanInmobles.class);
		
		filtreBeanInmobles.setTipusFilter(keyTipus);
	}

	
	public Short getNumero() {
		return numero;
	}

	public void setNumero(Short numero) {
		this.numero = numero;
	}

	public Short getPlanta() {
		return planta;
	}

	public void setPlanta(Short planta) {
		this.planta = planta;
	}

	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public Short getMetres() 
  	{
	    return metres;
  	}
	
  	public void setMetres(Short newMetres) 
  	{
	    metres = newMetres;
  	}  	
	
    	
  	public long getPreu() 
  	{
	    return preu;
  	}
	
  	public void setPreu(long newPreu) 
  	{
	    preu = newPreu;
  	}
  	
  	public String getVenedor() 
  	{
	    return venedor;
  	}
	
  	public void setVenedor(String venedor) 
  	{
	    this.venedor = venedor;
  	}
  	
  
	public List<FotoForm> getFotos() {
		return fotos;
	}

	public void setFotos(List<FotoForm> fotos) {
		this.fotos = fotos;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<InmobleForm> getInmoblesBuscats() {
		return inmoblesBuscats;
	}

	public void setInmoblesBuscats(List<InmobleForm> InmoblesBuscats) {
		this.inmoblesBuscats = InmoblesBuscats;
	}


	public String getCurrentInmobleIndex() {
		return currentInmobleIndex;
	}

	public void setCurrentInmobleIndex(String currentInmobleIndex) {
		this.currentInmobleIndex = currentInmobleIndex;
	}

	
	
	/****************************************************/
	/* Metodes necessaris per Filtrar (exemple : tipus) */
	/****************************************************/
	
	/*
	 * retorna tots els items (en el nostre cas inmobles)
	 */
    public List<InventoryItem> getAllInventoryItems() {
    	
        synchronized (this) {
        	
            if (allInventoryItems == null) {
                allInventoryItems = new ArrayList<InventoryItem>();

                facesContext = FacesContext.getCurrentInstance(); // Contexte JSF
        		UserForm userForm = (UserForm) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{userForm}", UserForm.class);
         		
        		UsuariItemDto usuari = new UsuariItemDto();

    			usuari.setUsuariKey(userForm.getGuid());
        		
        		Iterator<?> it = inmoblesService.inmoblesVenedor(usuari).iterator();
        		while (it.hasNext())
        		{
        			InventoryItem inmobleForm = new InventoryItem();
        			Inmobles inmoble = (Inmobles)it.next();
          			
        			inmobleForm.setKey(inmoble.getInmobleKey());
        			inmobleForm.setNom(inmoble.getNom());
        			inmobleForm.setAdreca(inmoble.getAdreca());
        		    inmobleForm.setLocalitat(inmoble.getCiutats().getCiutatKey());
        		    
        		    inmobleForm.setProvincia(inmoble.getProvincies().getProvinciaKey());
        			
        			//inmobleForm.setKeyTipus(inmoble.getTipus().getKey());

        			inmobleForm.setNumero(inmoble.getNumero());
        			inmobleForm.setPlanta(inmoble.getPlanta());
        			inmobleForm.setPuerta(inmoble.getPuerta());
        			inmobleForm.setMetres(inmoble.getMetres());
        			inmobleForm.setPreu(inmoble.getPreu());
        		    
        			// Objecte venedor		
        				
        			//inmobleForm.setVenedor(inmoble.getUsuaris().getUsuariKey());
        				
        			inmobleForm.setVisitat(inmoble.isVisitat());
        		    
        			allInventoryItems.add(inmobleForm);
        		}
 
            }
        }
        return allInventoryItems;
    }

  
    private void itemToTipusItem(InventoryItem item, InventoryFiltreItem newItem) {
        newItem.setAdreca(item.getAdreca());

        newItem.setKey(item.getKey());
        newItem.setImatge(item.getImatge());
        newItem.setKeyTipus(item.getKeyTipus());
        newItem.setLocalitat(item.getLocalitat());
        
        newItem.setMetres(item.getMetres());
        newItem.setNom(item.getNom());
        newItem.setPlanta(item.getPlanta());
        newItem.setPreu(item.getPreu());
        newItem.setProvincia(item.getProvincia());
        
        newItem.setPuerta(item.getPuerta());
        newItem.setSmallImageURL(item.getSmallImageURL());
       
        newItem.setVenedor(item.getVenedor());
    }
	
    /*
     * Construeix una llista de llistes on cada element de la llista
     * conté el valor (agrupacio) del filtre i els items (en el nostre cas
     * inmobles) amb aquest valor de filtre
     */
    public List<InventoryFiltreList> getInventoryFiltreLists() {
        synchronized (this) {
            if (InventoryFiltreLists == null) {
                InventoryFiltreLists = new ArrayList<InventoryFiltreList>();
                List<InventoryItem> inventoryItems = getAllInventoryItems();

                Collections.sort(inventoryItems, new Comparator<InventoryItem>() {
                    public int compare(InventoryItem o1, InventoryItem o2) {
                        return o1.getTipusStr().compareTo(o2.getTipusStr());  //1. representa sobre el que comparem
                        //return o1.getHabitacions().compareTo(o2.getHabitacions());
                        //return o1.getBanys().compareTo(o2.getBanys());
                    }
                });
                
                Iterator<InventoryItem> iterator = inventoryItems.iterator();
                InventoryFiltreList filtreList = new InventoryFiltreList();
                filtreList.setFiltre(inventoryItems.get(0).getTipusStr()); // 2. representa sobre el que comparem
                //filtreList.setFiltre(inventoryItems.get(0).getHabitacions()); 
                while (iterator.hasNext()) {
                    InventoryItem item = iterator.next();
                    InventoryFiltreItem newItem = new InventoryFiltreItem();
                    itemToTipusItem(item, newItem);
                    // comparem per un determinat FILTRE 
                    //(ex: agrupat per tipus d'inmoble, agrupats per #habitacions,
                    // agrupat per #banys,...)
                    
                    
                    if (!item.getTipusStr().equals(filtreList.getFiltre())) { // 3. representa sobre el que comparem
                    //if (!item.getHabitacions().equals(tipusList.getFiltre())) {
                        InventoryFiltreLists.add(filtreList);
                        filtreList = new InventoryFiltreList();
                        filtreList.setFiltre(item.getTipusStr());  // 4. representa sobre el que comparem
                        //tipusList.setFiltre(item.getHabitacions());  
                    }
                    filtreList.getFiltreItems().add(newItem);
                }
                InventoryFiltreLists.add(filtreList);
            }
        }
        return InventoryFiltreLists;
    }

 	/*
 	 * Retorna la llista de valors d'un determinat Filtre
 	 * ex : si Filtre es "tipus" retorna (PIS,APARTAMENT,CASA ADOSADA,...)
 	 * ex : si Filtre es "habitacions" retorna (1,2,3,4,5,6,...)
 	 * ex : si Filtre es "banys" retorna (1,2,3,4,5,6,...)
 	 */
    public List<String> getAllTipus() {
        List<String> result = new ArrayList<String>();
        for (InventoryFiltreList filtreList : getInventoryFiltreLists()) {
            result.add(filtreList.getFiltre());
        }
        return result;
    }
    
    /****************************************************/
	/* FI Metodes necessaris per Filtrar (exemple : tipus) */
	/****************************************************/
	 
    
    
    
    
    
	public boolean isNou() {
		return nou;
	}

	public void setNou(boolean nou) {
		this.nou = nou;
	}

	public boolean isModificable() {
		return modificable;
	}

	public void setModificable(boolean modificable) {
		this.modificable = modificable;
	}

	public boolean isCercable() {
		return cercable;
	}

	public void setCercable(boolean cercable) {
		this.cercable = cercable;
	}

	public int getNumSol() {
		return numSol;
	}

	public void setNumSol(int numSol) {
		this.numSol = numSol;
	}

	
	/* NO S'UTILITZA.Es per rich:listShuttle (només Richfaces 3 ---- */
	/* Caracteristiques (subtipus) d'un tipus d'inmoble ------------ */
	public List<CaracteristicaForm> getCaractFreeItems() {
		
		Tipus tipus = new Tipus();
		/*tipus.setKey(1);*/
				
		/*Injector injector = Guice.createInjector(new BillingModule()); 
		ICaracteristiques caracteristiques_app = injector.getInstance(ICaracteristiques.class);*/
		
		List<Caracteristiques> caracteristiques = caracteristiquesFinder.caractTipus(tipus);
		
		return FormHBM.passarCaracteristiquesHBMtoForm(caracteristiques);
		
	}
	

	public List<UserForm> getCompradors() {
		return compradors;
	}

	public void setCompradors(List<UserForm> compradors) {
		this.compradors = compradors;
	}

	public Collection<UserForm> getSelection() {
		return selection;
	}

	public void setSelection(Collection<UserForm> selection) {
		this.selection = selection;
	}

	public boolean isVisitat() {
		return visitat;
	}

	public void setVisitat(boolean visitat) {
		this.visitat = visitat;
	}
	
		
	public Map<String, Object> getValorsCaracts() {
		return valorsCaracts;
	}

	public void setValorsCaracts(Map<String, Object> valorsCaracts) {
		this.valorsCaracts = valorsCaracts;
	}

	/*
	 * Reconstruim els controls dinamics en funcio del tipus d'inmoble
	 */
	public void buildControlsDinamics(HtmlPanelGroup containerControlsDinamics, String idTipus)
	{
		
		Map<Long, Boolean> valueMapTemp = new HashMap<Long, Boolean>();  // indica visibilitats de columnes de taula en funcio del que s'ha seleccionat

		valueMapTemp.put(73L, columnesVisibles.get(73L) != null ? columnesVisibles.get(73L) : true);
		valueMapTemp.put(74L, columnesVisibles.get(74L) != null ? columnesVisibles.get(74L) : true);
		valueMapTemp.put(75L, columnesVisibles.get(75L) != null ? columnesVisibles.get(75L) : true);
		
		containerControlsDinamics.getChildren().clear();

		/*Injector injector = Guice.createInjector(new BillingModule()); 
		ICaracteristiques caracteristiques_app = injector.getInstance(ICaracteristiques.class);*/
		
		Tipus tipus = new Tipus();
		//tipus.setKey(keyTipus);
		//tipus.setKey(keyTipus);
	
		List<Caracteristiques> c = caracteristiquesFinder.caractTipus(tipus,1, false);  // seleccionem els que no son booleans i no son caract comunes
		
		
		// en funcio del tipus(varchar,select,...) del Tipus construim el formulari
		Iterator<?> iter = c.iterator();
		while (iter.hasNext())
		{
			Caracteristiques cc = (Caracteristiques) iter.next();
			
			if (cc.getTtpcontrol().getTipus().compareTo("ITXT") == 0)
			{
				// construim outputText
		       	containerControlsDinamics.getChildren().add(HtmlDinamic.buildOutputText(
					Cadenes.primeraLletraMajuscula(cc.getNom())
					,(ValueExpression) Utils.resolveExpression(cc.getNom() + " " + cc.getCaracteristicaKey())));
			        		
		   		// construim inputText
						        		
			   	HtmlInputText inputText = new HtmlInputText();    
				ValueExpression v = (ValueExpression) Utils.resolveExpression("#{inmobleForm.valorsCaracts[" + cc.getCaracteristicaKey() + "]}");
			    Utils.processProperty(inputText, v,  "value");
			        		
			   	containerControlsDinamics.getChildren().add(HtmlDinamic.buildInputText(
			   		"controlsdinamics_" + cc.getNom().toLowerCase() , 
			   		v, 
			      	"width:" + (10*cc.getTamanyControl()) + "px", 
			      	cc.getTamanyControl(), 
			      	"Campo requerido",   // requiredMessage
			      	cc.getTtpbasic().getBdtype().compareTo("INT") == 0 ?  "valor numérico" : "valor incorrecto",
			      	cc.getTtpbasic().getBdtype(),
			      	cc.getObligatori(),
			      	true,     // transient !!!!!!!!!!!!
			      	0,
			      	cc.getTamanyControl())
			      	);
			
			   	// misatge d'error
			   	containerControlsDinamics.getChildren().add(HtmlDinamic.buildMessageRich(
			   			"inputText_controlsdinamics_" + cc.getNom().toLowerCase(), 
			   			"color:red;font-weight:bold",
			   			true));	 // transient, true pq no dupliqui ID	
			        		
			    // construim checkbox
			       	
			    ValueExpression v2 = (ValueExpression) Utils.resolveExpression("#{inmobleForm.columnesVisibles[" + cc.getCaracteristicaKey() + "]}");
			        		
			    containerControlsDinamics.getChildren().add(HtmlDinamic.buildCheckbox(
			       	cc.getCaracteristicaKey(),  // idCaracteristica
			       	v2,
			       	new ArrayList<String>(){/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

					{add("@this");add("dyn_taulaInmobles");add("dyn_taulaInmoblesVenedor");}},
			       	new ArrayList<String>(){/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

					{add("@this");}}));
			        		
			        		
			        		
			    //CAL MODIFICAR LA HASMAP QUE ENS MARCA QUINES COLUMNES DE LA DATATABLE DINAMICA S'HAURA DE VISIBILITZAR
			  /*      		
			    if (!columnesVisibles.containsKey(cc.getKey()))
			    	valueMapTemp.put(cc.getKey() , false);  // inicialment la columna estara invisible
			    else
			    	valueMapTemp.put(cc.getKey() , columnesVisibles.get(cc.getKey()));
	        */
			}   // IF			

		} // while
		
	//} // if


		columnesVisibles = valueMapTemp;
		
	}
	
 
    
    /*
     * Per actualitza el model de dades
     */
    void actualitzarDataModel(Key keyTipus)
    {
    	
    	facesContext = FacesContext.getCurrentInstance(); // Contexte JSF
    	
    	// CUIDADO !!!!!!!!! el #{persistenceService} no s'ha de cambiar encara que es canvii de persistence-unit
		PersistenceService persistenceService = (PersistenceService) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{persistenceService}", PersistenceService.class);
		
		EntityManager em = persistenceService.getEntityManager();
        
        EntityTransaction transaction = em.getTransaction();
        
        try {

        	em.getTransaction().begin();
        	em.clear();
            
        	        	
            Tipus tipus = new Tipus();
            //tipus.setKey(keyTipus);
             
           
            
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
           //////// //em.close();
        }
        
    }
    
    
	
	
	/*
	 * Cambia la llista de caracteristiques no seleccionades en funcio del tipus d'inmoble
	 */
	private void cambiaCaracteristiquesNoSel(Key keyTipus)
	{
			
		facesContext = FacesContext.getCurrentInstance(); // Contexte JSF
		DragDropBeanCaract dragDropBean = (DragDropBeanCaract) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{dragDropBean}", DragDropBeanCaract.class);
		
		dragDropBean.getSource().clear();
/*
		Injector injector = Guice.createInjector(new BillingModule()); 
		ICaracteristiques caracteristiques_app = injector.getInstance(ICaracteristiques.class);*/
		
		Tipus tipus = new Tipus();
		//tipus.setKey(keyTipus);
		
		Iterator<Caracteristiques> iter = caracteristiquesFinder.caractTipus(tipus, 0, false).iterator();
		while (iter.hasNext())
		{
			Caracteristiques caracteristica = (Caracteristiques)(iter.next());
			
			CaracteristicaForm caract = new CaracteristicaForm();
			
			caract.setKey(caracteristica.getCaracteristicaKey());
			//caract.setKeyTipus(caracteristica.getTipus().getTipusKey());
			caract.setNom(caracteristica.getNom());
			                   
			dragDropBean.getSource().add(caracteristica);
			
		}
	}

	
	/*
	 * retorna l'objecte dins d'una llista tals que els seus camps coincideix 
	 * null : si no existeix
	 * objecte : si existeix
	 */
	private CaracteristicaForm existeixElement(Caracteristiques c, Collection<Caracteristiques> l)
	{
		
		Iterator<Caracteristiques> iter = l.iterator();
		while(iter.hasNext())
		{
			Caracteristiques lc = (Caracteristiques) iter.next();
			/*if (lc.compareTo(c) == 0)
			{	
				return lc;
			}*/
		}
		
		return null;
	}
	
	
	/*
	 * 
	 */
	private HashMap<?, ?> mergeHash(HashMap<String, Boolean> h1 , HashMap<?, ?> h2)
	{
		
		Set<String> k1 = h1.keySet();
		Set<?> k2 = h2.keySet();
		
		Iterator<String> it_h1 = k1.iterator();
		
		while (it_h1.hasNext())
		{
			String k11 = (String) it_h1.next();
			if (!h2.containsKey(k11))
				h1.remove(k11);
			else
				h2.remove(k11);
		}
		
		// afegim els elements que no estan a la primera llista pero si a la segona
		Iterator<?> it_h2 = k2.iterator();
		while (it_h2.hasNext())
		{
			String k22 = (String) it_h2.next();
			h1.put(k22, true);
		}
		
		return h2;
		
		
	}
	

	
	/*
	 * Reconstruim les llistes DragAndDrop (caracteristiques booleanes)
	 */
	public void buildLlistaDragAndDrop(Key key2)
	{

		facesContext = FacesContext.getCurrentInstance(); 
		DragDropBeanCaract dragDropBean = (DragDropBeanCaract) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{dragDropBeanCaract}", DragDropBeanCaract.class);
		
		/*Injector injector = Guice.createInjector(new BillingModule()); 
		ICaracteristiques caracteristiques_app = injector.getInstance(ICaracteristiques.class);*/
		
		Tipus tipus = new Tipus(); 
		/*tipus.setKey(keyTipus == null ? 1 : keyTipus); // cuidado*/
		
		List<Caracteristiques> novaLlista = caracteristiquesFinder.caractTipus(tipus, 0, false);  // 0 = caracteristiques booleanes
		dragDropBean.reset(novaLlista);  
		
		dragDropBean.getSource().clear();
		
		Iterator<Caracteristiques> iter = novaLlista.iterator();
		while (iter.hasNext())
			{
				Caracteristiques caracteristicaHBM = (Caracteristiques)(iter.next());
				
				dragDropBean.getSource().add(caracteristicaHBM);
			}
		
	}
	
}