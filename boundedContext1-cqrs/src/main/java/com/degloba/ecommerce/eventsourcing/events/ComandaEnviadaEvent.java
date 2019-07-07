package com.degloba.ecommerce.eventsourcing.events;

public  class ComandaEnviadaEvent {
		 
	    private final String orderId;

		public ComandaEnviadaEvent(String orderId2) {
			// TODO Auto-generated constructor stub
			this.orderId = orderId2;
		}

		public String getOrderId() {
			return orderId;
		} 
	 
	    // default constructor, getters, equals/hashCode and toString 
	    
}
