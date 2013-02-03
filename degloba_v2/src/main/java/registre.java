

import java.util.List;

public class registre {
	
	private String ciutat;
	private List<despesa> despeses;
	
	private int totalMenjars;
	private int totalHotels;
	private int totalTransport;
	private int total;
	
	
	public registre() {
		super();
		// TODO Auto-generated constructor stub
	}


	public registre(String ciutat,List<despesa> despeses) {
		super();
		this.ciutat = ciutat;
		this.despeses=despeses;
		setTotalMenjars();
		setTotalHotels();
		setTotalTransport();
		setTotal();
	}


	public String getCiutat() {
		return ciutat;
	}


	public void setCiutat(String ciutat) {
		this.ciutat = ciutat;
	}


	public List<despesa> getDespeses() {
		return despeses;
	}


	public void setDespeses(List<despesa> despeses) {
		this.despeses = despeses;
	}


	public int getTotalMenjars() {
	
		return totalMenjars;
	}


	public void setTotalMenjars() {
		
		totalMenjars=0;
		
		for(despesa desp: despeses)
		{
			totalMenjars=totalMenjars + desp.getMenjars(); 
		
		}
	}


	public int getTotalHotels() {
		
		return totalHotels;
	}


	public void setTotalHotels() {
		
		totalHotels=0;
		
		for(despesa desp: despeses)
		{
			totalHotels=totalHotels + desp.getHotels(); 
		
		}
	}


	public int getTotalTransport() {
		
		return totalTransport;
	}


	public void setTotalTransport() {

		totalTransport=0;
		
		for(despesa desp: despeses)
		{
			totalTransport=totalTransport + desp.getTransport(); 
		
		}
		
	}


	public int getTotal() {
		return total;
	}


	public void setTotal() {
		this.total=totalHotels + totalTransport + totalMenjars;
		
	}
	
	
	
}
