package com.degloba.vo;
 

import java.io.IOException; 
import java.io.OutputStream; 
import java.io.Serializable; 
import java.util.ArrayList;   


import javax.faces.bean.ManagedBean; 
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.richfaces.event.FileUploadEvent; 
import org.richfaces.model.UploadedFile;   


/**  * @author Ilya Shaikovsky  *   */

@ManagedBean(name="fileUploadBean")
@SessionScoped
public class FileUploadBean implements Serializable { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<UploadedImage> files = new ArrayList<UploadedImage>();   
	
	public void paint(OutputStream stream, Object object) throws IOException {         
		stream.write(getFiles().get((Integer) object).getData()); 
	stream.close();   
	}    
	
	public void listener(FileUploadEvent event) throws Exception {         
		UploadedFile item = event.getUploadedFile();         
		UploadedImage file = new UploadedImage();         
		file.setLength(item.getData().length);         
		file.setName(item.getName());         
		file.setData(item.getData());         
		files.add(file);     
		
		// Afegim al InmobleForm
		FacesContext context = FacesContext.getCurrentInstance(); 
		InmobleForm inmobleForm = (InmobleForm) context.getApplication().evaluateExpressionGet(context, "#{inmobleForm}", InmobleForm.class);
		
		FotoForm fotoForm = new FotoForm();
		fotoForm.setImatge(file.getData());
		fotoForm.setIdInmoble(inmobleForm.getId());
		fotoForm.setDescripcio(file.getName());
		
		inmobleForm.getFotos().add(fotoForm);
		
		
	}       

	public String clearUploadData() {         
		files.clear();         
		return null;     
	}       

	public int getSize() {         
	if (getFiles().size() > 0) {             
	
	return getFiles().size();         
	} 
	else {             
		return 0;         
		}     
	}       

	public long getTimeStamp() {        
		return System.currentTimeMillis();     
	}       
		
	public ArrayList<UploadedImage> getFiles() {         
		return files;   
	}  
	
	public void setFiles(ArrayList<UploadedImage> files) {         
		this.files = files;     
	}   
	
		
}