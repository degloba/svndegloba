package com.degloba.domain.persistence.nosql.mongodb;



import org.springframework.data.mongodb.core.mapping.Document;

import com.degloba.domain.event.DomainEvent;
import com.degloba.event.persistence.IStoredDomainEvent;
import com.degloba.ioc.spring.InstanceFactory;
import com.degloba.utils.Assert;
import com.degloba.utils.IObjectSerializer;

import org.springframework.data.annotation.Id;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;


	/** 
	 * @author degloba
	 * 
	 * @category Representa l'entitat de persistencia associada a un event de domini (persistit amb MongoDB)</br>
	 * Utilitzem Spring Data per declarar-lo com un {@link Document}
	 **/ 
	@Document public class StoredDomainEventMongoDb implements IStoredDomainEvent { 
		
		 @Id 
		 private String entityId; 
		 
		 private String eventId;     //Event ID, the ID associated with the field event DomainEvent
		 private String typeName;    //Event Type Name
		 private Date occurredOn;    //Event time
		 private String eventBody;   //A string representation of the body with the incident

		 @Inject
		 private static IObjectSerializer serializer;
		 

		 protected StoredDomainEventMongoDb() {
		    }

		 protected StoredDomainEventMongoDb(String typeName, Date occurredOn, String eventBody) {
		        Assert.notNull(occurredOn, "occurredOn is null!");
		        Assert.notEmpty(typeName, "typeName is null or empty!");
		        this.typeName = typeName;
		        this.eventBody = eventBody;
		        this.occurredOn = occurredOn;
		    }

		 private StoredDomainEventMongoDb(String typeName, Date occurredOn, String eventBody, String eventId) {
		        this(typeName, occurredOn, eventBody);
		        this.eventId = eventId;
		    }
		    
		 public StoredDomainEventMongoDb(String entityId, String eventId, String typeName, Date occurredOn, String eventBody) {
			super();
			this.entityId = entityId;
			this.eventId = eventId;
			this.typeName = typeName;
			this.occurredOn = occurredOn;
			this.eventBody = eventBody;
		}

		public String getEntityId() {
			return entityId;
		}

		public void setEntityId(String entityId) {
			this.entityId = entityId;
		}

		
		public String getEventId() {
			return eventId;
		}

		public void setEventId(String eventId) {
			this.eventId = eventId;
		}

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public Date getOccurredOn() {
			return occurredOn;
		}

		public void setOccurredOn(Date occurredOn) {
			this.occurredOn = occurredOn;
		}

		public String getEventBody() {
			return eventBody;
		}

		public void setEventBody(String eventBody) {
			this.eventBody = eventBody;
		}

		/*public static ObjectSerializer getSerializer() {
			return serializer;
		}*/
	    public static  IObjectSerializer getSerializer() {
	        if (serializer == null) {
	            serializer = InstanceFactory.getInstance(IObjectSerializer.class);
	        }
	        return serializer;
	    }
		

		public void setSerializer(IObjectSerializer serializer) {
			StoredDomainEventMongoDb.serializer = serializer;
		}


    public static StoredDomainEventMongoDb fromDomainEvent(DomainEvent event) {
        Assert.notNull(event);
        try {
			return new StoredDomainEventMongoDb(event.getClass().getName(), event.getOccurredOn(),
			        getSerializer().serialize(event), event.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }

    @SuppressWarnings("unchecked")
	public <T extends DomainEvent> T toDomainEvent() {
        Class<T> domainEventClass = null;

        try {
            domainEventClass = (Class<T>) Class.forName(this.getTypeName());
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Class load error, because: "
                            + e.getMessage());
        }
        return getSerializer().deserialize(eventBody, domainEventClass);
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
        	StoredDomainEventMongoDb typedObject = (StoredDomainEventMongoDb) anObject;
            equalObjects = this.eventId == typedObject.eventId;
        }

        return equalObjects;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
                + (1237 * 233)
                        + this.eventId.hashCode();

        return hashCodeValue;
    }

    @Override
    public String toString() {
        return "StoredEvent [eventBody=" + eventBody + ", eventId=" + eventId + ", occurredOn=" + occurredOn + ", typeName="
                + typeName + "]";
    }


}
