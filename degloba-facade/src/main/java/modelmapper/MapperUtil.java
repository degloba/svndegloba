package modelmapper;

import org.modelmapper.ModelMapper;

import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @category Utilitat que utilitzem per mapejar una llista de DTOs en una llista d'entitats
 * 
 * @author pere
 *
 */
@NoArgsConstructor
public class MapperUtil {
	
	private static ModelMapper modelMapper = new ModelMapper();


    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {

        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
