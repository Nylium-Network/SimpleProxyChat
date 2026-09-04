package com.beanbeanjuice.simpleproxychat.shared.fluxer;

import com.beanbeanjuice.simpleproxychat.shared.config.Config;
import com.beanbeanjuice.simpleproxychat.shared.config.ConfigKey;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.function.Consumer;

public class FluxerChatHandler extends ListenerAdapter {

    private final Config config;
    private final Consumer<MessageReceivedEvent> sendFromFluxer;

    public FluxerChatHandler(Config config,
                             Consumer<MessageReceivedEvent> sendFromFluxer) {
        this.config = config;
        this.sendFromFluxer = sendFromFluxer;
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getChannel().getId().equalsIgnoreCase(config.get(ConfigKey.CHANNEL_ID).asString())) return;
        if (event.getAuthor().isBot()) return;
        if (!config.get(ConfigKey.DISCORD_CHAT_ENABLED).asBoolean()) return;

        sendFromFluxer.accept(event);
    }

}
