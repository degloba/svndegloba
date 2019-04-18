package com.degloba.pubSub;

import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import java.io.IOException;



public class PubSubPublish  {

	public void Publish(String message) {
		
	    Publisher publisher = this.publisher;
	    
	    try {
	      String topicId = System.getenv("PUBSUB_TOPIC");
	      
	      // create a publisher on the topic
	      if (publisher == null) {
	        ProjectTopicName topicName = ProjectTopicName.newBuilder()
	            .setProject(ServiceOptions.getDefaultProjectId())
	            .setTopic(topicId)
	            .build();
	        
	        publisher = Publisher.newBuilder(topicName).build();
	      }
	      // construct a pubsub message from the payload
	      PubsubMessage pubsubMessage =
	          PubsubMessage.newBuilder().setData(ByteString.copyFromUtf8(message)).build();
	
	      publisher.publish(pubsubMessage);
	    
		 } catch (IOException e) {
	 }
	
  }

  private Publisher publisher;

  public PubSubPublish() { }

  PubSubPublish(Publisher publisher) {
    this.publisher = publisher;
  }
}
