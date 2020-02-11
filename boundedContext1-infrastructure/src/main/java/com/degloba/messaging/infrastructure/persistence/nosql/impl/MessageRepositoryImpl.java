package com.degloba.messaging.infrastructure.persistence.nosql.impl;

import com.degloba.domain.Message;
import com.degloba.domain.messaging.IMessageRepository;
import com.degloba.persistence.nosql.impl.googleDatastore.api.lowlevel.BaseRepository;
import com.google.cloud.datastore.Datastore;

import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery;
import com.google.cloud.datastore.Transaction;

import java.util.ArrayList;
import java.util.List;

/** 
 * @category Repositori de {@link Message}s utilitzant la implementació Google Cloud Datastore/Natiu 
 * 
 */
public class MessageRepositoryImpl extends BaseRepository 
		implements IMessageRepository {

  public MessageRepositoryImpl(Datastore datastore, Transaction transaction) {
		super(datastore, transaction);
		// TODO Auto-generated constructor stub
	}


  /**
   * @category els {@link Message} es guarden en el datastore amb el tipus d'entitat anomenat "messages"
   */
  private String messagesKind = "messages";
  private KeyFactory keyFactory = getDatastoreInstance().newKeyFactory().setKind(messagesKind);

  /**
   * @category sobrecarrega un mètode de guardar un {@link Message}
   */
  @Override
  public void saveMessage(Message message) {

    Datastore datastore = getDatastoreInstance();
    Key key = datastore.allocateId(keyFactory.newKey());

    Entity.Builder messageEntityBuilder = Entity.newBuilder(key).set("messageId", message.getMessageId());

    if (message.getData() != null) {
      messageEntityBuilder = messageEntityBuilder.set("data", message.getData());
    }

    if (message.getPublishTime() != null) {
      messageEntityBuilder = messageEntityBuilder.set("publishTime", message.getPublishTime());
    }
    datastore.put(messageEntityBuilder.build());
  }

  // Recupera una llista de {@link Message}S del {@link Datastore}
  @Override
  public List<Message> retrieveMessages(int limit) {
    
    Datastore datastore = getDatastoreInstance();
    Query<Entity> query =
        Query.newEntityQueryBuilder()
            .setKind(messagesKind)
            .setLimit(limit)
            .addOrderBy(StructuredQuery.OrderBy.desc("publishTime"))
            .build();
    QueryResults<Entity> results = datastore.run(query);

    List<Message> messages = new ArrayList<>();
    while (results.hasNext()) {
      Entity entity = results.next();
      Message message = new Message(entity.getString("messageId"));
      String data = entity.getString("data");
      if (data != null) {
        message.setData(data);
      }
      String publishTime = entity.getString("publishTime");
      if (publishTime != null) {
        message.setPublishTime(publishTime);
      }
      messages.add(message);
    }
    return messages;
  }


}
