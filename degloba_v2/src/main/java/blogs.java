

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.http.HttpSession;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

import com.google.gdata.client.*;
import com.google.gdata.data.*;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import churchillobjects.rss4j.*;


public class blogs implements ActionListener{
	
	private static String titolActiu;  // titol del Blog actiu
	private static String descripcioBlogActiu;  // descripcio del Blog actiu
	private List<Boolean> estatBlogs=new ArrayList<Boolean>();  // per saber quin blog esta activat
	
	private List<RssChannelItem> itemsBlog = new ArrayList<RssChannelItem>();
	SimpleSelection seleccio = new SimpleSelection();
	
	private  ArrayList<blog> items = new ArrayList<blog>();
	
	private Boolean mostrar=false;
	private static int numPag=1;
	int numItemsBlogPagina = 4;
	
	String selection;
	String accio;  // visualitzar, cerca
		
	accesDB ac;
	FacesContext context;

	blog blog;
	
	private String sortMode="single";
	private String selectionMode="multi";
		
	public blogs() {
		// TODO Auto-generated constructor stub
		super();
		context  = FacesContext.getCurrentInstance();
		
		omplirBlogs();
		//////////////////omplirTweets();
		//omplirItemsBlog();
		
	}
	
	
	
	public void processAction(ActionEvent event) throws AbortProcessingException {
		// TODO Auto-generated method stub
		
		
		/*** Visibilitzem el panell dels blogs i invisibilitzem el panell de l"esquema degloba" ***/
		FacesContext context;
		context  = FacesContext.getCurrentInstance();
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		visiblePanellDegloba visiblePanellDegloba = (visiblePanellDegloba)session.getAttribute("visiblePanellDegloba");
		
		//Només si el panell de l'esquema degloba esta visible cal modificar la visibilitat
		if (visiblePanellDegloba.getVisible())
			visiblePanellDegloba.cambiaVisible();
		/******************************************************************************************/
		
		
		// Calculem el Blog que hem de visualitzar.
		String quinBlog = event.getComponent().getAttributes().get("quinBlog").toString();
		setAccio(event.getComponent().getAttributes().get("accio").toString());
		
		Iterator iterador =items.listIterator();
		
		while (iterador.hasNext())
		{
			
			blog actual = ((blog)iterador.next());
			
			if (quinBlog.compareTo(Integer.toString(actual.getId())) == 0)
			{
				estatBlogs.set(actual.getId() - 1 ,true);
				titolActiu=actual.getTitol();
				descripcioBlogActiu=actual.getDescripcio();
			}
			else
				estatBlogs.set(actual.getId() - 1 ,false); 
		}
			
		mostrar=true;
		
		omplirItemsBlog();
		
	}
	
	
	
