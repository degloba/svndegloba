import java.util.List;

import com.degloba.domain.Message;
import com.degloba.persistence.nosql.googleDatastore.api.lowlevel.IBaseRepository;


/** 
 * @category Repositori de {@link Message}S utilitzant Google Cloud DataStore/Natiu 
 * */
public interface IMessageRepository extends IBaseRepository {

  /** Guarda un {@link Message} a un repository */
  void save(Message message);

  /**
   * Recupera el més recents {@link Message}S guardats.
   * @param limit número de {@link Message}
   * @return llista de {@link Message}
   */
  List<Message> retrieve(int limit);
}
