package com.xbla.slackscheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private SlackService slackService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    public ScheduledTask(SlackService slackService){
        this.slackService = slackService;
    }

    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
         SlackMessage slackMessage = SlackMessage.builder()
                .channel("general")
                .username("Feels Man")
                .text("feels boi every 1 sec")
                .icon_emoji(":feels:")
                .build();
         slackService.sendMessage(slackMessage);
    }
}