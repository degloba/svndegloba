package com.degloba.infrastructure.bigdata.pubSub;


import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.PushConfig;

/**
 * Crea una {@link Subscription} i un {@link MessageReceiver}
 * treure els missatges de manera asíncrona.
 */
public class CreateSubscriptionAndConsumeMessages {
	
	/**
	 * @category Crea una {@link Subscription} que està definida pel {@link Topic} , un {@link Subscriber}
	 * 
	 * @param projecte
	 * @param nousubscription
	 * @param topicSubscription
	 * @throws Exception
	 */
	 public static void createSubscription(String projecte, String nouSubscription, String topicSubscription) throws Exception {
		 ProjectTopicName topic = CreateTopicAndPublishMessages.createTopic(projecte, topicSubscription);
				 				 
		 ProjectSubscriptionName subscription = ProjectSubscriptionName.of(projecte, nouSubscription);
		 
		 try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
		      subscriptionAdminClient.createSubscription(
		          subscription, topic, PushConfig.getDefaultInstance(), 0);
		    }

		    MessageReceiver receiver =
		        new MessageReceiver() {
		          @Override
		          public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
		            System.out.println("Received message: " + message.getData().toStringUtf8());
		            consumer.ack();
		          }
		        };
		        
		        Subscriber subscriber = null;
		        try {
		          subscriber = Subscriber.newBuilder(subscription, receiver).build();
		          subscriber.addListener(
		              new Subscriber.Listener() {
		                @Override
		                public void failed(Subscriber.State from, Throwable failure) {
		                  // Handle failure. This is called when the Subscriber encountered a fatal error and is
		                  // shutting down.
		                  System.err.println(failure);
		                }
		              },
		              MoreExecutors.directExecutor());
		          subscriber.startAsync().awaitRunning();

		        } finally {
		          if (subscriber != null) {
		            subscriber.stopAsync().awaitTerminated();
		          }
		        }
	 }

}