	public  void printAllPosts() {
		
		
		Locale currentLocale;
		
		context = FacesContext.getCurrentInstance();
		currentLocale = context.getViewRoot().getLocale();
		
		String llengua=currentLocale.getLanguage();
		
		
		
		GoogleService myService = new GoogleService("blogger", "exampleCo-exampleApp-1");
		try {
			myService.setUserCredentials("santasusanap@gmail.com", "tivoli00");
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		  // Request the feed
		URL feedUrl = null;
		try {
			feedUrl = new URL("http://" + titolActiu + llengua + ".blogspot.com/feeds/posts/default?alt=rss");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 // URL feedUrl = new URL("http://www.blogger.com/feeds/" + blogID + "/posts/default");
		  Feed resultFeed = null;
		try {
			resultFeed = myService.getFeed(feedUrl, Feed.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		  // Print the results
		  System.out.println(resultFeed.getTitle().getPlainText());
		  for (int i = 0; i < resultFeed.getEntries().size(); i++) {
		    Entry entry = resultFeed.getEntries().get(i);
		    System.out.println("\t" + entry.getTitle().getPlainText());
		  }
		  System.out.println();
		}

	
	public void omplirItemsBlog()
	{
		Locale currentLocale;
		
		context = FacesContext.getCurrentInstance();
		currentLocale = context.getViewRoot().getLocale();
		
		String llengua=currentLocale.getLanguage();
		
		printAllPosts();
		/*
		SimpleRssOutput s = new SimpleRssOutput("http://" + titolActiu + llengua + ".blogspot.com/feeds/posts/default?alt=rss");
		s.setUrl("http://" + titolActiu + llengua + ".blogspot.com/feeds/posts/default?alt=rss");
		
		List<RssChannelItem> itemsBlog= new ArrayList<RssChannelItem>(), itemsTemp =new ArrayList<RssChannelItem>();
		
		Logger log=Logger.getLogger("");
		
		try {
			
			// Recuperem nomes els items del Blog de la pagina en què estem
			
			itemsTemp = s.getMultipleItems(14);
			
			for (int i=0;i< itemsTemp.size();i++)
			{
				 if (i >= ((numPag-1) * numItemsBlogPagina) && (i < (numPag * numItemsBlogPagina)))
				 {
					 itemsBlog.add(itemsTemp.get(i));
				 }
				 
			}
		
			setItemsBlog(itemsBlog);
			
		} catch (InvalidItemClassException e) {
			// TODO Auto-generated catch block
			log.error("blogs.java : " + e.getMessage());
			e.printStackTrace();
		}		
		*/
		
	}
	
	
	private void omplirBlogs() {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
		
		ac = new accesDB();
		ac.cargarDriver();
		
		stmt = ac.con.createStatement();
    	
        if (stmt.execute("SELECT DISTINCT BLOGID , TITOL , DESCRIPCIO FROM blogs order by blogid")) {
            rs = stmt.getResultSet();
        }
        
        while (rs.next())
  		{
	  		blog = new blog();
	  		
	  		blog.setId(rs.getInt("BLOGID"));
	  		blog.setTitol(rs.getString("TITOL"));
	  		blog.setDescripcio(rs.getString("DESCRIPCIO"));
									
			this.items.add(blog);

			estatBlogs.add(false);
				
  		}
        
    	ac.con.close();  		
		
		}	catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
		this.setItems(this.items);
		
	}


	// afegim un tweet (twitter) per cada ultimaNoticia del blog del mateix nom
	private void omplirTweets() {
		
		String tweet;
	
	/*	
		SimpleRssOutput s = new SimpleRssOutput("http://ultimesnoticieses.blogspot.com/feeds/posts/default?alt=rss");
		s.setUrl("http://ultimesnoticieses.blogspot.com/feeds/posts/default?alt=rss");
		
		List<RssChannelItem> itemsBlog= new ArrayList<RssChannelItem>(), itemsTemp =new ArrayList<RssChannelItem>();
		
		Logger log=Logger.getLogger("");
		
		try {
			
			itemsTemp = s.getMultipleItems(14);
			
			for (int i=0;i< itemsTemp.size();i++)
			{
				 itemsBlog.add(itemsTemp.get(i));
					 
				 ConfigurationBuilder cb = new ConfigurationBuilder();
				 cb.setDebugEnabled(true)
					 
				   .setOAuthConsumerKey("5WCqqklWV5mLKirtxHTow")
				   .setOAuthConsumerSecret("WgOsaHWGtcC6V4vBpabG1lmQmC6ah6nqNEOpNhyw6KA")
				   .setOAuthAccessToken("202092247-lSfywII0IQ1AEO0ExwKnhjiPDFhq46OrOhSM6xD2")
				   .setOAuthAccessTokenSecret("yWEJuJ0yFZik3REXo8TuoyROLVuhPwCBZtbi6IjlQ");
					 
				 TwitterFactory tf = new TwitterFactory(cb.build());
				 Twitter twitter = tf.getInstance();
					 
				 Status nouTweet;
				 
				try {
					if (itemsTemp.get(i).getItemTitle().length()> 140)
						tweet = itemsTemp.get(i).getItemTitle().substring(0,139);
					else
						tweet = itemsTemp.get(i).getItemTitle();
						
					Query q = new Query();
					q.setQuery("degloba");
					QueryResult resultatQuery = twitter.search(q);
					
					List<Tweet> ltweets = resultatQuery.getTweets();
					
					Boolean trobat= false;
		            for (Tweet itweet : ltweets) {
		                
		                if (itweet.getText().compareTo(tweet) == 0 || itweet.getText().contains("@degloba"))
		                	trobat = true;
		            }
					
					
					if (!trobat)
						nouTweet = twitter.updateStatus(tweet);  // crea el tweet
						
						
					} catch (TwitterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 
			}
		
			setItemsBlog(itemsBlog);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//log.error("blogs.java : " + e.getMessage());
			e.printStackTrace();
		}		
		*/
		
	}
	
	
	
	public void paginaSeguent()
	{
		numPag++;
		setNumPag(numPag);
		
		omplirItemsBlog();
			
	}
	
	public void paginaAnterior()
	{
		numPag--;
		setNumPag(numPag);
		
		omplirItemsBlog();
		
	}
	
	
	public Boolean getMostrar() {
		return mostrar;
	}


	public void setMostrar(Boolean mostrar) {
		this.mostrar = mostrar;
	}


	public List<Boolean> getestatBlogs() {
		return estatBlogs;
	}


	public void setestatBlogs(List<Boolean> estatBlogs) {
		this.estatBlogs = estatBlogs;
	}


	public String getTitolActiu() {
		return titolActiu;
	}


	public void setTitolActiu(String titolActiu) {
		this.titolActiu = titolActiu;
	}
	
	
	public String getDescripcioBlogActiu() {
		return descripcioBlogActiu;
	}



	public void setDescripcioBlogActiu(String descripcioBlogActiu) {
		this.descripcioBlogActiu = descripcioBlogActiu;
	}



	public ArrayList<blog> getItems() {
		return this.items;
	}

	public void setItems(ArrayList<blog> items) {
		this.items = items;
	}

	

	
	public SimpleSelection getSeleccio() {
		return seleccio;
	}



	public void setSeleccio(SimpleSelection seleccio) {
		this.seleccio = seleccio;
	}



	public List<RssChannelItem> getItemsBlog() {
		return itemsBlog;
	}


	public void setItemsBlog(List<RssChannelItem> itemsBlog) {
		this.itemsBlog = itemsBlog;
	}

	public int getNumPag() {
		return numPag;
	}



	public String getSelection() {
		return selection;
	}



	public void setSelection(String selection) {
		this.selection = selection;
	}



	public void setNumPag(int numPag) {
		this.numPag = numPag;
	}



	public String getAccio() {
		return accio;
	}



	public void setAccio(String accio) {
		this.accio = accio;
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



}
