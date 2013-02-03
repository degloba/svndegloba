

import javax.swing.tree.TreeNode;
import java.util.*;

public class document extends ObjecteNode implements TreeNode {

	private static final long serialVersionUID = 1L;
	private String abrev;
	private String action;   // accio JSF FACES que s'ha d'executar al clikar el link del document
	private String icon;
	private String descripcio;
	private String tecnologia;
	
	private String idDoc;  // id del document que retorna el SlideShare
	private String doc;  // document que retorna el SlideShare
	private String embedCode;   // codi que retorna el SlideShare
	private String tags;   // tags que retorna el SlideShare

	
	private long id;
	private entorn nodePare;
	
	
	public  document(long id) {
		this.id=id;
	}


	public TreeNode getChildAt(Object arg0) {
		// TODO Auto-generated method stub
		//return null;
		
		throw new UnsupportedOperationException("Es fulla");
	}

	
	public Iterator getChildren() {
		// TODO Auto-generated method stub
		//return null;
		return new ArrayList().iterator();
	}

	
	public Object getData() {
		// TODO Auto-generated method stub
		//return null;
		return this;
	}

	
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		//return null;
		
		return (TreeNode) nodePare;
	}

	
	public boolean isLeaf() {
		// TODO Auto-generated method stub
//		return false;
		return true;
	}

	
	public void setData(Object arg0) {
		// TODO Auto-generated method stub
		
	}


	public void setParent(TreeNode parent) {
		// TODO Auto-generated method stub
		this.nodePare = (entorn) parent;	
	}

	public document() {
		super();
		
		}
	
	
	
	/*
	 * Propietats + Metodes Auxiliars
	 */
	
	public long getId() {
		return id;
	}
	
	public String getType() {
		return "document";
	}
	
	
	public String getAbrev() {
		return abrev;
	}

	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}

	public String getAction() {
			
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public String getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}

	public String getEmbedCode() {
		return embedCode;
	}

	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}


	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return null;
	}


	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return 0;
	}


	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}


	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}


	

}
