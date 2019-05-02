package com.degloba.bigdata.pubSub;

/*
 * Copyright 2016 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


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
	 * Crea una {@link Subscription} que està definida pel {@link Topic} , un {@link Subscriber}
	 * @param projecte
	 * @param nousubscription
	 * @param topicSubscription
	 * @throws Exception
	 */
	 public static void createSubscription(String projecte, String nouSubscription, String topicSubscription) throws Exception {
		 ProjectTopicName topic = CreateTopicAndPublishMessages.createTopic(projecte, topicSubscription);
				 				 
		 ProjectSubscriptionName subscription =
			        ProjectSubscriptionName.of(projecte, nouSubscription);
		 
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

