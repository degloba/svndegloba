
import java.util.List;

import com.degloba.domain.Message;
import com.degloba.persistence.nosql.googleDatastore.api.lowlevel.IBaseRepository;


/** Interfície : Repositori missatges utilitzant Google Cloud DataStore/Natiu */
public interface IMessageRepository extends IBaseRepository {

  /** Save message to persistent storage. */
  void save(Message message);

  /**
   * Retrieve most recent stored messages.
   * @param limit number of messages
   * @return list of messages
   */
  List<Message> retrieve(int limit);
}
