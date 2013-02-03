

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class ObjecteNode implements ActionListener {
   
    private static ArrayList<ObjecteNode> CHILDREN_ABSENT = new ArrayList<ObjecteNode>();
    
    private ArrayList<ObjecteNode> children;
    private ArrayList<ObjecteNode> fulles = new ArrayList<ObjecteNode>();
   
    private int idArbre;  // es el ID dins l'arbre de l'objecte (Ex: wizard,document,...) 
    private int idAnterior;  // es el ID de l'objecte (Ex: wizard,document,...) anterior en l'arbre
    private String abrev;   // correspon al camp abrev lligat a idioma_XX.properties
    private String descripcio; //
    private Boolean esFulla;
    
    public String nomObjecte;  // string que es passa per identificar el tipus d'objecte de l'arbre (ex: wizard, document,...)
    
	FacesContext context;
	ResourceBundle bundle;
	
	accesDB ac;
    
    public ObjecteNode(int idArbre, int idAnterior,String titol, String descripcio, String nomObjecte) {
    	
    	this.nomObjecte = nomObjecte;
    	this.idArbre = idArbre;
        this.idAnterior = idAnterior;
        this.abrev = titol;
        this.descripcio = descripcio;
                
		context  = FacesContext.getCurrentInstance();		
		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
    }

    
    public synchronized ArrayList<ObjecteNode> getNodes() {
        if (children == null) {
            
            ArrayList<Object> resourceObjectes = new ArrayList<Object>();
            
    		Statement stmt = null;
    		ResultSet rs = null;
    		String sql = "";
    		
    		Logger log=Logger.getLogger("");
    		
    		
    		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale());
    		
    		// calculem tots els Objectes de l'arbre (ex: wizards,documents,...) del root
    		try {
	    		ac = new accesDB();
	    		ac.cargarDriver();
	    		
	    		stmt = ac.con.createStatement();
	    		
	    		if (this.idAnterior == -1)  // cas que volem obtenir tots els objectes root de l'arbre
	    			sql = "SELECT DISTINCT IDARBRE, IDANTERIOR,ABREV,DESCRIPCIO FROM " + nomObjecte + "s WHERE ESROOT = 1";  // busquem els Root
	    		else
	    			if (this.idAnterior >= 0)
	    				sql = "SELECT DISTINCT IDARBRE, IDANTERIOR,ABREV, DESCRIPCIO FROM " + nomObjecte + "s WHERE IDANTERIOR =" + this.idArbre;
	    		
		            if (stmt.execute(sql)) 
		            {
		            	rs = stmt.getResultSet();
		            }
	    		
				    while (rs.next())
		            	{
		            	
					  		Class cl = Class.forName(nomObjecte);

					  		ObjecteNode obj =(ObjecteNode) cl.newInstance();
					  		obj.setIdArbre(rs.getInt("IDARBRE"));   // id dins l'arbre de l'objecte
					  		obj.setIdAnterior(rs.getInt("IDANTERIOR"));  // idAnterior = indica el anterior objecte dins l'arbre
					  		obj.setAbrev(bundle.getString("titol" + nomObjecte +"." +rs.getString("ABREV")));
					  		obj.setDescripcio(rs.getString("DESCRIPCIO"));
				  			resourceObjectes.add(obj);
		            	}
		            
						ac.con.close();  		
	        	
					}	catch (SQLException ex) {
						    // handle any errors
						    System.out.println("SQLException: " + ex.getMessage());
						    System.out.println("SQLState: " + ex.getSQLState());
						    System.out.println("VendorError: " + ex.getErrorCode());
				
    		} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
    					////////////log.error("ObjectNode.java : " + e.getMessage());
						e.printStackTrace();
					} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				//////////////log.error("ObjectNode.java : " + e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				/////////////////log.error("ObjectNode.java : " + e.getMessage());
				e.printStackTrace();
			}
			
			
            // resource tenim tots els objectes root de l'arbre (ex:wizards, documents,...) 
            if (resourceObjectes.size() != 0) {
                ArrayList<Object> nodes =  resourceObjectes;
                children = new ArrayList<ObjecteNode>();
                
                Iterator itNodes = nodes.iterator();
                while (itNodes.hasNext())
                {
                	Object objNode = itNodes.next();
                	int idArbre = ((ObjecteNode)objNode).getIdArbre(); // conté  l'ID dins l'arbre de l'objecte
                    int idAnterior = ((ObjecteNode)objNode).getIdAnterior(); // conté  l'IDAnterior de l'bjecte dins l'arbre
                    String titol = ((ObjecteNode)objNode).getAbrev(); // 
                    String descripcio = ((ObjecteNode)objNode).getDescripcio(); // conté el texte que ha de sortir de l'objecte dins l'arbre
                    
                    Boolean fulla = ((ObjecteNode)objNode).esFulla(nomObjecte,idArbre);
                    //if (!fulla)
                    //{
                    	children.add(new ObjecteNode(idArbre, idAnterior,titol, descripcio, nomObjecte));

                    	children.get(children.size()-1).getNodes();
                   // }
                    //else
                    //{
                    	//fulles.add(new ObjecteNode(this.idArbre, this.idAnterior,titol, this.descripcio, this.nomObjecte));
                        ///fulles.get(children.size()-1).getNodes();
                      
                        //children = CHILDREN_ABSENT;
                    //}
                  
                }
            }
            else
            {
            	String titol = ((ObjecteNode)this).getAbrev(); // 
            	fulles.add(new ObjecteNode(this.idArbre, this.idAnterior,titol, this.descripcio, this.nomObjecte));
                ///fulles.get(children.size()-1).getNodes();
              
                children = CHILDREN_ABSENT;
            }
       
        }

        return children;
    }
 	
	public int getIdArbre() {
		return idArbre;
	}


	public void setIdArbre(int idArbre) {
		this.idArbre = idArbre;
	}


	public int getIdAnterior() {
		return idAnterior;
	}

	public void setIdAnterior(int idAnterior) {
		this.idAnterior = idAnterior;
	}
	
	
	public String getAbrev() {
		return abrev;
	}

	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}


	public String getDescripcio() {
		return descripcio;
	}


	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}


	

	public void processAction(ActionEvent event) throws AbortProcessingException {
		// TODO Auto-generated method stub
		String strAtributo1 = event.getComponent().getAttributes().get("nombreAtributo1").toString();
		 String strAtributo2 = event.getComponent().getAttributes().get("nombreAtributo2").toString();
	  
		 System.out.println(strAtributo1 + " " + strAtributo2);
		
	}

	public ObjecteNode() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ArrayList<ObjecteNode> getFulles() {
		return fulles;
	}


	public void setFulles(ArrayList<ObjecteNode> fulles) {
		this.fulles = fulles;
	}


	private Boolean esFulla(String nomObjecte, int idArbre) {
		
		Statement stmt = null;
		Boolean esFulla = false;
		
		ac = new accesDB();
		ac.cargarDriver();
		
		try {
			stmt = ac.con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "SELECT DISTINCT IDARBRE, IDANTERIOR,ABREV, DESCRIPCIO FROM " + nomObjecte + 
			"s WHERE IDANTERIOR =" + idArbre;
		
		try {
			ResultSet rs = stmt.executeQuery(sql);
			rs.last();
			esFulla = rs.getRow() == 0 ? true : false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return esFulla;
	}

	
	
}

