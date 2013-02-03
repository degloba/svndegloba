

public class despesa {
	
	private String dia;
	private int menjars;
	private int hotels;
	private int transport;
	
	
	
	
	public despesa(String dia, int menjars, int hotels, int transport) {
		super();
		this.dia = dia;
		this.menjars = menjars;
		this.hotels = hotels;
		this.transport = transport;
	}
	
	
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public int getMenjars() {
		return menjars;
	}
	public void setMenjars(int menjars) {
		this.menjars = menjars;
	}
	public int getHotels() {
		return hotels;
	}
	public void setHotels(int hotels) {
		this.hotels = hotels;
	}
	public int getTransport() {
		return transport;
	}
	public void setTransport(int transport) {
		this.transport = transport;
	}
	
	
	
	

}
