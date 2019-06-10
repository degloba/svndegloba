import com.degloba.domain.Message;
import com.degloba.persistence.nosql.googleDatastore.api.lowlevel.DatabaseException;
import com.google.cloud.datastore.Batch;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
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
 * @category Repositori de missatges utilitzant la implementació Google Cloud Datastore/Natiu 
 * 
 */
public class MessageRepositoryImpl implements IMessageRepository {

  private static MessageRepositoryImpl instance;

  private String messagesKind = "messages";
  private KeyFactory keyFactory = getDatastoreInstance().newKeyFactory().setKind(messagesKind);

  @Override
  public void save(Message message) {

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

  // Recupera un {@link Message} del {@link Datastore}
  @Override
  public List<Message> retrieve(int limit) {
    
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

  // Recupera un {@ink Datastore}
  private Datastore getDatastoreInstance() {
    return DatastoreOptions.getDefaultInstance().getService();
  }

  private MessageRepositoryImpl() {
  }

  // retrieve a singleton instance
  public static synchronized  MessageRepositoryImpl getInstance() {
    if (instance == null) {
      instance = new MessageRepositoryImpl();
    }
    return instance;
  }

@Override
public <T> void create(String clazz, String keyName) {
	instance.create(clazz, keyName);	
}

@Override
public <T> void update(String clazz, String keyName, String propertyName, String value) throws DatabaseException {	
	instance.update(clazz, keyName, propertyName, value);	
}

@Override
public Entity getById(Class<Entity> clazz, Key id) throws DatabaseException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public <T> Key getKey(Key parent, Class<? extends T> kindClass, long id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public <T> List<T> list(Class<T> clazz) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public <T> void delete(Transaction tx, Key userKey) throws DatabaseException {
	// TODO Auto-generated method stub
	
}

@Override
public void deleteEntityAndAncestors(Transaction tx, Key userKey, String kindAncestor) {
	// TODO Auto-generated method stub
	
}

@Override
public String runInTransaction(String callableResult) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Batch newBatch(String clazz, ArrayList<String> lstKeyString) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Key allocateIdSingle(String clazz) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Key> batchAllocateId(String clazz) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void batchUpdateEntities(String clazz, String keyName1, String keyName2) {
	// TODO Auto-generated method stub
	
}

@Override
public void putSingleEntity(String clazz, String keyName, String propertyName, String value) {
	// TODO Auto-generated method stub
	
}

@Override
public void batchPutEntities(String clazz, String keyName1, String keyName2) {
	// TODO Auto-generated method stub
	
}

@Override
public void batchDeleteEntities(String clazz, String keyName1, String keyName2) {
	// TODO Auto-generated method stub
	
}

@Override
public KeyFactory createKeyFactory() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Entity getEntityWithKey(String clazz, String keyName) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Entity> getEntitiesWithKeys(String clazz, ArrayList<String> lstKeyString) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Entity> fetchEntitiesWithKeys(String clazz, ArrayList<String> lstKeyString) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Entity> runQuery(String kind) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Entity> runQueryOnProperty(String kind, String property, String value) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Entity get(String clazz, String keyName) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Entity> getMultiple(String clazz, String keyName1, String keyName2) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Entity> run(String parentClazz, String clazz, String parentKeyName) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Key commit(String clazz, String propertyName, String value) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Key rollback(String clazz, String propertyName, String value) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Key active(String clazz, String propertyName, String value) {
	// TODO Auto-generated method stub
	return null;
}
}
