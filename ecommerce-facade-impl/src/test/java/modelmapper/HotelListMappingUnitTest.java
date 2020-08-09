package modelmapper;

import org.assertj.core.api.AssertFactory;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration.AccessLevel;

import com.degloba.hotels.Hotel;
import com.degloba.hotels.HotelDto;
import com.degloba.hotels.HotelList;
import com.degloba.hotels.HotelListDto;
import com.degloba.hotels.facade.impl.converters.HotelListConverter;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;   // https://junit.org/junit5/docs/current/user-guide/#writing-tests-assertions

/**
 * 
 * @author pere
 *
 * @category Test de mapeig llista objectes DTO versus llista objectes d'entitats 
 * 
 * see <a href="https://github.com/eugenp/tutorials/blob/master/java-collections-conversions-2/src/main/java/com/baeldung/modelmapper/UsersListConverter.java">see</a>
 */
public class HotelListMappingUnitTest {
	
    private ModelMapper modelMapper;
    private List<Hotel> hotels;

    @BeforeEach
    public void init() {

        modelMapper = new ModelMapper();
        
        modelMapper.getConfiguration()
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(AccessLevel.PRIVATE);

        
        TypeMap<HotelList, HotelListDto> typeMap = modelMapper.createTypeMap(HotelList.class, HotelListDto.class);

        typeMap.addMappings(mapper -> mapper.using(new HotelListConverter())
                .map(HotelList::getHotels, HotelListDto::setHotels));

        hotels = new ArrayList();
        hotels.add(new Hotel("b100", "hote1", "user1@baeldung.com", "111-222", "4"));
        hotels.add(new Hotel("b101", "hotel2", "user2@baeldung.com", "111-333", "4"));
        hotels.add(new Hotel("b102", "hotel3", "user3@baeldung.com", "111-444", "3"));

    }

    @Test
    @DisplayName("Should contain a correct element")
    public void whenInteger_thenMapToCharacter() {

        List<Integer> integers = new ArrayList<Integer>();

        integers.add(1);
        integers.add(2);
        integers.add(3);

        List<Character> characters = modelMapper.map(integers, new TypeToken<List<Character>>() {
        }.getType());

        assertThat(characters,  hasItems('1', '2'));
       
    }


	/*
	 * @Test
	 * 
	 * @DisplayName("Should contain a correct element") public void
	 * givenUsersList_whenUseGenericType_thenMapToUserDTO() {
	 * 
	 * // Mapping lists using custom (generic) type mapping
	 * 
	 * List<HotelDto> userDtoList = MapperUtil.mapList(hotels, HotelDto.class);
	 * 
	 * assertThat(userDtoList, Matchers.<HotelDto>hasItem(
	 * Matchers.both(hasProperty("userId", equalTo("b100")))
	 * .and(hasProperty("email", equalTo("user1@baeldung.com")))
	 * .and(hasProperty("username", equalTo("user1")))));
	 * 
	 * 
	 * }
	 */

	/*
	 * @Test
	 * 
	 * @DisplayName("Should contain a correct element") public void
	 * givenUsersList_whenUseConverter_thenMapToUsernames() {
	 * 
	 * // Mapping lists using property mapping and converter
	 * 
	 * HotelList userList = new HotelList(); userList.setHotels(hotels);
	 * HotelListDto dtos = new HotelListDto(); modelMapper.map(userList, dtos);
	 * 
	 * assertThat(dtos.getHotels(), hasItems("user1", "user2", "user3"));
	 * 
	 * }
	 */
}
