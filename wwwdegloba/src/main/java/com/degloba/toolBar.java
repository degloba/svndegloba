package com.degloba;

import java.util.ArrayList;
import java.util.List;


public class toolBar {
	
	private List<ToolBarItem> freeItems;
	private List<ToolBarItem> toolBarItems;
	
	
	public toolBar() {
		omplirFrameworks();
	}
	
  	
  	
  	private void omplirFrameworks() {

		ToolBarItem item;

		freeItems = new ArrayList<ToolBarItem>();

		
		try {
			
 
    	
/*        if (stmt.execute("SELECT * FROM frameworks")) {
            rs = stmt.getResultSet();
        }
    	
		while (rs.next())
  		{
			item=new ToolBarItem();
			item.setNom(rs.getString("nom"));
			item.setIconURI(rs.getString("icon"));
			item.setFramework(rs.getString("tecnologia"));
			item.setDescripcio(rs.getString("descripcio"));
			freeItems.add(item);
  		}*/
  		
		
		} catch (Exception ex) {
		    // handle any errors

		}
  	}

	public List<ToolBarItem> getFreeItems() {
			return freeItems;
	}

	public void setFreeItems(List<ToolBarItem> freeItems) {
		this.freeItems = freeItems;
		}

	public List<ToolBarItem> getToolBarItems() {
		return toolBarItems;
	}

	public void setToolBarItems(List<ToolBarItem> toolBarItems) {
		
		if (!toolBarItems.isEmpty()) {
			
			}
		this.toolBarItems = toolBarItems;
	}

}
