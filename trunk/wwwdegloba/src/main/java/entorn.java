

import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.tree.TreeNode;

import java.util.*;

public class entorn implements TreeNode {


	private static final long serialVersionUID = 1L;
	private Map documents=new HashMap<Object, TreeNode>();
	private long id;  
 	
	private documents nodePare;
	
 	private String nomEntorn;   /* nom de l'entorn  hibernate, JSF, .net,....	*/
 	

	public void addDocument(document document) {
		try {
			addChild(Long.toString(document.getId()),document);
			document.setParent(this);	
		} catch (Exception ex) {
			System.out.println("Error addDocument : " + ex.getMessage());
		}
		
	}
	
	
	public entorn(long id) {
 		this.id=id;
	}
 	
	

	public void addChild(Object identifier, TreeNode child) {
		// TODO Auto-generated method stub

		try {
			documents.put(identifier, child);	
		} catch (Exception ex)
		{
			System.out.println("Error addChild entorn : " + ex.getMessage());
		}
 		

	}


	public TreeNode getChild(Object id) {
		// TODO Auto-generated method stub
		
		return (TreeNode) documents.get(id);
		//return null;
	}


	public Iterator getChildren() {
		// TODO Auto-generated method stub
		
		return documents.entrySet().iterator();
		
		//return null;
	}


	public Object getData() {
		// TODO Auto-generated method stub
		//return null;
		return this;
	}

	public TreeNode getParent() {
		// TODO Auto-generated method stub
		//return null;
		return nodePare;
	}

	public boolean isLeaf() {
		// TODO Auto-generated method stub
		//	return false;		
		return documents.isEmpty();

	}

	
	public void removeChild(Object id) {
		// TODO Auto-generated method stub
		documents.remove(id);
	}


	public void setData(Object arg0) {
		// TODO Auto-generated method stub

	}


	public void setParent(TreeNode parent) {
		// TODO Auto-generated method stub
		nodePare = (documents) parent;
	}
	
	
	
	
	/* METODES AUXILIARS 
	 * 
	 */
	
	public documents getNodePare() {
		return nodePare;
	}


	public void setNodePare(documents nodePare) {
		this.nodePare = nodePare;
	}
	
	
	
	public String getType() {
		return "entorn";
	}
	
	
	public long getId() {
		return id;
		}
	
	public String getNomEntorn() {
		return nomEntorn;
	}

	public void setNomEntorn(String nomEntorn) {
		this.nomEntorn = nomEntorn;
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
