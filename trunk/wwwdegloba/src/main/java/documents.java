

import javax.swing.tree.TreeNode;

import com.benfante.jslideshare.SlideShareAPI;
import com.benfante.jslideshare.SlideShareAPIFactory;
import com.benfante.jslideshare.messages.Slideshow;
import com.benfante.jslideshare.messages.User;

import java.sql.*;

import java.util.*;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.http.HttpSession;


public class documents  implements TreeNode , ActionListener{

	private static final long serialVersionUID = 1L;

	private Map<Object, TreeNode> documents = null;
	
	private Connection con = null;
	
	private String idDoc;  // id del document que retorna el SlideShare
	private String doc;  // document que retorna el SlideShare
	private String embedCode;   // codi que retorna el SlideShare
	private String tags; // tags que retorna el SlideShare.Indica el tipus de document (pdf, video,..) que retorna
	private String abrev; //abrev de la taula ha de coincidir amb el titol de l'SlidShare
	private String descripcioActiva; //
		

	private Map<Object, TreeNode> getDocuments() {
		if (this.documents == null) { 
				omplirDocuments();
		}  
	return this.documents;
	}
	
	
	public void addEntorn(entorn entorn) {
		
		try {
			addChild(Long.toString(getNextId()),entorn);	
		} catch (Exception ex){
			System.out.println("Error addEntorn : " + ex.getMessage());
		}
		
	}
	

	public void addChild(Object identifier, TreeNode child) {
		// TODO Auto-generated method stub
		
		try {
			getDocuments().put(identifier, child); 
			////////child.setParent(this);	
		} catch (Exception ex) {
			System.out.println("Error addChild entorn : " + ex.getMessage());	
		}
	}

	public TreeNode getChild(Object id) {
		// TODO Auto-generated method stub
		//return null;
		return (TreeNode) getDocuments().get(id);
	}


	public Iterator getChildren() {
		// TODO Auto-generated method stub
		return getDocuments().entrySet().iterator();	
	}


	
	public void removeChild(Object id) {
		// TODO Auto-generated method stub
		getDocuments().remove(id);
	}


	public void setData(Object arg0) {
		// TODO Auto-generated method stub
		
	}


	public void setParent(TreeNode arg0) {
		// TODO Auto-generated method stub
		
	}

	 
	/*
	 * Propietats i Metodes auxiliars
	 */

	
	public void setEntorns(Map documents) {
		this.documents = documents;
	}


	public String getType() {
		return "documents";
	}
	

	public String getTags() {
		return tags;
	}



	public void setTags(String tags) {
		this.tags = tags;
	}
  	
  	
  	/*
  	 * Omple L'ARBRE
  	 */
  	private void omplirDocuments() {
  		
	Statement stmt = null;
	ResultSet rs = null;
		
	accesDB aDB = new accesDB();
		
	aDB.cargarDriver();
		
	Logger log=Logger.getLogger("");
	
	try {
		stmt = con.createStatement();
    	
    if (stmt.execute("SELECT * FROM documents ORDER BY ordre")) {
        rs = stmt.getResultSet();
        }
        
    documents = new HashMap<Object, TreeNode>();
		  
	Boolean seguir=true;
    while (rs.next() || seguir== true)
  		{
        	
        	Integer ordre = rs.getInt("ordre");
        	entorn entorn = getEntornByName(rs.getString("entorn"),this);
			
        	document document = new document(ordre);
        	document.setAbrev(rs.getString("abrev"));
        	document.setAction(rs.getString("action"));
        	document.setIcon(rs.getString("icon"));
        	document.setDescripcio(rs.getString("descripcio"));

			entorn.addDocument(document);
					
  		}
        
    	con.close();  		
		
		}	catch (SQLException ex) {
			    // handle any errors
				//log.error("documents.java : " + ex.getMessage());
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
		}
  	
  	}

  	
  	private long nextId = 0;
  	
