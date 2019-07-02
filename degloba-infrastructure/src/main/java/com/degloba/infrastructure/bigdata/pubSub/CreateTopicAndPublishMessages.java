package com.degloba.infrastructure.bigdata.pubSub;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Per crear un {@link com.google.pubsub.v1.Topic} Pub/Sub i publicar missatges en ell de forma assíncrona.
 */
public class CreateTopicAndPublishMessages {

	/**
	 * Crea un {@link Topic}
	 * 
	 * @param projecte
	 * @param noutopic
	 * @return
	 * @throws Exception
	 */
  public static ProjectTopicName createTopic(String projecte, String noutopic) throws Exception {
    ProjectTopicName topic = ProjectTopicName.of(projecte, noutopic);
    try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
     topicAdminClient.createTopic(topic);
    }
    return topic;
  }

  /**
   * Publica una llista de {@link String}S en un {@link Topic}
   * 
   * @param messages
   * @throws Exception
   */
  public static void publishMessages(List<String> messages) throws Exception {
    // [START pubsub_publish]
    ProjectTopicName topicName = ProjectTopicName.of("my-project-id", "my-topic-id");
    Publisher publisher = null;
    List<ApiFuture<String>> messageIdFutures = new ArrayList<>();

    try {
      // Crea una instància {@link Publisher} amb la configuració predeterminada lligada al {@link Topic}
      publisher = Publisher.newBuilder(topicName).build();

      // programa la publicació d'un missatge a la vegada: els missatges es recullen automàticament
      for (String message : messages) {
        ByteString data = ByteString.copyFromUtf8(message);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

        // Un cop publicat, retorna un identificador de missatge assignat pel servidor (únic dins del {@link Topic)
        ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
        messageIdFutures.add(messageIdFuture);
      }
    } finally {
      // espera les sol·licituds de publicació pendents.
      List<String> messageIds = ApiFutures.allAsList(messageIdFutures).get();

      for (String messageId : messageIds) {
        System.out.println("published with message ID: " + messageId);
      }

      if (publisher != null) {
        // Quan ja hem acabat amb el {@link Publisher}, alliberem recursos.
        publisher.shutdown();
        publisher.awaitTermination(1, TimeUnit.MINUTES);
      }
    }
    // [END pubsub_publish]
  }

  /**
   * Publica una llista de {@link String}S en un {@link Topic}
   * Afegeix un Callback per capturar si la publicació ha acabat amb èxit o ha fallat
   * 
   * @param projecte
   * @param topic
   * @param messages
   * @throws Exception
   */
  public static void publishMessagesWithErrorHandler(String projecte, String topic, List<String> messages) throws Exception {
    // [START pubsub_publish_error_handler]
    ProjectTopicName topicName = ProjectTopicName.of(projecte, topic);
    Publisher publisher = null;

    try {
      // Create a publisher instance with default settings bound to the topic
      publisher = Publisher.newBuilder(topicName).build();

      for (final String message : messages) {
        ByteString data = ByteString.copyFromUtf8(message);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

        // Once published, returns a server-assigned message id (unique within the topic)
        ApiFuture<String> future = publisher.publish(pubsubMessage);

        // Add an asynchronous callback to handle success / failure
        ApiFutures.addCallback(
            future,
            new ApiFutureCallback<String>() {

              @Override
              public void onFailure(Throwable throwable) {
                if (throwable instanceof ApiException) {
                  ApiException apiException = ((ApiException) throwable);
                  // details on the API exception
                  System.out.println(apiException.getStatusCode().getCode());
                  System.out.println(apiException.isRetryable());
                }
                System.out.println("Error publishing message : " + message);
              }

              @Override
              public void onSuccess(String messageId) {
                // Once published, returns server-assigned message ids (unique within the topic)
                System.out.println(messageId);
              }
            },
            MoreExecutors.directExecutor());
      }
    } finally {
      if (publisher != null) {
        // When finished with the publisher, shutdown to free up resources.
        publisher.shutdown();
        publisher.awaitTermination(1, TimeUnit.MINUTES);
      }
    }
    // [END pubsub_publish_error_handler]
  }

  public static void main(String... args) throws Exception {
    // createTopic();
    // publishMessages();
  }
}
