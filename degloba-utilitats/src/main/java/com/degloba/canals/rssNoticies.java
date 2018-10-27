package com.degloba.canals;

import java.io.File;

import java.util.Enumeration;

import churchillobjects.rss4j.RssChannel;
import churchillobjects.rss4j.RssChannelItem;
import churchillobjects.rss4j.RssDocument;
import churchillobjects.rss4j.generator.RssGenerationException;
import churchillobjects.rss4j.generator.RssGenerator;


public class rssNoticies {
	
	
	public static void main(String[] args) throws RssGenerationException {
		// TODO Auto-generated method stub
		
		RssDocument doc = new RssDocument();
		doc.setVersion(RssDocument.VERSION_10);
		
		RssChannel channel = new RssChannel();
		channel.setChannelTitle("Karens Recipes | Most Recent");
		channel.setChannelLink("http://santasu.blogspot.com/feeds/posts/default?alt=rss");
		channel.setChannelDescription("The 10 most recently added recipes in the soup category.");
		channel.setChannelUri("http://santasu.blogspot.com/feeds/posts/default?alt=rss");
		doc.addChannel(channel);
		
		Enumeration<?> ee = channel.items();
		


		
		// connect to the datasource
		// iterate over something (db? vector?...)
		RssChannelItem item = new RssChannelItem();
		item.setItemTitle("Karens Recipes | Most Recent");
		item.setItemLink("http://www.karensrecipes.com/3/Soup/default.jsp");
		item.setItemDescription("The 10 most recently added recipes in the soup category.");
		channel.addItem(item);
		
		int a =channel.getItemCount();
				
		File file = new File("/discNFS/rss.xml");
		try{
		RssGenerator.generateRss(doc, file);
		System.out.println("RSS file written.");
		}
		catch(RssGenerationException e){
		e.printStackTrace();
		}
		
		

	}

}
