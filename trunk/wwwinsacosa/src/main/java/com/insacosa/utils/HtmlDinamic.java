package com.insacosa.utils;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;

//------------------------------------------------------------
//Corresponen als components Core de JSF.  exemple : <f:facet>
//------------------------------------------------------------
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;

//---------------------------------------------------------------
//Validators JSF
//---------------------------------------------------------------
import javax.faces.validator.LengthValidator;
import javax.faces.validator.DoubleRangeValidator;

//---------------------------------------------------------------
//Converters JSF
//---------------------------------------------------------------
import javax.faces.convert.Converter;
import javax.faces.convert.IntegerConverter;
import javax.faces.convert.DateTimeConverter;



//-----------------------------------------------------------------
// Corresponen als components HTML de JSF.  exemple : <h:inputText>
//-----------------------------------------------------------------
import javax.faces.component.html.HtmlColumn;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;

//---------------------------------------------------------------
//Corresponen als components Richfaces de JSF.  exemple : <rich:>
//---------------------------------------------------------------
import org.richfaces.component.UICommandLink;
import org.richfaces.component.UIDataScroller;
import org.richfaces.component.UIDataTable;
import org.richfaces.component.UIPanelMenu;
import org.richfaces.component.UISelect;
import org.richfaces.component.UIDropDownMenu;
import org.richfaces.component.UIMenuItem;
import org.richfaces.component.UIColumn;

import org.richfaces.component.UITooltip;
import org.richfaces.component.UIRegion;
import org.richfaces.component.UIPopupPanel;


import org.richfaces.component.UIRichMessage;

import com.google.appengine.api.datastore.Key;
import com.insacosa.entitats.Caracteristiques;
import com.insacosa.entitats.Inmobles;


import com.sun.faces.taglib.jsf_core.SetPropertyActionListenerImpl;

import com.insacosa.dataModels_JPA.InmobleCaract;

import org.ajax4jsf.component.behavior.AjaxBehavior;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.context.FacesContext;


import javax.faces.event.ValueChangeListener;

import com.insacosa.utils.Utils;
import com.insacosa.vo.InmobleForm;



public abstract class HtmlDinamic implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum TipusColumna {
		INT, VCHR, BOOL;
		}
	
	public enum TipusControl {
		ITXT, SELT, FILE, ICHK;
		}
	
	
	private LengthValidator validadorLongitud()
	{
		LengthValidator lv = new LengthValidator();
	lv.setMinimum(1);
	lv.setMaximum(2);
	return lv;
	}

	
	private Log log = LogFactory.getLog(HtmlDinamic.class);
	
	// Configuracio JSF 
	//////static FacesContext facesContext = FacesContext.getCurrentInstance(); 
	/////////static Application application = facesContext.getApplication();
	
	
	// Constructor
	public HtmlDinamic() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public static UIRegion region(String id)
	{
		UIRegion  newRegion = new UIRegion();
		
		return newRegion;
	}
	
	
	public static UIPopupPanel buildPopupPanel(String id)
	{
		Application application = FacesContext.getCurrentInstance().getApplication();
	      
		UIPopupPanel popupPanel = (UIPopupPanel) application.createComponent(UIPopupPanel.COMPONENT_TYPE);
		
		popupPanel.setId("popupPanel_" + id);
		popupPanel.setTransient(true);
		
		popupPanel.setAutosized(true);
		popupPanel.setModal(false);
		
		return popupPanel;
	}
	
	
	
	public static UITooltip tooltip(String id)
	{
		Application application = FacesContext.getCurrentInstance().getApplication();
	      
		UITooltip tooltip = (UITooltip) application.createComponent(UITooltip.COMPONENT_TYPE);
		
		tooltip.setId("tooltip_" + id);
		tooltip.setTransient(true);
		
		tooltip.setStyleClass("tooltip");
			
		return tooltip;
	}
	
	
	/*------------------------------------------------------------------------------
	 <rich:message for="name" ajaxRendered="true" /> 
	 ------------------------------------------------------------------------------*/
	public static UIRichMessage buildMessageRich(
			String id,
			String style,
			Boolean vtransient)
	{
		Application application = FacesContext.getCurrentInstance().getApplication();
      
		UIRichMessage richMessage = (UIRichMessage) application.createComponent(UIRichMessage.COMPONENT_TYPE);
		
		richMessage.setId("richMessage_" + id);
		richMessage.setTransient(vtransient);
		
		// Formatejar el "message" 
		richMessage.getAttributes().put("style", style);
		
		richMessage.setShowDetail(false);
		
		richMessage.setShowSummary(true);
		
		richMessage.setFor(id);
		richMessage.setAjaxRendered(true);
		
		return richMessage;
	}
	

	
