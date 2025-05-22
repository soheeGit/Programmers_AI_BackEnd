package org.example.bootslackoutgoing.dto;

public record SlackWebhookDTO(
        String token,
        String team_id,
        String team_domain,
        String channel_id,
        String channel_name,
        String timestamp,
        String user_id,
        String user_name,
        String text,
        String trigger_word
) {
}
