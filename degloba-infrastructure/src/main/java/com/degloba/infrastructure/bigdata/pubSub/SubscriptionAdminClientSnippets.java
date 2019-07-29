package com.degloba.infrastructure.bigdata.pubSub;

import com.google.cloud.Identity;
import com.google.cloud.Role;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient.ListSubscriptionsPagedResponse;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.iam.v1.Binding;
import com.google.iam.v1.Policy;
import com.google.iam.v1.TestIamPermissionsResponse;
import com.google.pubsub.v1.ListSubscriptionsRequest;
import com.google.pubsub.v1.ProjectName;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PushConfig;
import com.google.pubsub.v1.Subscription;
import java.util.LinkedList;
import java.util.List;

/** Aquesta classe conté un numero de fragments de codi per la interficie {@link SubscriptionAdminClient}. 
 * 
 * https://github.com/googleapis/google-cloud-java/tree/master/google-cloud-examples/src/main/java/com/google/cloud/examples/pubsub/snippets 
 */
public class SubscriptionAdminClientSnippets {

  private final String projectId;

  public SubscriptionAdminClientSnippets() {
    this.projectId = ServiceOptions.getDefaultProjectId();
  }

  public String getProjectId() {
    return projectId;
  }

  /**
   * 
   * @param topicId
   * @param subscriptionId
   * @return
   * @throws Exception
   * 
   * @category crea una subscripció per a un {@link topic}.
   */
  public Subscription createSubscription(String topicId, String subscriptionId) throws Exception {
    // [START pubsub_create_pull_subscription]
    try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
    	
      // eg. projectId = "my-test-project", topicId = "my-test-topic"
      ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
      // eg. subscriptionId = "my-test-subscription"
      ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);
      // create a pull subscription with default acknowledgement deadline
      Subscription subscription = subscriptionAdminClient.createSubscription(
              subscriptionName, topicName, PushConfig.getDefaultInstance(), 0);
      return subscription;
    }
    // [END pubsub_create_pull_subscription]
  }

  /**
   * 
   * @param topicId
   * @param subscriptionId
   * @param endpoint
   * @return
   * @throws Exception
   * 
   * @category crea una subscripció amb un push endpoint.
   */
  public Subscription createSubscriptionWithPushEndpoint(String topicId, String subscriptionId, String endpoint) throws Exception {
    // [START pubsub_create_push_subscription]
    try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
      ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
      ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);

      // eg. endpoint = "https://my-test-project.appspot.com/push"
      PushConfig pushConfig = PushConfig.newBuilder().setPushEndpoint(endpoint).build();

      // acknowledgement deadline in seconds for the message received over the push endpoint
      int ackDeadlineInSeconds = 10;

      Subscription subscription = subscriptionAdminClient.createSubscription(
              subscriptionName, topicName, pushConfig, ackDeadlineInSeconds);
      return subscription;
    }
    // [END pubsub_create_push_subscription]
  }

  /**
   * 
   * @param subscriptionId
   * @param endpoint
   * @throws Exception
   * 
   * substitueix la configuració push d’una subscripció, establint el push endpoint.
   */
  public void replacePushConfig(String subscriptionId, String endpoint) throws Exception {
    // [START pubsub_update_push_configuration]
    try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
      ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);
      PushConfig pushConfig = PushConfig.newBuilder().setPushEndpoint(endpoint).build();
      subscriptionAdminClient.modifyPushConfig(subscriptionName, pushConfig);
    }
    // [END pubsub_update_push_configuration]
  }

  /** Llista {@link Subscription}S */
  public ListSubscriptionsPagedResponse listSubscriptions() throws Exception {
    // [START pubsub_list_subscriptions]
    try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
      ListSubscriptionsRequest listSubscriptionsRequest =
          ListSubscriptionsRequest.newBuilder()
              .setProject(ProjectName.of(projectId).toString())
              .build();
      ListSubscriptionsPagedResponse response = subscriptionAdminClient.listSubscriptions(listSubscriptionsRequest);
      Iterable<Subscription> subscriptions = response.iterateAll();
      for (Subscription subscription : subscriptions) {
        // do something with the subscription
    	//TODO
    	  
    	  
    	  
      }
      return response;
    }
    // [END pubsub_list_subscriptions]
  }

  /** Esborra una  {@link Subscription} */
  public ProjectSubscriptionName deleteSubscription(String subscriptionId) throws Exception {
    // [START pubsub_delete_subscription]
    try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
      ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);
      subscriptionAdminClient.deleteSubscription(subscriptionName);
      return subscriptionName;
    }
    // [END pubsub_delete_subscription]
  }

  /** Retorna una {@link com.google.iam.v1.Policy} d'una {@link Subscription}  */
  public Policy getSubscriptionPolicy(String subscriptionId) throws Exception {
    // [START pubsub_get_subscription_policy]
    try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
      ProjectSubscriptionName subscriptionName =
          ProjectSubscriptionName.of(projectId, subscriptionId);
      Policy policy = subscriptionAdminClient.getIamPolicy(subscriptionName.toString());
      if (policy == null) {
        // subscription was not found
      }
      return policy;
    }
    // [END pubsub_get_subscription_policy]
  }

  /** Substitueix una {@link com.google.iam.v1.Policy} d'una {@link Subscription} */
  public Policy replaceSubscriptionPolicy(String subscriptionId) throws Exception {
    // [START pubsub_set_subscription_policy]
    try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
      ProjectSubscriptionName subscriptionName =
          ProjectSubscriptionName.of(projectId, subscriptionId);
      Policy policy = subscriptionAdminClient.getIamPolicy(subscriptionName.toString());
      // Create a role => members binding
      Binding binding =
          Binding.newBuilder()
              .setRole(Role.viewer().toString())
              .addMembers(Identity.allAuthenticatedUsers().toString())
              .build();
      // Update policy
      Policy updatedPolicy = policy.toBuilder().addBindings(binding).build();

      updatedPolicy =
          subscriptionAdminClient.setIamPolicy(subscriptionName.toString(), updatedPolicy);
      return updatedPolicy;
    }
    // [END pubsub_set_subscription_policy]
  }

  /** testeja si el the caller  the provided permissions on a subscription. */
  public TestIamPermissionsResponse testSubscriptionPermissions(String subscriptionId)
      throws Exception {
    // [START pubsub_test_subscription_permissions]
    try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
      List<String> permissions = new LinkedList<>();
      permissions.add("pubsub.subscriptions.get");
      ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);
      TestIamPermissionsResponse testedPermissions = topicAdminClient.testIamPermissions(subscriptionName.toString(), permissions);
      return testedPermissions;
    }
    // [END pubsub_test_subscription_permissions]
  }

  /** retorna una subscripció a partir del seu id. */
  public Subscription getSubscription(String subscriptionId) throws Exception {
    // [START pubsub_get_subscription]
    try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
      ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);
      Subscription subscription = subscriptionAdminClient.getSubscription(subscriptionName);
      return subscription;
    }
    // [END pubsub_get_subscription]
  }
}
