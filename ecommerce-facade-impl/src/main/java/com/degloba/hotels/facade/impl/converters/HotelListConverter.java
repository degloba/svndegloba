package com.degloba.hotels.facade.impl.converters;


import org.modelmapper.AbstractConverter;


import com.degloba.hotels.Hotel;
import com.degloba.hotels.HotelDto;
import com.degloba.hotels.HotelListDto;

import modelmapper.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

/**
 * 
 * @author pere
 * 
 * @category Retorna els valors de la propietat Nom de la classe {@link Hotel} partir d'una
 * llista d'entitats {@link Hotel}
 * 
 * see <a href="https://www.baeldung.com/java-modelmapper-lists">see</a>
 * 
 * exemple de "Type Map and Property Mapping"
 * 
 */
public class HotelListConverter extends AbstractConverter<List<Hotel>, List<String>> {
	
	
	  @Override protected List<String> convert(List<Hotel> hotels) {
	  
	  return hotels 	
			  .stream() 
			  .map(Hotel::getNom) .collect(Collectors.toList()); 
	  }
	 
    
}
