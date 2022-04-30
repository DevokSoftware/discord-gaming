package com.devok.common.discord.events;

import com.devok.common.models.Character;
import com.devok.common.services.CharacterService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class MessageEvent extends ListenerAdapter {
    @Inject
    private CharacterService characterService;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equals("dv rank")) {
            Character character = characterService.fetchCharacterByUserIdAndServer(event.getAuthor().getId(), event.getGuild().getId());
           if(character != null){
               event.getMessage().getChannel().sendMessage("Level: " + characterService.calculateLevel(character.getExp())).queue();
           }
        }
    }
}
