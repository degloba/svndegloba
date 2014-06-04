package com.degloba;

import java.util.ArrayList;
import java.util.List;


import churchillobjects.rss4j.RssChannelItem;


/*
 * Es una classe ExtendedTableBean
 */
public class extendedTableRss extends ExtendedTableBean {
	
	 private List<RssChannelItem> items = new ArrayList<RssChannelItem>(); 

	@Override
	public void omplirItems() {
		// TODO Auto-generated method stub
		//super.omplirItems();
		
	/*	
		
		Locale currentLocale;
		FacesContext context;
		
		context = FacesContext.getCurrentInstance();
		currentLocale = context.getViewRoot().getLocale();
		
		String llengua=currentLocale.getLanguage();
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		blogs nB = (blogs)session.getAttribute("blogs");
		
		String blogActiu = nB.getTitolActiu();
		SimpleRssOutput s = new SimpleRssOutput("http://" + blogActiu + llengua + ".blogspot.com/feeds/posts/default?alt=rss");
		s.setUrl("http://"+ blogActiu +"es" + ".blogspot.com/feeds/posts/default?alt=rss");
		
		
		try {
			
			// Recuperem nomes els items del Blog de la pagina en que estem
			
			 setItems(s.getMultipleItems(14));
			
		} catch (InvalidItemClassException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		*/	
		
	}
		

	
	public void setItems(List<RssChannelItem> items) {
		 this.items = items;
	 }
	 

	
	
	
}
