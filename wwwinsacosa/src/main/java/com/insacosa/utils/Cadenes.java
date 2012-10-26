package com.insacosa.utils;


public class Cadenes {
	

	 public static String primeraLletraMajuscula(String cadena) {
		 
		 Integer x = cadena.length();
		 String s2 = cadena.substring(0,1).toUpperCase().concat(cadena.toLowerCase().substring(1, x));
		 
		 return s2;
	 }
} 
