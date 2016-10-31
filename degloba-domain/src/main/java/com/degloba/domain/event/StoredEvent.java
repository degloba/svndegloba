package com.degloba.domain.event;

import com.degloba.domain.InstanceFactory;

import com.degloba.utils.Assert;
import com.degloba.utils.ObjectSerializer;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id; 

import java.util.Date;

/**
 * Stored in the form of the event.
 */


	/** 
	 * * A simple POJO representing a EventStore 
	 **/ 
	@Document public class StoredEvent { 
		
		 @Id 
		 private String entityId; 
		 
		 private String eventId;       //Event ID, the ID associated with the field event DomainEvent
		 private String typeName;    //Event Type Name
		 private Date occurredOn;    //Event time
		 private String eventBody;   //A string representation of the body with the incident

		 private static ObjectSerializer serializer;
		 

		 protected StoredEvent() {
		    }

		 protected StoredEvent(String typeName, Date occurredOn, String eventBody) {
		        Assert.notNull(occurredOn, "occurredOn is null!");
		        Assert.notEmpty(typeName, "typeName is null or empty!");
		        this.typeName = typeName;
		        this.eventBody = eventBody;
		        this.occurredOn = occurredOn;
		    }

		 private StoredEvent(String typeName, Date occurredOn, String eventBody, String eventId) {
		        this(typeName, occurredOn, eventBody);
		        this.eventId = eventId;
		    }
		    
		 public StoredEvent(String entityId, String eventId, String typeName, Date occurredOn, String eventBody) {
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
	    public static  ObjectSerializer getSerializer() {
	        if (serializer == null) {
	            serializer = InstanceFactory.getInstance(ObjectSerializer.class);
	        }
	        return serializer;
	    }
		

		public void setSerializer(ObjectSerializer serializer) {
			StoredEvent.serializer = serializer;
		}


    public static StoredEvent fromDomainEvent(DomainEvent event) {
        Assert.notNull(event);
        return new StoredEvent(event.getClass().getName(), event.getOccurredOn(),
                getSerializer().serialize(event), event.getId());
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
            StoredEvent typedObject = (StoredEvent) anObject;
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
