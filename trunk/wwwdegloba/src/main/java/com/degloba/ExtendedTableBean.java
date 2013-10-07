package com.degloba;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.ajax4jsf.model.ExtendedDataModel;

 
import churchillobjects.rss4j.RssChannelItem;


/**
 * @author Ilya Shaikovsky
 *
 */
public class ExtendedTableBean {
	 private String sortMode="single";
	 private String selectionMode="multi";
	 private SimpleSelection seleccio;
	 private List<RssChannelItem> selectedItems = new ArrayList<RssChannelItem>(); ////////////////////
	 
				
	 private ExtendedDataModel<RssChannelItem> dataModel;
	 private List<RssChannelItem> items = new ArrayList<RssChannelItem>(); ////////////////////
		
		
 public SimpleSelection getSeleccio() {
	 return seleccio;
 }
 
 public List<RssChannelItem> getSelectedItems() {
	 return selectedItems;
 }
 
 public void setSelectedItems(List<RssChannelItem> selectedItems) {
	 this.selectedItems = selectedItems;
 }
 
 public void setSeleccio(SimpleSelection seleccio) {
	 this.seleccio = seleccio;
 }
 
 public String getSortMode() {
	 return sortMode;
 }
 
 public void setSortMode(String sortMode) {
	 this.sortMode = sortMode;
 }
 
 public String getSelectionMode() {
	 return selectionMode;
 }
 
 public void setSelectionMode(String selectionMode) {
	 this.selectionMode = selectionMode;
 }
 
 
public ExtendedTableBean(String nomObjecte) {  // exemple : RssItem
	 
	 /* per si volem utilitzar la classe per qualsevol objecte que vulguem posar en una taula ExtendedTableBean */
	 Object objecte = new Object();
		
		try {
			Class cl = Class.forName(nomObjecte);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 omplirItems();  
 }
 
 
 public ExtendedTableBean() {
	 omplirItems();  
 }
 
 
 /*
 public ExtendedDataModel<RssChannelItem> getItemsDataModel() {

	 if (dataModel == null) {
		 dataModel = new ExtendedDataModel<RssChannelItem>(new DataProvider<RssChannelItem>(){
 
			 private static final long serialVersionUID = 5054087821033164847L;
			 
			 public RssChannelItem getItemByKey(Object key) {
			 for(RssChannelItem c : items){
				 if (key.equals(getKey(c))){
					 return c;
				 }
			 }
			 return null;
			 }
			 
			 public List<RssChannelItem> getItemsByRange(int firstRow, int endRow) {
				 return items.subList(firstRow, endRow);
			 }
			 
			 public Object getKey(RssChannelItem item) {
				 return item.getItemTitle();
			 }
			 
			 public int getRowCount() {
				 return items.size();
			 }
		 
		 });
	 }
 return dataModel;

 }
  */
 
 /*
public void takeSelection() {
	 
	 
	 getSelectedItems().clear();
Iterator<Object> iterator = getSeleccio().getKeys();
while (iterator.hasNext()){
Object key = iterator.next();
getItemsDataModel().setRowKey(key);
getSelectedItems().add((RssChannelItem) getItemsDataModel().getRowData());
}
}
  */
 
 public void setItems(List<RssChannelItem> items) {
	 this.items = items;
 }
 
 
/* Recuperem els "registres" de la taula  */
public void omplirItems()
{
	Locale currentLocale;
	FacesContext context;
	
	context = FacesContext.getCurrentInstance();
	currentLocale = context.getViewRoot().getLocale();
	
	String llengua=currentLocale.getLanguage();
	
	HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
	//blogs nB = (blogs)session.getAttribute("blogs");
	
	/*
	String blogActiu = nB.getTitolActiu();
	SimpleRssOutput s = new SimpleRssOutput("http://" + blogActiu + llengua + ".blogspot.com/feeds/posts/default?alt=rss");
	s.setUrl("http://"+ blogActiu +"es" + ".blogspot.com/feeds/posts/default?alt=rss");
	
	
	try {
		
		// Recuperem nomes els items del Blog de la pagina en qui estem
		
		 setItems(s.getMultipleItems(14));
		
	} catch (InvalidItemClassException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	*/
}

}
