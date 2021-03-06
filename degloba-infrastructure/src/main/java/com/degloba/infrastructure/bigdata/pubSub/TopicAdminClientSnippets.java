package com.degloba.infrastructure.bigdata.pubSub;

import com.google.cloud.Identity;
import com.google.cloud.Role;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminClient.ListTopicSubscriptionsPagedResponse;
import com.google.cloud.pubsub.v1.TopicAdminClient.ListTopicsPagedResponse;
import com.google.iam.v1.Binding;
import com.google.iam.v1.Policy;
import com.google.iam.v1.TestIamPermissionsResponse;
import com.google.pubsub.v1.ListTopicSubscriptionsRequest;
import com.google.pubsub.v1.ListTopicsRequest;
import com.google.pubsub.v1.ProjectName;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.Topic;
import java.util.LinkedList;
import java.util.List;

/** This class contains a number of snippets for the {@link TopicAdminClient} interface. */
public class TopicAdminClientSnippets {

  private final String projectId;

  public TopicAdminClientSnippets() {
    this.projectId = ServiceOptions.getDefaultProjectId();
  }

  public String getProjectId() {
    return projectId;
  }

  /** crea un topic a partir del seu nom */
  public Topic createTopic(String topicId) throws Exception {
    // [START pubsub_create_topic]
    try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
      // projectId <=  unique project identifier, eg. "my-project-id"
      // topicId <= "my-topic-id"
      ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
      Topic topic = topicAdminClient.createTopic(topicName);
      return topic;
    }
    // [END pubsub_create_topic]
  }

  /** Llista de {@link Topic}S */
  public ListTopicsPagedResponse listTopics() throws Exception {
    // [START pubsub_list_topics]
    try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
      ListTopicsRequest listTopicsRequest =
          ListTopicsRequest.newBuilder().setProject(ProjectName.format(projectId)).build();
      ListTopicsPagedResponse response = topicAdminClient.listTopics(listTopicsRequest);
      Iterable<Topic> topics = response.iterateAll();
      for (Topic topic : topics) {
        // do something with the topic
      }
      return response;
    }
    // [END pubsub_list_topics]
  }

  /** Llista de {@link Subscription}S d'un {@link Topic} */
  public ListTopicSubscriptionsPagedResponse listTopicSubscriptions(String topicId)
      throws Exception {
    // [START pubsub_list_topic_subscriptions]
    try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
      ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
      ListTopicSubscriptionsRequest request =
          ListTopicSubscriptionsRequest.newBuilder().setTopic(topicName.toString()).build();
      ListTopicSubscriptionsPagedResponse response =
          topicAdminClient.listTopicSubscriptions(request);
      Iterable<String> subscriptionNames = response.iterateAll();
      for (String subscriptionName : subscriptionNames) {
        // do something with the subscription name
      }
      return response;
    }
    // [END pubsub_list_topic_subscriptions]
  }

  /** Esborra {@link Topic} */
  public ProjectTopicName deleteTopic(String topicId) throws Exception {
    // [START pubsub_delete_topic]
    try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
      ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
      topicAdminClient.deleteTopic(topicName);
      return topicName;
    }
    // [END pubsub_delete_topic]
  }

  /** Retorna {@link com.google.iam.v1.Policy} d'un {@link Topic} */
  public Policy getTopicPolicy(String topicId) throws Exception {
    // [START pubsub_get_topic_policy]
    try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
      ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
      Policy policy = topicAdminClient.getIamPolicy(topicName.toString());
      if (policy == null) {
        // topic iam policy was not found
      }
      return policy;
    }
    // [END pubsub_get_topic_policy]
  }

  /** Example of replacing a topic policy. */
  public Policy replaceTopicPolicy(String topicId) throws Exception {
    // [START pubsub_set_topic_policy]
    try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
      String topicName = ProjectTopicName.format(projectId, topicId);
      Policy policy = topicAdminClient.getIamPolicy(topicName);
      // add role -> members binding
      Binding binding =
          Binding.newBuilder()
              .setRole(Role.viewer().toString())
              .addMembers(Identity.allAuthenticatedUsers().toString())
              .build();
      // create updated policy
      Policy updatedPolicy = Policy.newBuilder(policy).addBindings(binding).build();
      updatedPolicy = topicAdminClient.setIamPolicy(topicName, updatedPolicy);
      return updatedPolicy;
    }
    // [END pubsub_set_topic_policy]
  }

  /**
   * Example of testing whether the caller has the provided permissions on a topic. Only viewer,
   * editor or admin/owner can view results of pubsub.topics.get
   */
public TestIamPermissionsResponse testTopicPermissions(String topicId) throws Exception {
    // [START pubsub_test_topic_permissions]
    try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
      List<String> permissions = new LinkedList<>();
      permissions.add("pubsub.topics.get");
      ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
      TestIamPermissionsResponse testedPermissions =
          topicAdminClient.testIamPermissions(topicName.toString(), permissions);
      return testedPermissions;
    }
    // [END pubsub_test_topic_permissions]
  }

  /** Retorna un {@link Topic} a partir del seu nom */
  public Topic getTopic(String topicId) throws Exception {
    // [START pubsub_get_topic]
    try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
      ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
      Topic topic = topicAdminClient.getTopic(topicName);
      return topic;
    }
    // [END pubsub_get_topic]
  }
}
