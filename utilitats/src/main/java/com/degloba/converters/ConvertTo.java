package com.degloba.converters;

/*
 * 
 */
public class ConvertTo {
	
	  public static Object convertir(Class<?> outputType, Object value) {

	    if(Byte.class.equals(outputType)) {
	    	value = ((Byte) value).byteValue(); 
	    }
	    if(Short.class.equals(outputType)) {
	    	value = ((Short) value).shortValue(); 
	    }
	    if(Integer.class.equals(outputType)) {
	    	value = ((Integer) value).intValue(); 
	    }
	    if(Long.class.equals(outputType)) {
	    	value = ((Long) value).longValue(); 
	    }
	    if(Float.class.equals(outputType)) {
	    	value = ((Float) value).floatValue(); 
	    }
	    if(Double.class.equals(outputType)) {
	    	value = ((Double) value).doubleValue(); 
	    }

	    //throw new TypeMismatchException();
	    
	    return value;
		  
	  }
	
	
}
