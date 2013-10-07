package com.degloba;

import java.util.ArrayList;
import java.util.List;

public class ObjecteBean {
    private static int IDANTERIOR= -1;  //  per processar inicialment els fills del root de l'arbre
    
    private ArrayList<ObjecteNode> objectRoots;
        	
    private ArrayList<ObjecteNode> objectRootsr[] = new ArrayList[2];
    
    public synchronized ArrayList<ObjecteNode>[] getObjectRoots() {
        if (objectRoots == null) {
              
       	  objectRoots= new ObjecteNode(0,IDANTERIOR,"","", "wizard").getNodes();
       	  objectRootsr[0] = objectRoots;
        	
       	  objectRoots = new ObjecteNode(1,IDANTERIOR,"","", "document").getNodes();
       	  objectRootsr[1] = objectRoots;
        
        }
        
        return objectRootsr;
    }
	

	public List<ObjecteNode>[] getObjectRootsr() {
		return objectRootsr;
	}

	public void setObjectRootsr(ArrayList<ObjecteNode> objectRootsr[]) {
		this.objectRootsr = objectRootsr;
	}

	public ObjecteBean() {
		super();
		// TODO Auto-generated constructor stub
	}
   
}

