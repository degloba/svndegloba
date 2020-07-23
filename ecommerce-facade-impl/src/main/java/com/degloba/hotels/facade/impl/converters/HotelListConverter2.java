package com.degloba.hotels.facade.impl.converters;


import org.modelmapper.AbstractConverter;


import com.degloba.hotels.Hotel;
import com.degloba.hotels.HotelDto;
import com.degloba.hotels.HotelListDto;

import modelmapper.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 
 * @author pere
 * 
 * @category Retorna la llista {@link HotelListDto} a partir d'una llista {@link HotelList}
 * 
 * see <a href="https://www.baeldung.com/java-modelmapper-lists">see</a>
 * 
 */
public class HotelListConverter2 extends AbstractConverter<List<Hotel>, List<HotelDto>> {
	
	
	  @Override protected List<HotelDto> convert(List<Hotel> hotels) {
  		  return MapperUtil.mapList(hotels, HotelDto.class);
	  }
	 
    
}