	private long getNextId() {
		return nextId++;
	}
	
	private Map<?, ?> documentCache = new HashMap();
	private Map<String, entorn> entornCache = new HashMap<String, entorn>();
	
	
	private entorn getEntornByName(String nomEntorn, documents documents) {
		entorn entorn = (entorn)entornCache.get(nomEntorn);
		if (entorn==null) {
			entorn = new entorn(getNextId());
			entorn.setNomEntorn(nomEntorn);
			entornCache.put(nomEntorn, entorn);
			this.addEntorn(entorn);
		}
		//else
			//entornCache.put(nomEntorn, entorn);
		return entorn;
	}
	
	
	
	public String getIdDoc() {
		return idDoc;
	}



	public void setIdDoc(String idDoc) {
		this.idDoc = idDoc;
	}



	public String getDoc() {
		return doc;
	}



	public void setDoc(String doc) {
		this.doc = doc;
	}


	public String getEmbedCode() {
		return embedCode;
	}



	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
	}

	
	public String getAbrev() {
		return abrev;
	}


	public void setAbrev(String descripcio) {
		this.abrev = descripcio;
	}

	
	public String getDescripcioActiva() {
		return descripcioActiva;
	}


	public void setDescripcioActiva(String descripcioActiva) {
		this.descripcioActiva = descripcioActiva;
	}


	public void processAction(ActionEvent event) throws AbortProcessingException {
		// TODO Auto-generated method stub
		
		/*** Visibilitzem el panell dels documents i invisibilitzem el panell de l"esquema degloba" ***/
		FacesContext context;
		context  = FacesContext.getCurrentInstance();
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		visiblePanellDegloba visiblePanellDegloba = (visiblePanellDegloba)session.getAttribute("visiblePanellDegloba");
		
		//Només si el panell de l'esquema degloba esta visible cal modificar la visibilitat
		if (visiblePanellDegloba.getVisible())
			visiblePanellDegloba.cambiaVisible();
		/******************************************************************************************/
		
		
		
		// Calculem el document que hem de visualitzar.A partir de la descripcio
		abrev = event.getComponent().getAttributes().get("titol").toString();
		setDescripcioActiva(event.getComponent().getAttributes().get("descripcio").toString());
		
		
		/* Calculem el embedCode que retorna el SlideShare a partir de l'API Java per SlideShare */
		SlideShareAPI ssapi =  SlideShareAPIFactory.getSlideShareAPI("FfZhXNRX", "TAqlQKeN"  );   // Your API key + Your shared secret
      
		
		String tmpembedCode="";
		String tags="";

		User user = ssapi.getSlideshowByUser("degloba"); 
		List<Slideshow> list = user.getSlideshows();
		
	    for (Slideshow slideshow : list){

	        if (slideshow.getTitle().compareTo(abrev) == 0 )
	        {
	        	tmpembedCode = slideshow.getEmbedCode();
	        	tags=slideshow.getTags();
		    	
	        	break;
	        }
	      }
	    	    
	    
	    try 
	    {
	    	//Calculem els valors per construir l'OBJECT/EMBED del document SlideShare
		    String idDoc = (tmpembedCode.split("\\?")[1]).split("&")[0];
		    String doc = (tmpembedCode.split("\\?")[1]).split("&")[1];
		    
		    setIdDoc(idDoc.split("=")[1]);  //SlideShare
		    setDoc(doc.split("=")[1]) ;  //SlideShare
		    
			setEmbedCode(tmpembedCode.replaceAll("&", "&amp;"));
			setTags(tags);
		
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("SQLException: " + ex.getMessage());
	    }
		
		System.out.println(getIdDoc() + " " + getDoc() + " " + getEmbedCode() + " " + getTags());
		
	}
	

	
	public List<document> getDocument() {
  
        return (List<document>) documents;
    }


	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return null;
	}


	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}


	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return 0;
	}


	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}


	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}


	
} 




