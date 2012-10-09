package com.degloba.vo;


import com.degloba.filtre.InventoryItem;
import com.degloba.filtre.InventoryFiltreItem;
import com.degloba.filtre.InventoryFiltreList;

import com.degloba.interfaces.Inmoble_Impl;
import com.degloba.interfaces.Objecte;
import com.degloba.interfaces.QuerysJPA;
import com.degloba.interfaces.Usuari_Impl;

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
import java.util.Random;
import java.util.Set;

import javax.el.ValueExpression;

/*----------------------------*/
/* JSF                        */
/*----------------------------*/
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/*----------------------------*/
/* JPA -Persistencia          */
/*----------------------------*/
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.application.FacesMessages;
import org.richfaces.component.SortOrder;
import org.richfaces.component.UIDataTable;
import org.richfaces.component.UIExtendedDataTable;
import org.richfaces.component.UIRichMessage;
import org.richfaces.component.UITooltip;

/*----------------------------*/
/* Utilitats                  */
/*----------------------------*/
import com.degloba.utils.Cadenes;
import com.degloba.utils.FilterBeanInmobles;
import com.degloba.utils.FormHBM;
import com.degloba.utils.HtmlDinamic;
import com.degloba.utils.Utils;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.common.collect.Maps;

import com.degloba.controladorMSG.ChatBean;
import com.degloba.dataModels_JPA.InmobleCaract;
import com.degloba.dataModels_JPA.JPADataModel;
import com.degloba.dataModels_JPA.PersistenceService;
import com.degloba.dragdrop.DragDropBeanCaract;


import com.degloba.HBM.*;


