package com.degloba.domain.messaging;

import java.util.List;

import com.degloba.domain.Message;
import com.degloba.domain.persistence.nosql.googledatastore.lowlevel.IBaseRepository;



/** 
 * @category Repositori de {@link Message}S utilitzant Google Cloud DataStore/Natiu 
 * <ul>
 * <li>
 * Podem guardar un {@link Message} en el repositori
 * </li>
 * <li>
 * Podem recuperar els {@link Message} guardats més recents
 * </li>
 * </ul> 
 */
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
