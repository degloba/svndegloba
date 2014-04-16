package com.degloba;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;


import churchillobjects.rss4j.RssChannelItem;


public class noticies {
		
	private List<RssChannelItem> items = new ArrayList<RssChannelItem>();
	private static int numPag=1;
	
	FacesContext context;
		
	public noticies() {
		// TODO Auto-generated constructor stub
		super();
				
		omplirNoticies();
	}
			
	
	public List<RssChannelItem> getItems() {
		return this.items;
	}

	public void setItems(List<RssChannelItem> items) {
		this.items = items;
	}

	
	public int getNumPag() {
		return numPag;
	}



	public void setNumPag(int numPag) {
		noticies.numPag = numPag;
	}


	private void omplirNoticies() {
	
		Locale currentLocale;
		
		context = FacesContext.getCurrentInstance();
		currentLocale = context.getViewRoot().getLocale();
		
		String llengua=currentLocale.getLanguage();
	/*	
		SimpleRssOutput s = new SimpleRssOutput("http://noticies" + llengua + ".blogspot.com/feeds/posts/default?alt=rss");
		s.setUrl("http://noticies" + llengua + ".blogspot.com/feeds/posts/default?alt=rss");
			
		List<RssChannelItem> items= new ArrayList<RssChannelItem>(), itemsTemp =new ArrayList<RssChannelItem>();
			
		try {
				
			// Recuperem nomes els items de la pagina en qu� estem
				
			itemsTemp = s.getMultipleItems(14);
				
			for (int i=0;i< itemsTemp.size();i++)
			{
			 if (i >= ((numPag-1) * 4) && (i < (numPag * 4)))
				 items.add(itemsTemp.get(i));
			}
			
			setItems(items);
				
			} catch (InvalidItemClassException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		*/
		}
	
	
	public void paginaSeguent()
	{
		numPag++;
		setNumPag(numPag);
	
		omplirNoticies();
	}
	
	public void paginaAnterior()
	{
		numPag--;
		setNumPag(numPag);

		omplirNoticies();		
	}
	
}