@ManagedBean(name = "inmoblesForm")
@SessionScoped
public class InmobleForm extends Objecte implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	//********************************
	// Propietats Fixes Inmoble (VO)
	//********************************
	private String key;  // Representacio String de la "key" (datastore Google App Engine)
	
	
	
  	private String nom; // referencia
  	private String adreca;
	private Integer idTipus = 1;  // PIS
	
	private Short numero,planta;	
	private String puerta;
	private Short metres = 0;
	private long preu;


	private String smallImageURL;

	private String venedor;  // nomUusari
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
	private List<InmobleForm> inmoblesVenedor; // inicialitzo a null
	private List<InmobleCaract> inmoblesVenedorCaract = new ArrayList<InmobleCaract>(); // inicialitzo 
	private List<InmobleForm> inmoblesSolicitatsPerComprador; // inmobles solicitats per un possible comprador
	private List<UserForm> compradors = new ArrayList<UserForm>(); // llista de compradors/solicitants de l'inmoble
	private List<UserForm> solicitantsInmobleVenedor = new ArrayList<UserForm>();
	
	
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
	private Map<Long, String> columnesOperacions = new HashMap<Long, String>();  
	
	// Valors de les caracteristiques dels controls UI d'entrada
	// <idCaracteristica,valor>
	private Map<Long, Object> valorsCaracts = Maps.newHashMap();

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
	////////private static final class InmobleFormDataModel extends JPADataModel<Inmobles> {	
				
	    	
	    	public InmobleFormDataModel(EntityManager entityManager,
					Class<InmobleCaract> entityClass) {
				super(entityManager, entityClass);
				// TODO Auto-generated constructor stub
			}

				@Override
				protected Object getId(InmobleCaract t) {
					// TODO Auto-generated method stub
					return t.getIdInmoble();
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
	private Map<Long, Object> filterValues = Maps.newHashMap();           
	private String sortProperty; 
	
	
	/*--------------------------*/
    /* EntityManager - JPA      */
    /*--------------------------*/
	 private EntityManager lookupEntityManager() {         
		 FacesContext facesContext = FacesContext.getCurrentInstance(); 
		 
		// CUIDADO !!!!!! el #{persistenceService} no s'ha de cambiar encara que es canvii de persistence-unit
		 PersistenceService persistenceService = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{persistenceService}", PersistenceService.class);         
	 return persistenceService.getEntityManager();     
	 } 
	 
	
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
		return new InmobleFormDataModel(lookupEntityManager(),null);     
		}
	
	
	public String getSortProperty() {
		return sortProperty;
	}


	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}


	public Map<Long, Object> getFilterValues() {
		return filterValues;
	}

	
	public void setFilterValues(Map<Long, Object> filterValues) {
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


	public Map<Long, String> getColumnesOperacions() {
		return columnesOperacions;
	}

	public void setColumnesOperacions(Map<Long, String> columnesOperacions) {
		this.columnesOperacions = columnesOperacions;
	}


	public HtmlPanelGroup getContainerHtmlDataTableFS() {
		return this.containerHtmlDataTableFS;
		
	}


	public void setContainerHtmlDataTableFS(HtmlPanelGroup containerHtmlDataTableFS) {
		
		if (containerHtmlDataTableFS.getChildren().isEmpty())   // la primera vegada dins el HtmlPanelGroup no hi
															  // ha cap datatable i per tant la construim
		{
			// calculem la nova llista d'inmobles venedor
			Inmoble_Impl r = new Inmoble_Impl();
			 
		    Tipus tipus = new Tipus();
		    tipus.setKey(idTipus);
		    
		    List<Caracteristiques> lc = r.caractTipus(tipus,1, true);
		 
			
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
			
			// calculem la nova llista d'inmobles venedor
			Inmoble_Impl r = new Inmoble_Impl();
			 
		    Tipus tipus = new Tipus();
		    tipus.setId(idTipus);
		    
		    List<Caracteristiques> lc = r.caractTipus(tipus,1, true);  // caracteristiques per tipus d'inmoble , INCLOU LES CARACT COMUNES I NO BOOLEANES
		    
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
	    //super();
 		
        sortOrders.put("nom", SortOrder.unsorted);
        //sortOrders.put("surname", SortOrder.unsorted);
        //sortOrders.put("email", SortOrder.unsorted);
  	}
  	
  	

	
	
	
	public String clearInmoble()
	{
		facesContext = FacesContext.getCurrentInstance(); 
		
	    setNom(null);
	    setAdreca(null);
	    setIdTipus(1);  //PISO
	    
	    setNumero(null);
	    setPlanta(null);
	    setPuerta(null);
	    setMetres(null);
	    setPreu(0L);
		setVenedor(null);
	    
	    setVisitat(true);
	    
	    // Llistes ( fotos,caracteristiques seleccionades,...
	    setFotos(new ArrayList<FotoForm>());
	    
	    ///////////DragDropBeanCaract dragDropBean = (DragDropBeanCaract) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{dragDropBean}", DragDropBeanCaract.class);
	    ///////////dragDropBean.setTarget(new ArrayList<CaracteristicaForm>());
		
		// Llista pels pujats
		FileUploadBean fileUploadBean = (FileUploadBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{fileUploadBean}", FileUploadBean.class);
		fileUploadBean.clearUploadData();
		fileUploadBean.getFiles().clear();
		
		setNou(false); // es insertable
		setModificable(false); // es no modificable
		
		
		// inicialitzem els filtres de l'inmoble
		clearFiltreInmoble();
		
		return "success";
		
	}
	
	
	public void clearFiltreInmoble()
	{
		facesContext = FacesContext.getCurrentInstance(); 
		FilterBeanInmobles filtreBeanInmobles = (FilterBeanInmobles) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{filterBeanInmobles}", FilterBeanInmobles.class);
		
		// Modifiquem la provincia  i localitat dels corresponents filtres 
		filtreBeanInmobles.setLocalitatFilter(967);
		filtreBeanInmobles.setProvinciaFilter(8);
		filtreBeanInmobles.setMetresFilter(0);
		filtreBeanInmobles.setPreuFilter(0);
		filtreBeanInmobles.setTipusFilter(1);
	}

	
	
	/*
	 * Si volem "carregar" un inmoble, per exemple de la llista
	 * d'inmobles del venedor
	 */
	public void editarInmoble() {
		
		setNou(true);
		setModificable(true);

		facesContext = FacesContext.getCurrentInstance(); 
		InmobleForm inmobleForm = (InmobleForm) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{inmobleForm}", InmobleForm.class);
		
		String keyInmoble = inmobleForm.getKey();
				
		Inmoble_Impl r = new Inmoble_Impl();

		Entity inmoble = r.detallInmoble(KeyFactory.stringToKey(keyInmoble));
		
		// Assignem els valors del formulari (inmoble)
			
		setKey(KeyFactory.keyToString((Key)inmoble.getKey()));
	    setNom((String)inmoble.getProperty("Nom"));
	    setAdreca((String)inmoble.getProperty("Adreca"));
	   
	    Provincies prov = new Provincies();
	    prov.setId(inmoble.getProvincies().getId()); 

	    Ciutats ciut = new Ciutats();
	    ciut.setId(inmoble.getCiutats().getId());  
	    
	    setIdTipus(inmoble.getTipus().getId()); 
	    
	    setNumero(inmoble.getNumero());
	    setPlanta(inmoble.getPlanta());
	    setPuerta(inmoble.getPuerta());
	    setMetres(inmoble.getMetres());
	    setPreu(inmoble.getPreu());

		setVenedor(inmoble.getUsuaris().getNomusuari()); //
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
			
			fotoForm.setId(foto.getId());
			fotoForm.setImatge(foto.getImatge());
			fotoForm.setDescripcio(foto.getDescripcio());
			fotoForm.setIdInmoble(foto.getInmobles().getId());
			
			fotosForm.add(fotoForm);
			
			// carreguem en el control de imatges pujades
			UploadedImage uploadedImage = new UploadedImage();
			uploadedImage.setData(foto.getImatge());
			uploadedImage.setLength(foto.getImatge().length);
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
		cambiaCaracteristiquesNoSel(inmobleForm.getIdTipus()); // inicialitzo la llista en funcio del tipus d'inmoble
		
		/////////passarCaracteristiquesHBMtoForm(new ArrayList<Caracteristiques>(caracteristiquesInmoble));
		
		it = caracteristiquesInmoble.iterator();
		while (it.hasNext())
		{
			CaracteristicaForm caracteristicaForm = new CaracteristicaForm();
			
			Caracteristiques caracteristicaInmoble = (Caracteristiques) it.next();
			caracteristicaForm.setId(caracteristicaInmoble.getId());
			caracteristicaForm.setIdTipus(caracteristicaInmoble.getTipus().getId());
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
	public List<InmobleForm> getInmoblesSolicitatsPerComprador()throws Exception
	{
		 synchronized (this) {
			 
			 if (inmoblesSolicitatsPerComprador == null) {
				 
	           	List<InmobleForm> inmoblesSolicitatsPerComprador = new ArrayList<InmobleForm>();
	    	
				Inmoble_Impl r = new Inmoble_Impl();
				
				if (!compradors.isEmpty()) // en el cas inicial que no hi ha cap seleccionat
				{
					Iterator<?> it = r.inmoblesSolicitatsPerUsuari(FormHBM.passarUsuariFormtoHBM(compradors.get(0))).iterator();
						
					while (it.hasNext())
					{
						Inmobles inmobleHBM = (Inmobles)it.next();
							
						inmoblesSolicitatsPerComprador.add(FormHBM.passarHBMtoForm(inmobleHBM));
					}
				}
			 }
		 }
				
		return inmoblesSolicitatsPerComprador;
   }
	
	
  	/*
  	 * 
  	 */
	public InmobleForm getDetallInmoble(String idInmoble) {
		// TODO Auto-generated method stub
		    
		InmobleForm inmobleForm = new InmobleForm();
		
		Inmoble_Impl r = new Inmoble_Impl();
		
		Key k = KeyFactory.stringToKey(idInmoble);
		
		Entity inmoble = r.detallInmoble(k);
	    
		inmobleForm =  FormHBM.passarHBMtoForm(inmoble);
		    
	    return inmobleForm;
	      	
	}	
  	
	/*
	 * Un posible comprador selecciona (s'interessa) per algun/s inmoble)
	 */
	public void solicitarInmobles()
	{
		// Recuperem l'usuari (comprador) de sessio
		facesContext = FacesContext.getCurrentInstance(); // Contexte JSF
		UserForm compradorForm = (UserForm) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{userForm}", UserForm.class);
		
		////////int idInmoble = currentInmobleIndex;
		Key idInmoble = KeyFactory.stringToKey(currentInmobleIndex);
		
		InmobleCaract inmobleSeleccionat = currentInmobleCaract;
		
		///////Solicituds solicitud = new Solicituds();
		Entity solicitud = new Entity("Solicituds");
		
		Inmoble_Impl r = new Inmoble_Impl();
		
		// Construim objecte Usuari i objecte Inmoble
		Usuaris usuari = new Usuaris();
		usuari.setNomusuari(compradorForm.getNomUsuari());
		
		Inmobles inmoble = new Inmobles();
		inmoble.setId(idInmoble);
		
		solicitud.setInmobles(inmoble);
		solicitud.setUsuaris(usuari);
		r.solicitarInmobles(solicitud);
		
		
		// Notificacio time-real JMS
		ChatBean ch = new ChatBean();
		ch.solicitudInmoble();
		
		
	}
	
	
	/*
	 * Solicitants (UserForm) d'un inmoble del venedor en sessio
	 */
	public List<UserForm> getSolicitantsInmobleVenedor()
	{
		synchronized (this) { 
			
			if (solicitantsInmobleVenedor == null)
			{
			
				List<UserForm> solicitants = new ArrayList<UserForm>();
				
				try
				{	
					Inmobles inmoble = new Inmobles();
					
					inmoble.setId(getId());
					
					Inmoble_Impl r = new Inmoble_Impl();
					
					List<Usuaris> usuaris = r.solicitantsInmoble(inmoble);
					
					solicitants = FormHBM.passarUsuarisHBMtoForm(usuaris);
					setSolicitantsInmobleVenedor(solicitants);
				}
				catch(Exception ex)
				{
				
				}
			}
		}
			
		return solicitantsInmobleVenedor;

	}
	
	
	
	public void setSolicitantsInmobleVenedor(
			List<UserForm> solicitantsInmobleVenedor) {
		this.solicitantsInmobleVenedor = solicitantsInmobleVenedor;
	}

	public void esborrarInmobles()throws Exception 
	{

		Key idInmoble = KeyFactory.stringToKey(currentInmobleIndex);
			
		///////Inmobles inmoble = new Inmobles();
		Entity inmoble = new Entity("Inmobles");
		///////inmoble.setId(idInmoble);
		
		Inmoble_Impl r = new Inmoble_Impl();
		inmoble = r.inmoblePerId(idInmoble);
		
		
		try {	/*-----------------------------------------------------------------------*/
				/* Eliminem ABANS els objectes FK (ex: valors, fotos, solicituds, ...)   */
				/*-----------------------------------------------------------------------*/
	
			
				// 0.- VALORS DE LES CARACTERISTIQUES DE L'INMOBLE
				InmobleCaract caractInmoble = r.valorsCaracteristiquesInmoble(idInmoble);
				
				Set<Long> idsCaracteristica = caractInmoble.getCaractInmobles().keySet();
				
				Iterator itIdsCaracteristica = idsCaracteristica.iterator();
				while (itIdsCaracteristica.hasNext())
				{
					r.eliminarValorCaract((Long)itIdsCaracteristica.next(), idInmoble);
				}
		
			
			
				// 1.- FOTOS
				Iterator<?> it = inmoble.getFotoses().iterator();
				while (it.hasNext())
				{
					Fotos foto = (Fotos)it.next();
					
					try
					{
						r.eliminarFoto(foto);
					}
					catch (Exception ex)
					{
						
					}
				}
			
			
				
				// 2.- SOLICITUDS INMOBLE
				it = inmoble.getSolicitudses().iterator();
				while (it.hasNext())
				{
					Solicituds solicitud = (Solicituds)it.next();
					
					try
					{
						r.eliminarSolicitud(solicitud);
					}
					catch (Exception ex)
					{
						
					}
				}
			
			
				
				r.eliminarInmoble(inmoble);
				
				
				// Notificar als solicitants la seva "eliminacio" (ja no poden "pujar"/"solictar" l'inmoble)
				
				
				
				
			
			// modifiquem la llista
			inmoblesVenedor.remove(currentInmobleIndex);
		}
		catch(Exception ex)
		{
			
		}
	
	}
	  	
	public void modificarInmobles()throws Exception 
	{
		/* AIXO ES UN EXEMPLE DE AFEGIR UN MISSATGE DE VALIDACIO EN SERVIDOR CONTRA UN COMPONENT UI
		FacesContext context = FacesContext.getCurrentInstance();

		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		message.setSummary("Name Field is Blank");
		message.setDetail("Name Field is Blank..");
		context.addMessage("formulariDatatable:dyn_taulaInmoblesVenedor:3:inputText_column_it_9",message);
		context.addMessage("formulariDatatable:dyn_taulaInmoblesVenedor:3:inputText_column_it_5",message);
		*/
			


		
		//////////int idInmoble = currentInmobleIndex;
		Key idInmoble = KeyFactory.stringToKey(currentInmobleIndex);
		
		InmobleCaract inmobleSeleccionat = currentInmobleCaract;
					
		Inmoble_Impl r = new Inmoble_Impl();
		
		Map<Long,String> valorsCaract = inmobleSeleccionat.getCaractInmobles();
		
		Iterator itValorsCaract = valorsCaract.entrySet().iterator();
		while (itValorsCaract.hasNext())
		{
			
			Map.Entry<Long, String> caracteristica = (Map.Entry<Long, String>)itValorsCaract.next();
			
			try {
				
				// NOMES MODIFIQUEM SI EL (IDINMOBLE,IDCARACTERISTICA) EXISTEIX
				r.modificarValorCaract(caracteristica.getKey(), idInmoble, String.valueOf(caracteristica.getValue()));
				
			}
			catch(Exception ex)
			{
				// SI DONA ERROR PER NO PODER FER UPDATE INTENTA FER INSERT
				
				ValuesCaracteristiquesId vcId = new ValuesCaracteristiquesId();
				vcId.setIdinmoble(idInmoble);
				vcId.setIdcaracteristica(caracteristica.getKey());
				
				ValuesCaracteristiques vc = new ValuesCaracteristiques();
				vc.setId(vcId);
				
				vc.setValue(caracteristica.getValue() == null ? "" : String.valueOf(caracteristica.getValue()));
				
				r.afegirValorCaract(vc);
			}
		}
		
		
		/////r.modificarInmoble(inmoble);
		
		/////////getInmoblesVenedor().set(currentInmobleIndex, this);
		
	}
	  	
	public String afegirInmoble() 
	{
		setNou(false); //
		setModificable(false);
		
		Inmoble_Impl r = new Inmoble_Impl();
		
		
		Inmobles inmoble = new Inmobles();
		
		inmoble.setNom(this.getNom());
		inmoble.setAdreca(this.getAdreca());
		
		Ciutats ciutat = new Ciutats();
		//ciutat.setId()
		//inmoble.setCiutats(ciutats);
		//inmoble.setProvincies(provincies);
		inmoble.setPreu(preu);
		
		r.afegirInmoble(inmoble);
		
		
		Set<Entry<Long, Object>> keysValors = valorsCaracts.entrySet();
		
		Iterator it = keysValors.iterator();
		
		while (it.hasNext())
		{
			Entry<Long,Object> cv = (Entry<Long, Object>) it.next();
		
			ValuesCaracteristiques vc = new ValuesCaracteristiques();
			
			vc.setInmobles(inmoble); // 1
			vc.setValue(String.valueOf(cv.getValue()));  // 2 convertim un Objecte (Integer, String,..) a String
			
			Caracteristiques c = new Caracteristiques();
			c.setId(cv.getKey().longValue());
			
			vc.setCaracteristiques(c);  // 3
				
			ValuesCaracteristiquesId vcId = new ValuesCaracteristiquesId();
			vcId.setIdcaracteristica(cv.getKey().longValue());
			vcId.setIdinmoble(inmoble.getId());
			vc.setId(vcId);  // 4
			
			r.afegirValorCaract(vc);
			
			
		}
		
		
	/*	
	Inmobles inmoble = FormHBM.passarFormtoHBM(this);
			
		
		 // QuerysJPA qJPA= new QuerysJPA(em, Inmobles.class);
		facesContext = FacesContext.getCurrentInstance(); // Contexte JSF
		
		// CUIDADO !!!!!!! el #{persistenceService} no s'ha de cambiar encara que es canvii de persistence-unit
		PersistenceService persistenceService = (PersistenceService) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{persistenceService}", PersistenceService.class);
		
		EntityManager em = persistenceService.getEntityManager();
        
		EntityTransaction transaction = em.getTransaction();
        
        try {
        	
        	em.persist(inmoble);
        	
		  transaction.commit();
              
	    } catch (Exception e) {
	        transaction.rollback();
	        e.printStackTrace();
	    } finally {
	       //////// em.close();
	    }
	   
          
          
          
		////inmoble = r.afegirInmoble(inmoble);
			
		getInmoblesVenedor().add(FormHBM.passarHBMtoForm(inmoble)); 
		*/
		
		setNou(true);
		
	    return "Success";
	
	}

	
	/*
	 * Construim les files de la datatable que son una llista d'objectes InmobleCaract
	 */
	public List<InmobleCaract> getInmoblesVenedorCaract()
    {

		synchronized (this) { 
		
			if (inmoblesVenedorCaract.isEmpty() || cambiDades)  // ES CAMBIA LES DADES SI CAMBIEM EL TIPUS
			{
				cambiDades = false;
				
				Inmoble_Impl r = new Inmoble_Impl();
		    	List<InmobleCaract> listDataRow = new ArrayList<InmobleCaract>();
			
		   		/* CAS : JPA
		   		 * 
		   		 */
		    	EntityManager entityManager = lookupEntityManager();
		   		Query query = entityManager.createQuery("select inmobles from Inmobles as inmobles " +
		   						"where inmobles.usuaris.nomusuari = 'EVA' and idtipus = " + idTipus);
		        
		   		// Recuperem NOMES els inmobles segons la pagina seleccionada
		   		////////SequenceRange sequenceRange = (SequenceRange) range;
		        query.setFirstResult(0);
		        query.setMaxResults(10);
		        
		        List<Inmobles> inmoblesVenedor =  query.getResultList();
		        
		    		
		   		/*
		   		 * CAS : HIBERNATE
		   		 
		    	Usuaris usuari = new Usuaris();
		    	usuari.setNomusuari("EVA");
		   		List<Inmobles> inmoblesVenedor = r.inmoblesVenedorRang(usuari, 1, 10); // llista inmobles del venedor
		 		*/
		        
				Iterator<Inmobles> inmoblesVenedorIt = inmoblesVenedor.iterator();
				while (inmoblesVenedorIt.hasNext())
				    {
						Inmobles inmoble = inmoblesVenedorIt.next();
							
						InmobleCaract valorsCaract = r.valorsCaracteristiquesInmoble(inmoble.getId());
						
						valorsCaract.setIdInmoble(inmoble.getId());
						
						listDataRow.add(valorsCaract);
				    }
		    	
		    	
				inmoblesVenedorCaract = listDataRow;
				
			}
		}
		
		return inmoblesVenedorCaract;
    	 
    }
	
	
	
	/*
	 * Llista de inmobles publicats per un venedor
	 */
	public List<InmobleForm> getInmoblesVenedor() {
		
		synchronized (this) {
           ////// if (inmoblesVenedor == null) { // CUIDADO !!!!! COMENTAR SI VOLEM REFRESCAR EL NUMERO SOLICITUDS
            	
			
				List<InmobleForm> inmoblesVenedor = new ArrayList<InmobleForm>();
    	
				Inmobles inmoble = null;
				
				facesContext = FacesContext.getCurrentInstance(); 
				UserForm userForm = (UserForm) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{userForm}", UserForm.class);
				
				Inmoble_Impl r = new Inmoble_Impl();
				
				Usuaris usuari = new Usuaris();
				usuari.setNomusuari(userForm.getNomUsuari());
				Iterator<?> it = r.inmoblesVenedor(usuari).iterator();
				
				List<Usuaris> solicitantsInmoble = new ArrayList<Usuaris>();
				
				
				// per cada inmoble del venedor
				while (it.hasNext())
				{
					
					inmoble = (Inmobles)it.next();
					
					// creem un nou inmobleForm corresponent al Inmobles
					InmobleForm inmobleForm = new InmobleForm(); 
						
					inmobleForm = FormHBM.passarHBMtoForm(inmoble); 
					
					
					
					
					
					
					// per cada inmoble calculem el numero de solicituds
					solicitantsInmoble = r.solicitantsInmoble(inmoble);
				
					
					
					
					
					inmobleForm.setNumSol(solicitantsInmoble.size());
					
					
					
					
					
					
					
					
					inmoblesVenedor.add(inmobleForm);
				}
				
				
			    setInmoblesVenedor(inmoblesVenedor);
         ///////  }
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
	
    
    
    
    
	public void setInmoblesVenedor(List<InmobleForm> inmoblesVenedor) {
		this.inmoblesVenedor = inmoblesVenedor;
	}

	
	public void setInmoblesVenedorCaract(List<InmobleCaract> inmoblesVenedorCaract) {
		this.inmoblesVenedorCaract = inmoblesVenedorCaract;
	}


	public void setInmoblesSolicitatsPerComprador(
			List<InmobleForm> inmoblesSolicitatsPerComprador) {
		this.inmoblesSolicitatsPerComprador = inmoblesSolicitatsPerComprador;
	}

	public String getKey() {
		return key;
	}

	public void setId(String key) {
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

  	
  	public Integer getIdTipus() {
		return idTipus;
	}

	public void setIdTipus(Integer idTipus) {
		this.idTipus = idTipus;
		
		facesContext = FacesContext.getCurrentInstance(); 
		FilterBeanInmobles filtreBeanInmobles = (FilterBeanInmobles) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{filterBeanInmobles}", FilterBeanInmobles.class);
		
		filtreBeanInmobles.setTipusFilter(idTipus);
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
	
  	public void setVenedor(String newVenedor) 
  	{
	    venedor = newVenedor;
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
       
                // tots els inmobles agrupats si cal !!!!!!!!!1
             
        		Inmoble_Impl r = new Inmoble_Impl();
        		
        		Usuaris usuari = new Usuaris();
        		usuari.setNomusuari(userForm.getNomUsuari());
        		
        		Iterator<?> it = r.inmoblesVenedor(usuari).iterator();
        		while (it.hasNext())
        		{
        			InventoryItem inmobleForm = new InventoryItem();
        			Inmobles inmoble = (Inmobles)it.next();
          			
        			inmobleForm.setId(inmoble.getId());
        			inmobleForm.setNom(inmoble.getNom());
        			inmobleForm.setAdreca(inmoble.getAdreca());
        		    inmobleForm.setLocalitat(inmoble.getCiutats().getId());
        		    
        		    inmobleForm.setProvincia(inmoble.getProvincies().getId());
        			
        			inmobleForm.setIdTipus(inmoble.getTipus().getId());

        			inmobleForm.setNumero(inmoble.getNumero());
        			inmobleForm.setPlanta(inmoble.getPlanta());
        			inmobleForm.setPuerta(inmoble.getPuerta());
        			inmobleForm.setMetres(inmoble.getMetres());
        			inmobleForm.setPreu(inmoble.getPreu());
        		    
        			// Objecte venedor
        			inmobleForm.setVenedor(inmoble.getUsuaris().getNomusuari());
        				
        			inmobleForm.setVisitat(inmoble.isVisitat());
        		    
        			allInventoryItems.add(inmobleForm);
        		}
 
            }
        }
        return allInventoryItems;
    }

  
    private void itemToTipusItem(InventoryItem item, InventoryFiltreItem newItem) {
        newItem.setAdreca(item.getAdreca());

        newItem.setId(item.getId());
        newItem.setImatge(item.getImatge());
        newItem.setIdTipus(item.getIdTipus());
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
		tipus.setId(1);
		
		Inmoble_Impl r = new Inmoble_Impl();
		
		List<Caracteristiques> caracteristiques = r.caractTipus(tipus);
		
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
	
		
	public Map<Long, Object> getValorsCaracts() {
		return valorsCaracts;
	}

	public void setValorsCaracts(Map<Long, Object> valorsCaracts) {
		this.valorsCaracts = valorsCaracts;
	}

	/*
	 * Reconstruim els controls dinamics en funcio del tipus d'inmoble
	 */
	public void buildControlsDinamics(HtmlPanelGroup containerControlsDinamics, Integer idTipus)
	{
		
		Map<Long, Boolean> valueMapTemp = new HashMap<Long, Boolean>();  // indica visibilitats de columnes de taula en funcio del que s'ha seleccionat

		valueMapTemp.put(73L, columnesVisibles.get(73L) != null ? columnesVisibles.get(73L) : true);
		valueMapTemp.put(74L, columnesVisibles.get(74L) != null ? columnesVisibles.get(74L) : true);
		valueMapTemp.put(75L, columnesVisibles.get(75L) != null ? columnesVisibles.get(75L) : true);
		
		containerControlsDinamics.getChildren().clear();

		Inmoble_Impl r = new Inmoble_Impl();
		
		Tipus tipus = new Tipus();
		tipus.setId(idTipus);
	
		List<Caracteristiques> c = r.caractTipus(tipus,1, false);  // seleccionem els que no son booleans i no son caract comunes
		
		
		// en funcio del tipus(varchar,select,...) del Tipus construim el formulari
		Iterator<?> iter = c.iterator();
		while (iter.hasNext())
		{
			Caracteristiques cc = (Caracteristiques) iter.next();
			
			if (cc.getTtpcontrol().getKtpcontrol().compareTo("ITXT") == 0)
			{
				// construim outputText
		       	containerControlsDinamics.getChildren().add(HtmlDinamic.buildOutputText(
					Cadenes.primeraLletraMajuscula(cc.getNom())
					,(ValueExpression) Utils.resolveExpression(cc.getNom() + " " + cc.getId())));
			        		
		   		// construim inputText
						        		
			   	HtmlInputText inputText = new HtmlInputText();    
				ValueExpression v = (ValueExpression) Utils.resolveExpression("#{inmobleForm.valorsCaracts[" + cc.getId() + "]}");
			    Utils.processProperty(inputText, v,  "value");
			        		
			   	containerControlsDinamics.getChildren().add(HtmlDinamic.buildInputText(
			   		"controlsdinamics_" + cc.getNom().toLowerCase() , 
			   		v, 
			      	"width:" + (10*cc.getTamanyControl()) + "px", 
			      	cc.getTamanyControl(), 
			      	"Campo requerido",   // requiredMessage
			      	cc.getTtpbasic().getKtpbasic().compareTo("INT") == 0 ?  "valor numérico" : "valor incorrecto",
			      	cc.getTtpbasic().getKtpbasic(),
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
			       	
			    ValueExpression v2 = (ValueExpression) Utils.resolveExpression("#{inmobleForm.columnesVisibles[" + cc.getId() + "]}");
			        		
			    containerControlsDinamics.getChildren().add(HtmlDinamic.buildCheckbox(
			       	Long.toString(cc.getId()),  // idCaracteristica
			       	v2,
			       	new ArrayList<String>(){{add("@this");add("dyn_taulaInmobles");add("dyn_taulaInmoblesVenedor");}},
			       	new ArrayList<String>(){{add("@this");}}));
			        		
			        		
			        		
			    //CAL MODIFICAR LA HASMAP QUE ENS MARCA QUINES COLUMNES DE LA DATATABLE DINAMICA S'HAURA DE VISIBILITZAR
			        		
			    if (!columnesVisibles.containsKey(cc.getId()))
			    	valueMapTemp.put(cc.getId() , false);  // inicialment la columna estara invisible
			    else
			    	valueMapTemp.put(cc.getId() , columnesVisibles.get(cc.getId()));
	        
			}   // IF			

		} // while
		
	//} // if


		columnesVisibles = valueMapTemp;
		
	}
	
 
    
    /*
     * Per actualitza el model de dades
     */
    void actualitzarDataModel(Integer idTipus)
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
            tipus.setId(idTipus);
            
            
            /* 
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder(); 
            CriteriaQuery<Inmobles> criteriaQuery = criteriaBuilder.createQuery(Inmobles.class); 
            Root<Inmobles> from = criteriaQuery.from(Inmobles.class); 
            
            -----------------
             * 
             */
            
            
            QuerysJPA qJPA= new QuerysJPA(em, Inmobles.class);
            
            qJPA.querySimple();
            
            qJPA.queryWithSingleCriteria(em, Inmobles.class, "habitacions", 2);
            
            /*
            Path<String> expression = from.get("nom");////// name, metres,....
            Expression<Integer> locator = criteriaBuilder.locate(criteriaBuilder.lower(expression), "CARL", 1);
            Predicate predicate = criteriaBuilder.gt(locator, 0);
  
           	CriteriaQuery<Inmobles> select = criteriaQuery.where(predicate); 
            TypedQuery<Inmobles> typedQuery = em.createQuery(select); 
            List<Inmobles> resultList = typedQuery.getResultList(); 
            
            -----------------*/
            
            /*Predicate predicate1 = criteriaBuilder.equal(from.get("tipus"), tipus); 
            
            
            CriteriaQuery<Inmobles> select = criteriaQuery.where(predicate1); 
            //select.orderBy(criteriaBuilder.asc(from.get("nom"))                         
            	//	,criteriaBuilder.desc(from.get("adreca"))); 
            
            TypedQuery<Inmobles> typedQuery = em.createQuery(select); 
            List<Inmobles> resultList = typedQuery.getResultList();*/ 
           
            //System.out.println("NUMERO RESULTATS : " +results.size());
           
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
           //////// em.close();
        }
        
    }
    
    
	
	
	/*
	 * Cambia la llista de caracteristiques no seleccionades en funcio del tipus d'inmoble
	 */
	private void cambiaCaracteristiquesNoSel(int idTipus)
	{
			
		facesContext = FacesContext.getCurrentInstance(); // Contexte JSF
		DragDropBeanCaract dragDropBean = (DragDropBeanCaract) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{dragDropBean}", DragDropBeanCaract.class);
		
		dragDropBean.getSource().clear();
		
		Inmoble_Impl r = new Inmoble_Impl();
		
		Tipus tipus = new Tipus();
		tipus.setId(idTipus);
		
		Iterator<Caracteristiques> iter = r.caractTipus(tipus, 0, false).iterator();
		while (iter.hasNext())
		{
			Caracteristiques caracteristicaHBM = (Caracteristiques)(iter.next());
			
			CaracteristicaForm caract = new CaracteristicaForm();
			
			caract.setId(caracteristicaHBM.getId());
			caract.setIdTipus(caracteristicaHBM.getTipus().getId());
			caract.setNom(caracteristicaHBM.getNom());
			                   
			dragDropBean.getSource().add(caracteristicaHBM);
			
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
	private HashMap mergeHash(HashMap<String, Boolean> h1 , HashMap h2)
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
	public void buildLlistaDragAndDrop(Integer idTipus)
	{

		facesContext = FacesContext.getCurrentInstance(); 
		DragDropBeanCaract dragDropBean = (DragDropBeanCaract) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{dragDropBeanCaract}", DragDropBeanCaract.class);
		
		Inmoble_Impl r = new Inmoble_Impl();
		
		Tipus tipus = new Tipus(); 
		tipus.setId(idTipus == null ? 1 : idTipus); // cuidado
		
		List<Caracteristiques> novaLlista = r.caractTipus(tipus, 0, false);  // 0 = caracteristiques booleanes
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