package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic() {
        Map<String, String> configurations = new HashMap<>();
        //DELETE or COMPACT (keeps the most recent)
        configurations.put(TopicConfig.CLEANUP_POLICY_COMPACT, TopicConfig.CLEANUP_POLICY_DELETE);
        //Time inside the topic. After this time, the top is deleted Default=-1 (Not deleted)
        configurations.put(TopicConfig.DELETE_RETENTION_MS_CONFIG, "1000");
        //Maximum size of the segment Default= 1073741824 1GB
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "10485760");
        //Maximum size of the message Default 1MB
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "10000");

        return TopicBuilder.name("oxieva-topic")
                .partitions(3)
                .replicas(2)
                .configs(configurations)
                .build();
    }
}
