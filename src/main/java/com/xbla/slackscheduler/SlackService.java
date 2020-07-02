package com.xbla.slackscheduler;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Getter
@Setter
@Service
public class SlackService {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private String slackWebhook;

    public SlackService(Environment env) {
        this.slackWebhook = env.getProperty("slack.webhook");
    }

    public void sendMessage(SlackMessage message) {
        CloseableHttpClient client = HttpClients.createDefault();
        log.info(slackWebhook);
        HttpPost httpPost = new HttpPost(slackWebhook);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(message);

            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            client.execute(httpPost);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}