package com.degloba.hotels.facade.impl.converters;


import org.modelmapper.AbstractConverter;

import com.degloba.hotels.Hotel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author pere
 * 
 * see <a href="https://www.baeldung.com/java-modelmapper-lists">see</a>
 * 
 * exemple de "Type Map and Property Mapping"
 * 
 */
public class HotelListConverter extends AbstractConverter<List<Hotel>, List<String>> {

    @Override
    protected List<String> convert(List<Hotel> hotels) {

        return hotels
                .stream()
                .map(Hotel::getNom)
                .collect(Collectors.toList());
    }
    
}
