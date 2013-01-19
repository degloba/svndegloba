

import java.util.ArrayList;
import java.util.List;

public class report {

	
	private List<registre> registres = new ArrayList();
	
	private int totalTransport;
	private int totalMenjars;
	private int totalHotels;
	private int grandTotal;

	
	
	
	public report() {
		super();
		
		despesa desp11=new despesa("13/02/2008", 135, 325, 100);
		List<despesa> ldesp1=new ArrayList();
		ldesp1.add(desp11);
		
		despesa desp12=new despesa("15/02/2008", 215, 135, 245);
		ldesp1.add(desp12);
		
		registre reg1=new registre("Manresa",ldesp1);
		registres.add(reg1);
		
		despesa desp21=new despesa("20/03/2008", 135, 325, 100);
		List<despesa> ldesp2=new ArrayList();
		ldesp2.add(desp21);
		
		despesa desp22=new despesa("28/03/2008", 115, 85, 85);
		ldesp2.add(desp22);
		
		registre reg2=new registre("Sabadell",ldesp2);
		registres.add(reg2);
		
		// TODO Auto-generated constructor stub
	}

	public List getRegistres() {
		return registres;
	}

	public void setRegistres(List registres) {
		this.registres = registres;
	}

	public int getTotalTransport() {
		
		totalTransport=0;
		
		for(registre reg: registres) {
			
		totalTransport=totalTransport + reg.getTotalMenjars();	
		}		
		return totalTransport;
	}

	public void setTotalTransport(int totalTransport) {
		this.totalTransport = totalTransport;
	}

	public int getTotalMenjars() {
		
		totalMenjars=0;
		
		for(registre reg: registres) {
			
		totalMenjars=totalMenjars + reg.getTotalMenjars();	
		}		

		return totalMenjars;
	}

	public void setTotalMenjars(int totalMenjars) {
		this.totalMenjars = totalMenjars;
	}

	public int getTotalHotels() {
		totalHotels=0;
		
		for(registre reg: registres) {
			
		totalHotels=totalHotels + reg.getTotalHotels();	
		}		
		return totalHotels;
	}

	public void setTotalHotels(int totalHotels) {
		this.totalHotels = totalHotels;
	}

	public int getGrandTotal() {
		grandTotal=0;
		
		for(registre reg: registres) {
			
		grandTotal=grandTotal + reg.getTotal();	
		}		
		
		return grandTotal;
	}

	public void setGrandTotal(int grandTotal) {
		this.grandTotal = grandTotal;
	}

		
	
}