/*
 * Butonera d'operadors numerics (>,= ,<,...) amb el titol del camp i l'inputText	
*/
	public static HtmlPanelGroup butoneraNumerica (
			String idColumna,
			String valueExpressionOperadors,
			String valueExpressionFiltre,
			String nomColumna,
			UIColumn column,
			String tipusColumna,
			String tipusControl,
			int minimum,  // nombre minim de digits o chars
			int maximum,  // nombre maxim de digits o chars
			ArrayList<String> renderList,
			ArrayList<String> executeList)
	{

		HtmlPanelGroup panelGroup1 = new HtmlPanelGroup();
		panelGroup1.setTransient(true);
		
		HtmlPanelGrid panelGrid1 = new HtmlPanelGrid();
		panelGrid1.setTransient(true);
		
		panelGrid1.setColumns(2);
		panelGrid1.setStyle("style='vertical-align: top'");
		
		HtmlPanelGroup panelGroup2 = new HtmlPanelGroup();
		panelGroup2.setTransient(true);
		
		HtmlPanelGrid panelGrid2 = new HtmlPanelGrid();
		panelGrid2.setTransient(true);
		
		panelGrid2.setColumns(1);
		
		/** BUTO GE **/
			UICommandLink clGE = buildCommandLinkRich(
					"GE_" + nomColumna,
					null,
					renderList ,
					executeList);
			
			ValueExpression vGE = (ValueExpression) Utils.resolveExpression("/images/ElementPlus.jpg");
			HtmlGraphicImage giGE = buildGraphicImage(
					"GE_" + nomColumna, 
					vGE, 
					"Mayor que");
			
			clGE.getChildren().add(giGE);
			
			// setPropertyBean (ge = greater or equal,...)
			clGE.addActionListener(setPropertyActionListener(
						valueExpressionOperadors, 
						"ge", 
						String.class));
			
			panelGrid2.getChildren().add(clGE);
		/*************************/
		
		
		/** BUTO LE **/
			UICommandLink clLE = buildCommandLinkRich(
							"LE_" + nomColumna,
							null,
							renderList ,
							executeList);
			
			ValueExpression vLE = (ValueExpression) Utils.resolveExpression("/images/ElementMinus.jpg");
			HtmlGraphicImage giLE = buildGraphicImage(
							"LE_" + nomColumna, 
							vLE, 
							"Menor que");
			
			clLE.getChildren().add(giLE);
			
			// setPropertyBean (ge = greater or equal,...)
			clLE.addActionListener(setPropertyActionListener(
							valueExpressionOperadors, 
							"le", 
							String.class));
			
			panelGrid2.getChildren().add(clLE);
		/*************************/
		
		panelGroup2.getChildren().add(panelGrid2);

		HtmlPanelGroup panelGroup3 = new HtmlPanelGroup();
		panelGroup3.setTransient(true);
		
		HtmlPanelGrid panelGrid3 = new HtmlPanelGrid();
		panelGrid3.setTransient(true);
		panelGrid3.setColumns(1);
		

		ValueExpression vFiltre =  (ValueExpression) Utils.resolveExpression(valueExpressionFiltre); 
		
		HtmlInputText it = buildInputText(
				"butoneraNumerica_it_" + idColumna , 
				vFiltre, 
				"width:50px", 
				20, 
				"", // requiredMessage
				"",  // messageConverter
				tipusColumna, 
				false,
				true, // transient !!!!!!!!
				minimum,
				maximum); 				
		afegirAjax(
				it, 
				"blur", 
				renderList, 
				executeList);
			

		panelGrid3.getChildren().add(it);
		
		panelGroup3.getChildren().add(panelGrid3);
		
		panelGrid1.getChildren().add(panelGroup2);
		panelGrid1.getChildren().add(panelGroup3);
		
		panelGroup1.getChildren().add(panelGrid1);
		
		return panelGroup1;
}


	
	
	
	/*------------------------------------------------------------------------------	
	<h:graphicImage value="/images/24-book-blue-open.png" alt="Editar domicili" style="border:0"/>                     
	-------------------------------------------------------------------------------- */
	public static HtmlGraphicImage buildGraphicImage(
			String displayValue,
			ValueExpression value, 
			String alt
			)
	{
		
		FacesContext context = FacesContext.getCurrentInstance(); 
		Application application = context.getApplication();

		HtmlGraphicImage graphicImage = (HtmlGraphicImage) application.createComponent(HtmlGraphicImage.COMPONENT_TYPE);
		
		graphicImage.setId("graphicImage_" + displayValue);
		graphicImage.setTransient(true);
		graphicImage.setStyle("border:0");
		
		if (value.isLiteralText())
			graphicImage.setValue(value.getExpressionString());
		else
			graphicImage.setValueExpression("value", value);
		
		return graphicImage;
		
	}
	
	
	/*------------------------------------------------------------------------------	
	<h:selectOneMenu value="#{inmobleForm.valorActualProv}" id="lprovincies"
		valueChangeListener="#{prov.valorCambiat}">
		<f:selectItems value="#{prov.provincies}"/>
		<a4j:ajax event="valueChange" render="lciutats,lciutatsFiltre,lprovinciesFiltre,taulaInmobles" 
		execute="@this"/>
	</h:selectOneMenu>
	-------------------------------------------------------------------------------- */
	public static HtmlSelectOneMenu buildSelectOneMenu(
			String displayValue ,
			String bindingValue ,
			String classesValueChangeList ,  // nom d'una classe ValueChangeListener
			String bindingValueSelItems,
			ArrayList<String> renderList,
			ArrayList<String> executeList)
	{
				
		FacesContext context = FacesContext.getCurrentInstance(); 
		Application application = context.getApplication();
		
		HtmlSelectOneMenu selectOneMenu = (HtmlSelectOneMenu) application.createComponent(HtmlSelectOneMenu.COMPONENT_TYPE);
		
		selectOneMenu.setId("selectOneMenu_" + displayValue);  
		selectOneMenu.setTransient(true);
		
		selectOneMenu.setValueExpression("value", (ValueExpression) Utils.resolveExpression(bindingValue));
		
		// NOTA : cal que hi hagi una classe ValueChangeListener a vo.
		try {
			selectOneMenu.addValueChangeListener( (ValueChangeListener) Class.forName("vo." + classesValueChangeList).newInstance());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		afegirAjax(
				selectOneMenu, 
				"valueChange", 
				renderList, 
				executeList);
		
		selectOneMenu.getChildren().add(buildSelectItems(
				displayValue, 
				bindingValueSelItems));
		
			
		return selectOneMenu;
		
	}
	

	/*------------------------------------------------------------------------------	
	 <h:selectBooleanCheckbox value="#{inmobleForm.columnesVisibles['chk_puerta']}" >
		<a4j:ajax event="click"  render="@this,dyn_taulaInmobles" execute="@this"/>
	</h:selectBooleanCheckbox>
	-------------------------------------------------------------------------------- */
	public final static HtmlSelectBooleanCheckbox buildCheckbox(
			String displayValue ,
			ValueExpression bindingValue ,
			ArrayList<String> renderList,
			ArrayList<String> executeList)
	{
				
		FacesContext context = FacesContext.getCurrentInstance(); 
		Application application = context.getApplication();
		
		final HtmlSelectBooleanCheckbox checkbox = (HtmlSelectBooleanCheckbox) application.createComponent(HtmlSelectBooleanCheckbox.COMPONENT_TYPE);

		checkbox.setId("checkbox_" + displayValue);  
		checkbox.setTransient(true);
		
		
		//////////////checkbox.setOnclick("alert('hola')");  funciona
 
		checkbox.setValueExpression("value", bindingValue);
 
		/*afegirAjax(
				checkbox, 
				"click", 
				renderList, 
				executeList);
        */
        
		return checkbox;

	}

	
	
    

	
	
	// ******************************************************
	// ******************************************************
	// Controls de components Core de JSF exemple : <f:facet>
	// ******************************************************
	// ******************************************************
	
	/*-------------------------------------------------------
	<f:selectItems value="#{prov.provincies}"/>
	   ------------------------------------------------------ */
	public static UISelectItems buildSelectItems(
			String displayValue,
			String bindingValue
			)
		{
	    	FacesContext facesContext = FacesContext.getCurrentInstance(); 
			Application application = facesContext.getApplication();
			
			UISelectItems selectItems = (UISelectItems) application.createComponent(UISelectItems.COMPONENT_TYPE);
			
			selectItems.setId("selectItems_" + displayValue);  
			selectItems.setTransient(true);
			
			selectItems.setValueExpression("value", (ValueExpression) Utils.resolveExpression(bindingValue));  
			
			return selectItems;
			
		}
	
	
	
    
	/*------------------------------------------------------------------------------
    <f:setPropertyActionListener target="#{bean.sortProperty}" value="#{property}" />
    ------------------------------------------------------------------------------ */
    public static javax.faces.event.ActionListener setPropertyActionListener(String target, String value, Class classe)
    {
 
    	FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Application application = facesContext.getApplication();
		
	    ValueExpression v = application.getExpressionFactory().createValueExpression(facesContext.getELContext(), 
	    		value,classe);
	    ValueExpression t = application.getExpressionFactory().createValueExpression(facesContext.getELContext(), 
	    		target,classe);
	    
	    SetPropertyActionListenerImpl listener = new SetPropertyActionListenerImpl(t, v);
	 
	    return listener;

    }
    
    
    
	/*------------------------------------------------------------------------------
    <f:selectItem .......   />
    ------------------------------------------------------------------------------ */
    public static UISelectItem buildSelectItem(
    		String displayValue,
    		String bindingValue)
    {
    	Application application = FacesContext.getCurrentInstance().getApplication();
		
    	UISelectItem selectItem = (UISelectItem)application.createComponent(UISelectItem.COMPONENT_TYPE);
    	
    	selectItem.setId("selectItem_" + displayValue);  
    	selectItem.setTransient(true);
		
    	return selectItem;
    	
	}
    
    
    
		
	
	
	// ************************************************************
	// ************************************************************
	// Controls de components HTML de JSF.  exemple : <h:inputText>
	// ************************************************************
	// ************************************************************
	
	/*------------------------------------------------------------------------------
	 <h:commandLink>
	 ------------------------------------------------------------------------------*/
	public static HtmlCommandLink buildCommandLink(
			String displayValue, 
			String bindingValue, 
			String idPrefix, 
			String id)
	{
		Application application = FacesContext.getCurrentInstance().getApplication();
		
		HtmlCommandLink commandLink = (HtmlCommandLink) application.createComponent(HtmlCommandLink.COMPONENT_TYPE);
		
		commandLink.setId("commandLink_" + displayValue);  
		commandLink.setTransient(true);
		
		commandLink.setActionExpression(Utils.createAction(bindingValue, MethodExpression.class));
		
		return commandLink;
		
	}
	
	/*------------------------------------------------------------------------------
	 <h:commandButton>
	 ------------------------------------------------------------------------------*/	 
	public static HtmlCommandButton buildCommandButton(
			String displayValue, 
			MethodExpression bindingValue, 
			String idPrefix, 
			String id)
	{
		Application application = FacesContext.getCurrentInstance().getApplication();
		
		HtmlCommandButton button= (HtmlCommandButton)application.createComponent(HtmlCommandButton.COMPONENT_TYPE);
		
		button.setId("commandButton_" + displayValue +"_" + id);
		button.setTitle(displayValue);
		button.setValue(displayValue);
		button.setStyleClass("commandExButton");
		
		button.setActionExpression(Utils.createAction(bindingValue.getExpressionString(), String.class)); 
		
		return button;
		 
	}
	
	
	
   
	/*------------------------------------------------------------------------------
	<h:inputText value="#{inmobleForm.numero}"  style="width:10px"                   
				requiredMessage="es requereix" id="numeroInmoble"                   
				converter="javax.faces.Integer" 
		   		converterMessage="Hauria de ser un valor numèric"                    
				validatorMessage="Hauria de ser un nom valid" label="numero">                     
		  	</h:inputText>
	------------------------------------------------------------------------------*/	
	public static HtmlInputText buildInputText(
			String displayValue, 
			ValueExpression bindingValue ,
			String style, 
			int tamanyControl, 
			String requiredMessage,
			String messageConverter,
			String tipusColumna,
			Boolean obligatori,
			Boolean vtransient, // transient !!!!!!!!
			int minimum,  // si INT , numero digits
			int maximum)  // si INT , numero digits
	{
		
		
		FacesContext context = FacesContext.getCurrentInstance(); 
		Application application = context.getApplication();
		
		HtmlInputText inputText = (HtmlInputText) application.createComponent(HtmlInputText.COMPONENT_TYPE);
		
		inputText.setId("inputText_" + displayValue);  
		inputText.setTransient(vtransient);

		inputText.setStyle(style);
		
		inputText.setValueExpression("value", bindingValue);  
		
		
		// VALIDADOR 
		final LengthValidator validator = (LengthValidator) application.createValidator(LengthValidator.VALIDATOR_ID);

		validator.setMinimum(minimum);
 	 	validator.setMaximum(maximum);
		
		// camp obligatori
		if (obligatori)
		{
			inputText.setRequired(obligatori);
			inputText.setRequiredMessage(requiredMessage);
		}
		
		// creem el convertidor en funcio del tipus de inputText
		// SI ES INTEGER HEM D'AFEGIR ELS BUTONS DELS OPERADORS (<=, =,...)

		if (tipusColumna != null && tipusColumna.compareToIgnoreCase("INT") == 0)
		{
			Converter conv = new IntegerConverter();
			
			inputText.setConverter(conv);
			inputText.setConverterMessage(messageConverter);
			
			inputText.setValidatorMessage("Entre " + minimum + " y " + maximum + " digitos");
						
		}
		else if (tipusColumna != null && tipusColumna.compareToIgnoreCase("VCHR") == 0)
		{
			inputText.setValidatorMessage("Entre " + minimum + " y " + maximum + " caracteres");
			
		}
		
 	 	
		inputText.addValidator(validator);


		return inputText;

	}
	
	/*------------------------------------------------------------------------------
	  <h:outputText value="Nom: " />
	------------------------------------------------------------------------------*/
	public  static HtmlOutputText buildOutputText(
			String idOutputText,
			ValueExpression value)
	{
		FacesContext context = FacesContext.getCurrentInstance(); 
		Application application = context.getApplication();

		HtmlOutputText outputText = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
		
		outputText.setId("outputText_"+ idOutputText);  
		outputText.setTransient(true);
		
		
		if (value.isLiteralText())
			outputText.setValue(value.getExpressionString());
		else
			outputText.setValueExpression("value", value);
		
		return outputText;
		
	}
	

	/*------------------------------------------------------------------------------
	   <h:outputLabel value="Nom: " />
	------------------------------------------------------------------------------*/
	public static HtmlOutputLabel buildOutputLabel(String displayValue)
	{
	    HtmlOutputLabel label = new HtmlOutputLabel();
	
	    label.setValue(displayValue);
	    
	    return label;
	    
	}
	
	
	/*		  
	 * Butonera booleana 
	 */
    public static UICommandLink butoneraBooleana(
    		String idColumna)
    {
    	
    	UICommandLink cl = buildCommandLinkRich(
    			"butoneraBooleana_" + idColumna ,
    			null,
    			new ArrayList<String>(){{add("@this");}} ,
    			new ArrayList<String>(){{add("@this");}});
		
		ValueExpression vTrue = (ValueExpression) Utils.resolveExpression("/images/16-security-lock.png");
		ValueExpression vFalse = (ValueExpression) Utils.resolveExpression("/images/16-em-cross.png");
		ValueExpression vNoTrueFalse = (ValueExpression) Utils.resolveExpression("/images/chkOff.gif");
		HtmlGraphicImage giTrue = buildGraphicImage("cl_gr_true" + idColumna, vTrue, "True");
		HtmlGraphicImage giFalse = buildGraphicImage("cl_gr_false" + idColumna, vFalse, "False");
		HtmlGraphicImage giNoTrueFalse = buildGraphicImage("cl_gr_notruefalse" + idColumna, vNoTrueFalse, "No llenado");
		

		giTrue.setValueExpression(
				"rendered", 
				(ValueExpression) Utils.resolveExpression("#{  item.caractInmobles[" + idColumna + "] != null ?  item.caractInmobles[" + idColumna + "] : 'false' }"));
		
		giFalse.setValueExpression(
				"rendered", 
				(ValueExpression) Utils.resolveExpression("#{not (item.caractInmobles[" + idColumna + "] != null ?  item.caractInmobles[" + idColumna + "] : 'false') }"));
		
		giNoTrueFalse.setValueExpression(
				"rendered", 
				(ValueExpression) Utils.resolveExpression("#{item.caractInmobles[" + idColumna + "] == null}"));


		cl.addActionListener(setPropertyActionListener(
								"#{item.caractInmobles[" + idColumna + "]}", 
								"#{not item.caractInmobles[" + idColumna + "] }", Boolean.class));
		
		cl.getChildren().add(giTrue);
		cl.getChildren().add(giFalse);
	
    	return cl;
    	
    	
    }
	
	
		
	
	
	/*
	 * Taula dels inmobles venedors sense Sorting/Filtering
	 */
	public static UIDataTable buildDatatable(
			final String componentID,
			String valueExpression,
			List<Caracteristiques> lc,
			Map<Long,Boolean> columnesVisibles, 
			int numRows,
			String rowClasses,
			String styleClass,
			String style
			)
	{
		
		Application application = FacesContext.getCurrentInstance().getApplication();
	      
		UIDataTable htmlDataTable = (UIDataTable) application.createComponent(UIDataTable.COMPONENT_TYPE);

	    htmlDataTable.setId(componentID);
		htmlDataTable.setVar("item");
		htmlDataTable.setRows(numRows);
		htmlDataTable.setRowClasses("odd-row,even-row");  // MOLT IMPORTANT SI VOLEM APLICAR EL PLUGIN TABLESORTER
													 // SINO , odd-row,even-row
		htmlDataTable.setStyleClass("stable");  // DE JQUERY !!!!!!!!!!!!!!!!!!!!
													 // stable
		
		htmlDataTable.setIterationStatusVar("it");
		htmlDataTable.setRowKeyVar("#{item}");
		/////////////htmlDataTable.setStyle(style);
		
		
		
		//htmlDataTable.setOnrowmouseover("function(){jQuery(this).children('#solicitantsInmoble').fadeIn('fast')};");
		//htmlDataTable.setOnrowmouseout("this.style.backgroundColor='#{a4jSkin.rowBackgroundColor}';");
		 
		htmlDataTable.setTransient(true); 
		
		htmlDataTable.setValueExpression("value", (ValueExpression) Utils.resolveExpression(valueExpression));
   	
		
	    // afegim la referencia/id de l'inmoble
	    htmlDataTable.getChildren().add(buildColumnID(1L ,
	    		"#{item.idInmoble}",
    			"ID", 
    			"VCHR", 
    			"ITXT",
    			false,
    			"color:black;background:gray;"));
		
	    // Columnes datatable segons la Hashtable columnesVisibles de visibilitats
	    Set<Long> idsColumnes = columnesVisibles.keySet();
	    
	    
	    Iterator<Caracteristiques> caracteristiquesIter = lc.iterator(); 
	    while (caracteristiquesIter.hasNext())
	    {
	    	Caracteristiques caracteristica = (Caracteristiques) caracteristiquesIter.next();
	    	
	    	UIColumn columna = buildColumn(
	    			caracteristica.getCaracteristicaKey() ,
	    			"#{item.caractInmobles[" + caracteristica.getCaracteristicaKey() + "]}",
	    			"#{inmobleForm.columnesVisibles[" + caracteristica.getNom() + "]}",
	    			caracteristica.getNom(), 
	    			caracteristica.getTtpbasic().getBdtype(), 
	    			caracteristica.getTtpcontrol().getTipus(),
	    			0,  // minim nombre digits o chars
	    			caracteristica.getTamanyControl() == null ? 0 : caracteristica.getTamanyControl().intValue(),  // maxim nombre digits o chars
	    			caracteristica.isModificable(),
	    			new ArrayList<String>(){{add(componentID);}},
	    			new ArrayList<String>(){{add("@this");}});  
	    	
	    	htmlDataTable.getChildren().add(columna);
			
	    }
	    
	
	    /*-----------------------------------------------*/
	    /* afegim (si cal) la columna amb butons/links   */
	    /*-----------------------------------------------*/
	    htmlDataTable.getChildren().add(buildColumnAmbLink(1L, 
	    		"solicitud",
	    		"headerTextValue",
	    		"#{inmobleForm.solicitarInmobles}",
	    		1,
	    		true,
	    		null, 
	    		"/images/bSelectNR.gif", 
	    		"Solicitar Inmueble",
	    		new ArrayList<String>(){{add("dyn_taulaInmobles");add(componentID);}},
	    		new ArrayList<String>(){{add("@this");}}));
	    
	    htmlDataTable.getChildren().add(buildColumnAmbLink(2L, 
	    		"eliminar","headerTextValue",
	    		"#{inmobleForm.esborrarInmobles}",
	    		1,
	    		true,
	    		null, 
	    		"/images/16-em-cross.png", 
	    		"Eliminar Inmueble",
	    		new ArrayList<String>(){{add("dyn_taulaInmobles");add(componentID);}},
	    		new ArrayList<String>(){{add("@this");}}));
	    
	    htmlDataTable.getChildren().add(buildColumnAmbLink(3L, 
	    		"modificar","headerTextValue",
	    		"#{inmobleForm.modificarInmobles}",
	    		1,
	    		true,
	    		null, 
	    		"/images/update.gif", 
	    		"Modificar Inmueble",
	    		new ArrayList<String>(){{add("dyn_taulaInmobles");add(componentID);}},
	    		new ArrayList<String>(){{add("@form");}}));
	   
	  	
        htmlDataTable.getFacets().put("footer", buildDatascroller("dyn_datascroller"));
		
	    return htmlDataTable;
	
	}
	
	
	
	// *********************************************************************************
	// *********************************************************************************
	// Controls de components Richfaces (implementacio JSF).  exemple : <rich:dataTable>
	// *********************************************************************************
	// *********************************************************************************
	
	 /*
     * <rich:dataTable value="#{inmobleForm.inmoblesBuscats}" var="item"           
   			iterationStatusVar="it" id="taulaInmobles" rows="10" 
   			rowClasses="odd-row, even-row" styleClass="stable">
     */
	public static UIDataTable buildDatatableFS(
			final String componentID,
			String valueExpression,
			List<Caracteristiques> lc,
			Map<Long,Boolean> columnesVisibles, 
			int numRows,
			String rowClasses,
			String styleClass,
			String style
			)
	{
		
		Application application = FacesContext.getCurrentInstance().getApplication();
	      
		UIDataTable htmlDataTable = (UIDataTable) application.createComponent(UIDataTable.COMPONENT_TYPE);

	    htmlDataTable.setId(componentID);
		htmlDataTable.setVar("item");
		htmlDataTable.setRows(numRows);
		htmlDataTable.setRowClasses(rowClasses);
		htmlDataTable.setStyleClass(styleClass);
		htmlDataTable.setIterationStatusVar("it");
		htmlDataTable.setRowKeyVar("#{item}");
		//htmlDataTable.setStyle(style);
		 
		htmlDataTable.setTransient(true); 
		
		htmlDataTable.setValueExpression("value", (ValueExpression) Utils.resolveExpression(valueExpression));
   
		
		 // afegim la referencia/id de l'inmoble
	    htmlDataTable.getChildren().add(buildColumnID(
	    		1L ,
	    		"#{item.idInmoble}",
    			"ID", 
    			"VCHR", 
    			"ITXT",
    			false,
    			"color:red;"));
		
	    // Columnes datatable segons la Hashtable columnesVisibles de visibilitats
	    Set<Long> idsColumnes = columnesVisibles.keySet();
	      
	    Iterator<Caracteristiques> caracteristiquesIter = lc.iterator(); 
	    while (caracteristiquesIter.hasNext())
	    {
	    	Caracteristiques caracteristica = (Caracteristiques) caracteristiquesIter.next();
	    	
	    	UIColumn columna = buildColumnSortFilter(
	    			caracteristica.getCaracteristicaKey() ,
	    			"#{item.caractInmobles[" + caracteristica.getCaracteristicaKey() + "]}",
	    			"#{inmobleForm.columnesVisibles[" + caracteristica.getNom() + "]}",
	    			caracteristica.getNom(), "", 
	    			caracteristica.getTtpbasic().getBdtype(), 
	    			caracteristica.getTtpcontrol().getTipus(),
	    			0,
	    			caracteristica.getTamanyControl() == null ? 0 : caracteristica.getTamanyControl().intValue());  
	    	
	    	
	    	System.out.println("ID/Caract : " + caracteristica.getCaracteristicaKey() + "/" + caracteristica.getNom() + "\n");
	    	System.out.println("Tipus BD : " + caracteristica.getTtpbasic().getKey());
	    	System.out.println("Tipus Control : " + caracteristica.getTtpcontrol().getKey());
	    	
	    	htmlDataTable.getChildren().add(columna);
			
	    }
	    
	    /*---------------------------------------------*/
	    /* afegim (si cal) columnes amb butons/links   */
	    /*---------------------------------------------*/
	    htmlDataTable.getChildren().add(buildColumnAmbLink(
	    		1L, 
	    		"solicitud",
	    		"headerTextValue",
	    		"#{inmobleForm.solicitarInmobles}",
	    		1,
	    		true,
	    		null, 
	    		"/images/bSelectNR.gif", 
	    		"Solicitar Inmueble",
	    		new ArrayList<String>(){{add(componentID);}},
	    		new ArrayList<String>(){{add("@this");}}));
	    

		
        htmlDataTable.getFacets().put("footer", buildDatascroller("dyn_datascrollerFS"));
		
	    
	    return htmlDataTable;
	
	}
	

	

	
	/* ---------------------------------------------------------------------------------
	 * Afegim comportament ajax a un component
	 * <a4j:ajax event="blur"  render="taulaInmobles" execute="@this" />
	------------------------------------------------------------------------------------*/
	private static void afegirAjax(UIComponent control, String event, ArrayList<String> renderList, ArrayList<String> execute)
	{
		Application application = FacesContext.getCurrentInstance().getApplication();
		
	    AjaxBehavior ajaxBehavior = (AjaxBehavior) application.createBehavior(AjaxBehavior.BEHAVIOR_ID);
	    
	    ajaxBehavior.setExecute(execute);
	    
	    ajaxBehavior.setRender(renderList);
	     
	    ((UIComponentBase) control).addClientBehavior(event, ajaxBehavior);
	}
 
	


	
	
	/*------------------------------------------------------------------------------
	 crea una columna amb un link i un bean associat a ell
		<a4j:commandLink styleClass="no-decor" action="#{inmobleForm.solicitarInmobles}" execute="@this">                     
			<h:graphicImage value="/images/24-book-blue-open.png" alt="Solicitar Inmueble" style="border:0"/>
			<f:setPropertyActionListener target="#{inmobleForm}" value="#{item}" /> 
			<f:setPropertyActionListener target="#{inmobleForm.currentInmobleIndex}" value="#{it.index}" /> 
		</a4j:commandLink>
	 ------------------------------------------------------------------------------ */
	private static UIColumn buildColumnAmbLink(
			long componentID,
			String displayValue,
			String headerTextValue, 
			String action, 
			int type, 
			boolean immediate, 
			UIComponent parent,
			String imatge,
			String toolTip,
			ArrayList<String> renderList,
			ArrayList<String> executeList)
	{
		
		Application application = FacesContext.getCurrentInstance().getApplication();
		
		UIColumn column = (UIColumn) application.createComponent(UIColumn.COMPONENT_TYPE);
	  
		column.setId("columnAmbLink_" + displayValue);  
		column.setTransient(true);
		
	   	UICommandLink clSolInmoble = buildCommandLinkRich(
	   							"clr_" + displayValue ,
	   							null,
	   							renderList ,  
	   							executeList);
	   	
	   	clSolInmoble.setActionExpression(Utils.createAction(action, String.class));  
		
	   	// ValueExpression "value" de la imatge
		ValueExpression v = (ValueExpression) Utils.resolveExpression(imatge);
		HtmlGraphicImage gisolInmoble = buildGraphicImage(
								"clr_gr_" + displayValue, 
								v, 
								toolTip);
		
		clSolInmoble.getChildren().add(gisolInmoble);
		
		// setPropertyBean inmoble seleccionat
		clSolInmoble.addActionListener(setPropertyActionListener(
								"#{inmobleForm.currentInmobleIndex}", 
								"#{item.idInmoble}", 
								Integer.class));
		
		
		clSolInmoble.addActionListener(setPropertyActionListener(
				"#{inmobleForm.currentInmobleCaract}", 
				"#{item}", 
				InmobleCaract.class));

		

		column.getChildren().add(clSolInmoble);
		
	  
	  return column;
	}

	
	/*
	 * Columna de la PK 
	 */
	public static UIColumn buildColumnID(
			long idColumna,
			String valueExpression,
			String nomColumna,
			String tipusColumna,
			String tipusControl,
			Boolean modificable,
			String style)
	{
		
		Application application = FacesContext.getCurrentInstance().getApplication();
		
		UIColumn column = (UIColumn) application.createComponent(UIColumn.COMPONENT_TYPE);
		
		column.setId("columnID_" + idColumna);  
		column.setTransient(true);
		
		HtmlOutputText header = new HtmlOutputText();
	    header.setValue(nomColumna + " " + idColumna);
	    header.setTransient(true);
	    
	    column.setHeader(header);
	    column.setStyle(style);
   
    	HtmlOutputText ot = buildOutputText(
    			"columnID_ot_" + nomColumna , 
    			(ValueExpression) Utils.resolveExpression(valueExpression));
	    	
    	column.getChildren().add(ot);
		
		return column;
	}
	
	
	
	/*-----------------------------------------------------------------
	 * <rich:column> Columna Richfaces SENSE suport Filtering/Sorting
	 ------------------------------------------------------------------ */
	public static UIColumn buildColumn(
			String idColumna,
			String valueExpression,
			String valueExpressionRendered,  // en principi esta relacionat amb la "inmobleForm.columnesVisibles" 
			String nomColumna,
			String tipusColumna,
			String tipusControl,
			int minimum,
			int maximum,
			Boolean modificable,
			ArrayList<String> renderList,
			ArrayList<String> executeList)
	{
		Application application = FacesContext.getCurrentInstance().getApplication();
			
		UIColumn column = (UIColumn) application.createComponent(UIColumn.COMPONENT_TYPE);
		
		column.setId("column_" + idColumna);  
		column.setTransient(true);
		
		HtmlOutputText header = new HtmlOutputText();
	    header.setValue(nomColumna + " " + idColumna);
	    header.setTransient(true);
	    
	    column.setHeader(header);

	    ///////////column.setValueExpression("rendered", (ValueExpression) Utils.resolveExpression(valueExpressionRendered));  
		
	    
	    // EN FUNCIO DE SI ES EDITABLE O NO inputText/outputText
	
	    if (modificable)
	    {
	    
	    	if (tipusColumna.compareTo("BOOL") == 0)
	    	{
	    	
	    		UICommandLink cl = butoneraBooleana(idColumna);
	    		
	    		column.getChildren().add(cl);
	    	
	    	}
	    	else
	    	{
			    ValueExpression v4 =  (ValueExpression) Utils.resolveExpression(valueExpression); 
				
			    HtmlInputText it = buildInputText("column_it_" + idColumna , 
			    		v4, 
			    		"width:50px", 
			    		20, 
			    		"",  
			    		tipusColumna.compareTo("INT") == 0 ? "valor numérico" : "valor incorrecto",
			    		tipusColumna, 
			    		false,
			    		false,   // transient !!!!!!
			    		minimum,
			    		maximum);
						
			    
				column.getChildren().add(it);
				
				
				UIPopupPanel popupPanel = buildPopupPanel("inputText_column_it_" + idColumna);
				
				// missatge error corresponent al inputtext
				UIRichMessage richMessage = buildMessageRich(
						"inputText_column_it_" + idColumna,
						"color:red;font-weight:bold",
						true);
				
				////popupPanel.getChildren().add(richMessage);
				
				column.getChildren().add(richMessage);
				
				
	    	}
		
	    }
	    else
	    {
	    
	    	HtmlOutputText ot = buildOutputText("column_ot_" + nomColumna , 
	    			(ValueExpression) Utils.resolveExpression(valueExpression));
	    	
	    	column.getChildren().add(ot);
	    }
	
		
		
		return column;
	}
	
	
	
	
	/*-----------------------------------------------------------------
	 * Si la datatable suporta filtering/ordering
	 construim els diferents elements
	 ------------------------------------------------------------------ */
	public static UIColumn afegirSortingFilteringColumn(
			String idColumna,
			String nomColumna,
			UIColumn column,
			String tipusColumna,
			String tipusControl,
			int minimum,
			int maximum)
	{
		
		// 1.-construim els diferents ValueExpression de la columna
		// ---------------------------------------------------------
	    // IMPORTANT ! XAPUSSA MIRAR JPADataModel.java
	    column.setValueExpression("sortBy", (ValueExpression) Utils.resolveExpression("#{" + nomColumna +"}")) ; 
		
		column.setValueExpression("sortOrder", (ValueExpression) Utils.resolveExpression("#{inmobleForm.sortOrders['"+ nomColumna + "']}"));  
		
		
		
		// IMPORTANT !!!!!!!!! XAPUSSA MIRAR JPADataModel.java
		//----------------------------------------------------------
		//----------------------------------------------------------

		column.setValueExpression("filterExpression", (ValueExpression) Utils.resolveExpression("#{" + nomColumna.toLowerCase() + "}"));  
	
		// XAPUSSA PER DISCRIMINAR LES PROPIETATS AMB "." (COLECCIO D'ENTITATS HBM)
		column.setValueExpression("filterValue", (ValueExpression) Utils.resolveExpression("#{inmobleForm.filterValues[" + idColumna + "]}"));  
		
		
		// 2.-construim el <h:panelGroup> -- Facet header
		// ----------------------------------------------
		HtmlPanelGroup panelGroup = new HtmlPanelGroup();
		panelGroup.setTransient(true);
	
		HtmlOutputText otPanelGroup = buildOutputText(
							"sfc_" + idColumna.toString(), 
							(ValueExpression) Utils.resolveExpression(nomColumna.toString()));
		
		panelGroup.getChildren().add(otPanelGroup);
		
		
		

		// 1- en funcio del tipus de control UI (ICHK,ITXT,...) de la caracterisitca
		// cal construir un control UI o una altre
	
	    TipusControl enumControl = TipusControl.valueOf(tipusControl);
	    
		switch (enumControl) {
			
			case ITXT:  // ITXT
				
				ValueExpression v4;
				HtmlInputText itPanelGroup;
			    
				// 1- en funcio del tipus de COLUMNA (VARCHAR,INTEGER,...) cal construir un FilterField o una altre
				// amb operador numerics, o fins i tot de cadenes
				// aixo a partir de la taula caracteristiques
					
				    TipusColumna enumColumna = TipusColumna.valueOf(tipusColumna);
				    
				    switch (enumColumna) {
					
						// SI ES INTEGER HEM D'AFEGIR ELS BUTONS DELS OPERADORS (<=, =,...)	
						case INT:  // TIPUS COLUMNA BD INTEGER
							
							
							HtmlPanelGroup butoneraNum = butoneraNumerica (
									idColumna,
									"#{inmobleForm.columnesOperacions[" + idColumna + "]}",
									"#{inmobleForm.filterValues[" + idColumna + "]}",
									nomColumna,
									null,
									tipusColumna,
									tipusControl,
									minimum,
									maximum,
									new ArrayList<String>(){{add("dyn_taulaInmobles");}},
									new ArrayList<String>(){{add("@this");}});
							
							panelGroup.getChildren().add(butoneraNum);	
							
							// NOTA : EN EL MOMENT DE CREAR UNA NOVA COLUMNA DE TIPUS NUMERIC (AMB OPERADORS) 
							//INICIALITZEM L'OPERADOR A per exemple "ge"
							InmobleForm inmobleForm = (InmobleForm)Utils.accessBeanFromFacesContext("inmobleForm", FacesContext.getCurrentInstance());
							inmobleForm.getColumnesOperacions().put(idColumna, "ge");
							
				  		
							
							break;
							
						case VCHR:  // TIPUS COLUMNA BD VARCHAR
				    
							v4 =  (ValueExpression) Utils.resolveExpression("#{inmobleForm.filterValues[" + idColumna + "]}"); 
							
							itPanelGroup = buildInputText(
									"inPnlGroup_" + idColumna , 
									v4, 
									"width:50px", 
									20, 
									"",  // requiredMessage 
									"",  // messageConverter
									tipusColumna, 
									false,
									false,   // transient !!!!!!
									minimum,
									maximum); 
									
							afegirAjax(
									itPanelGroup, 
									"blur", 
									new ArrayList<String>(){{add("pnlGr_inmobles");}}, 
									new ArrayList<String>(){{add("@this");}});
								
						    panelGroup.getChildren().add(itPanelGroup);
							
							break;
				    }
			    
					
				break;
				
			case SELT:  // COMBO
				
				// HEM DE SABER QUIN COMBO ESTEM CONSTRUINT I PASSAR-LI EL VALUECHANGELISTENER
				// CORRESPONENT
				
				HtmlSelectOneMenu selectOneMenu = buildSelectOneMenu(
						nomColumna.toLowerCase() , 
						"#{" + nomColumna.toLowerCase() + ".valorActual}",
						Cadenes.primeraLletraMajuscula(nomColumna.toLowerCase()) + "Form" , 
						"#{" + nomColumna.toLowerCase() + "." + nomColumna.toLowerCase() + "}", 
						new ArrayList<String>(){{add("pnlGr_inmobles");add("lciutats");add("lprovincies");}}, 
						new ArrayList<String>(){{add("@this");}});
				
				panelGroup.getChildren().add(selectOneMenu);
				
				break;
				
				
			case FILE:  // FILE
				
					
				break;
			
			
			case ICHK:  // CHECKBOX
				
				Random rndInm = new Random();  
				int numAleatInm = rndInm.nextInt(3000);	
						
				ValueExpression v = (ValueExpression) Utils.resolveExpression("/images/aa.gif");
				HtmlGraphicImage icone = buildGraphicImage(
						"icone_" + numAleatInm + "_" + nomColumna, 
						v, 
						"Operación");
					
				panelGroup.getChildren().add(icone);
		
				break;
		
		
	}
	    
		Random rndInm = new Random();  
		int numAleatInm = rndInm.nextInt(3000);
	    
	    HtmlCommandLink cl = buildCommandLink(
	    					"col_" + numAleatInm + "_" + nomColumna,  
	    					"#{inmobleForm.toggleSort}", 
	    					"", 
	    					"");
	    
		////IMPORTANT : SOBRE EL HTMLCOMMANDLINK NO FUNCIONA AFEGIR SUPORT AJAX !!!!!!!!!
	    ///////afegirAjax(cl, null, new ArrayList<String>(){{add("@this");}}, new ArrayList<String>(){{add("@this");}});
	    
		cl.setValueExpression("value", (ValueExpression) Utils.resolveExpression("#{inmobleForm.sortOrders['" + nomColumna +"']}"));   
		
		cl.addActionListener(setPropertyActionListener(
								"#{inmobleForm.sortProperty}", 
								nomColumna.toString(), 
								String.class));
	    
	    panelGroup.getChildren().add(cl);
	    
        
 
		column.getFacets().put("header", panelGroup);
		
		return column;
	
	}
	
	
	
	
	
	/*-----------------------------------------------------------------
	 * <rich:column> Columna Richfaces amb suport Filtering/Sorting
	 ------------------------------------------------------------------ */
	public static UIColumn buildColumnSortFilter(
			String idColumna,
			String valueExpression,
			String valueExpressionRendered,
			String nomColumna,
			String texte,
			String tipusColumna,
			String tipusControl,
			int minimum,
			int maximum)
	{
		
		Application application = FacesContext.getCurrentInstance().getApplication(); 
			
		UIColumn column = (UIColumn) application.createComponent(UIColumn.COMPONENT_TYPE);
		
		column.setId("columnSortFilter_" + idColumna);  
		column.setTransient(true);
		
	    // ValueExpression per "rendered" (visible/invisible columna)
	    /*column.setValueExpression(
	    		"rendered", 
	    		(ValueExpression) Utils.resolveExpression(valueExpressionRendered)); */ 
		
	    HtmlOutputText otColumn;
		
	
	    if (tipusColumna.compareTo("BOOL") != 0)
		{
			// Header columna
		    HtmlOutputText header = new HtmlOutputText();
			
			header.setId("hd_" + idColumna);  
			
		    header.setValue(nomColumna);
		    header.setTransient(true);
		    column.setHeader(header);
			
			// SI ES UN DATATABLE AMB SUPORT FILTERING/SORTING
		    column = afegirSortingFilteringColumn(
		    		idColumna,
		    		nomColumna,
		    		column, 
		    		tipusColumna, 
		    		tipusControl,
		    		minimum,
		    		maximum);
		    
		    otColumn = buildOutputText(
		    		"columnSortFilter_ot_" + idColumna, 
		    		(ValueExpression) Utils.resolveExpression(valueExpression));
				 
			column.getChildren().add(otColumn);
		}
		else
		{
			column = afegirSortingFilteringColumn(
					idColumna,
					nomColumna,
					column, 
					tipusColumna, 
					tipusControl,
					minimum,
					maximum);
			
			
			Random rndInm = new Random();  
			int numAleatInm = rndInm.nextInt(3000);
			
			
			// ValueExpression del "value" del graphicImage segons si te o no la caracteristica booleana
			ValueExpression v = (ValueExpression) Utils.resolveExpression(valueExpression + " ? '/images/bSaveNR.gif' : '/images/chkOff.gif'}");
			
			HtmlGraphicImage teCaract = buildGraphicImage(
					"teCaract_" + numAleatInm + "_" + idColumna, 
					v, 
					valueExpression);
				
			column.getChildren().add(teCaract);
		
		}
		
		
		return column;
	}
	
	
	
	/*------------------------------------------------------------------------------	
	PRIMEFACES
	-------------------------------------------------------------------------------- 
	
	public static CommandLink buildCommandLinkPrime(
			String displayValue,
			String bindingValue, 
			String renderList)
	{
		
		Application application = FacesContext.getCurrentInstance().getApplication();
		
		CommandLink commandLink = (CommandLink) application.createComponent(CommandLink.COMPONENT_TYPE);
		
		commandLink.setId("rcl__prime_" + displayValue);
		commandLink.setTransient(true);
		

		commandLink.setUpdate("renderList");
		
		commandLink.addActionListener(Utils.createActionListener("#{inmobleForm.afegirInmoble}"));
		
		HtmlOutputText o = buildOutputText("prime_" + displayValue, (ValueExpression) Utils.resolveExpression("prime_" + displayValue));
		

		commandLink.getChildren().add(o);
		
		return commandLink;
		
		
	}
	*/
	
	/*
	<a4j:commandLink  execute="@this" id="editInfo" value="Edit Info" action="#{inmobleForm.modificarInmobles}"
        oncomplete="#{rich:component('myModalPanel')}.show()" />
	*/
	public static UICommandLink buildCommandLinkRich(
			String displayValue,
			String bindingValue, 
			ArrayList<String> renderList,
			ArrayList<String> executeList)
	{
		Application application = FacesContext.getCurrentInstance().getApplication();
		
		UICommandLink commandLink = (UICommandLink) application.createComponent(UICommandLink.COMPONENT_TYPE);
				
		commandLink.setId("commandLinkRich_" + displayValue);
		commandLink.setTransient(true);
		
		commandLink.setExecute(executeList);
		commandLink.setRender(renderList);
		
		//commandLink.setOncomplete("#{rich:component('formulariDatatable:dyn_taulaInmoblesVenedor:3:popupPanel_inputText_column_it_5')}.show()");
		
		if (bindingValue != null)
			commandLink.setActionExpression(Utils.createAction(bindingValue, MethodExpression.class));
		
				
		return commandLink;
	
	}
	
	
	/*-------------------------
	 <rich:dataScroller 
	--------------------------- */
	public static UIDataScroller buildDatascroller(
			String dyn_datascroller)
	{
		Application application = FacesContext.getCurrentInstance().getApplication();

		UIDataScroller datascroller = (UIDataScroller) application.createComponent(UIDataScroller.COMPONENT_TYPE);
		
		datascroller.setId(dyn_datascroller);
		datascroller.setTransient(true);
		
		return datascroller;
	}
	

	
	
	/**********************************************************************/
	/**********************************************************************/
	/* mes mètodes de construir components                                */
	/**********************************************************************/
	/**********************************************************************/
	
    // Init --------------------------------------------------------------------------------------

    private HtmlPanelGroup actionDataTableGroup; // Placeholder.
    private HtmlDataTable actionDataTable;
    private Inmobles dataItem;
	
	  // Actions -----------------------------------------------------------------------------------

    private void populateActionDataTable() {

        // Create <h:dataTable binding="#{myBean.dataTable}"
        // value="#{myBean.dataList}" var="dataItem">.
        HtmlDataTable actionDataTable = new HtmlDataTable();
        actionDataTable.setValueExpression("binding",
            createValueExpression("#{myBean.actionDataTable}", HtmlDataTable.class));
        
        actionDataTable.setValueExpression("value",
            createValueExpression("#{myBean.dataList}", List.class));
        actionDataTable.setVar("dataItem");

        // Create <h:column> for 'ID' column.
        HtmlColumn idColumn = new HtmlColumn();
        actionDataTable.getChildren().add(idColumn);

        // Create <h:outputText value="ID"> for <f:facet name="header"> of 'ID' column.
        HtmlOutputText idHeader = new HtmlOutputText();
        idHeader.setValue("ID");
        idColumn.setHeader(idHeader);

        // Create <h:commandLink value="#{dataItem.id}" action="#{myBean.editPopulatedDataItem}" />
        // for the body of 'ID' column.
        HtmlCommandLink idLink = new HtmlCommandLink();
        idLink.setId("edit"); // Custom ID is required in dynamic UIInput and UICommand.
        idLink.setValueExpression("value",
            createValueExpression("#{dataItem.id}", Long.class));
        idLink.setActionExpression(
            createActionExpression("#{myBean.editPopulatedDataItem}", String.class));
        idColumn.getChildren().add(idLink);

        // Create <h:column> for 'Name' column.
        HtmlColumn nameColumn = new HtmlColumn();
        actionDataTable.getChildren().add(nameColumn);

        // Create <h:outputText value="Name"> for <f:facet name="header"> of 'Name' column.
        HtmlOutputText nameHeader = new HtmlOutputText();
        nameHeader.setValue("Name");
        nameColumn.setHeader(nameHeader);

        // Create <h:outputText value="#{dataItem.name}"> for the body of 'Name' column.
        HtmlOutputText nameOutput = new HtmlOutputText();
        nameOutput.setValueExpression("value",
            createValueExpression("#{dataItem.name}", String.class));
        nameColumn.getChildren().add(nameOutput);

        // Create <h:column> for 'Value' column.
        HtmlColumn valueColumn = new HtmlColumn();
        actionDataTable.getChildren().add(valueColumn);

        // Create <h:outputText value="Value"> for <f:facet name="header"> of 'Value' column.
        HtmlOutputText valueHeader = new HtmlOutputText();
        valueHeader.setValue("Value");
        valueColumn.setHeader(valueHeader);

        // Create <h:outputText value="#{dataItem.value}"> for the body of 'Value' column.
        HtmlOutputText valueOutput = new HtmlOutputText();
        valueOutput.setValueExpression("value",
            createValueExpression("#{dataItem.value}", String.class));
        valueColumn.getChildren().add(valueOutput);

        // Finally add the datatable to <h:panelGroup binding="#{myBean.actionDataTableGroup}">.
        actionDataTableGroup = new HtmlPanelGroup();
        actionDataTableGroup.getChildren().add(actionDataTable);
    }

    public String editPopulatedDataItem() {

        // Get selected MyData item to be edited.
        dataItem = (Inmobles) actionDataTable.getRowData();

        return "edit"; // Navigation case.
    }

    // Getters -----------------------------------------------------------------------------------

    public HtmlPanelGroup getActionDataTableGroup() {
        // This will be called once in the first RESTORE VIEW phase.
        if (actionDataTableGroup == null) {
            populateActionDataTable(); // Populate action datatable.
        }
        return actionDataTableGroup;
    }

    public HtmlDataTable getActionDataTable() {
        return actionDataTable;
    }

    // Setters -----------------------------------------------------------------------------------

    public void setActionDataTableGroup(HtmlPanelGroup actionDataTableGroup) {
        this.actionDataTableGroup = actionDataTableGroup;
    }

    public void setActionDataTable(HtmlDataTable actionDataTable) {
        this.actionDataTable = actionDataTable;
    }

    // Helpers -----------------------------------------------------------------------------------

    private ValueExpression createValueExpression(String valueExpression, Class<?> valueType) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getExpressionFactory().createValueExpression(
            facesContext.getELContext(), valueExpression, valueType);
    }

    private MethodExpression createActionExpression(String actionExpression, Class<?> returnType) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getExpressionFactory().createMethodExpression(
            facesContext.getELContext(), actionExpression, returnType, new Class[0]);
    }
	
}



