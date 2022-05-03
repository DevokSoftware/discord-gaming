package com.devok.common.discord.events;

import com.devok.common.models.Character;
import com.devok.common.services.CharacterService;
import com.devok.games.geoguessr.api.MapillaryService;
import com.devok.games.geoguessr.api.common.AuthenticationService;
import com.devok.games.geoguessr.api.model.TokenRequest;
import com.devok.games.geoguessr.api.model.TokenResponse;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.awt.*;
import java.time.Instant;

public class MessageEvent extends ListenerAdapter {
    @Inject
    private CharacterService characterService;

    @Inject
    private MapillaryService mapillaryService;

    @Inject
    private AuthenticationService authenticationService;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equals("dv rank")) {
            Character character = characterService.fetchCharacterByUserIdAndServer(event.getAuthor().getId(), event.getGuild().getId());
            if (character != null) {
                event.getMessage().getChannel().sendMessage("Level: " + characterService.calculateLevel(character.getExp())).queue();
            }
        } else if (event.getMessage().getContentRaw().equals("dv geo")) {
            //get access Token
            TokenRequest tokenRequest = new TokenRequest();
            tokenRequest.setClient_id(System.getProperty("mapillary.clientId"));
            tokenRequest.setGrant_type("authorization_code");
            tokenRequest.setCode(authenticationService.getMapillaryCode());
            String headerAuth = "OAuth " +System.getProperty("mapillary.clientToken");
            TokenResponse authorized = mapillaryService.getAccessToken(headerAuth, tokenRequest);
            System.out.println(authorized);

        } else if (event.getMessage().getContentRaw().equals("dv profile")) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTimestamp(Instant.now());
            builder.addField(":placard: EXP: 40/100", "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9⬛⬛⬛⬛⬛⬛", true);
            builder.addField(":coin: Coins:", "326", true);
            builder.addBlankField(false);
            builder.addField(":trophy: Trophies:", ":one: :seven:", true);
            builder.addField(":military_medal: Medals:", ":first_place: :first_place: :first_place: :second_place: :second_place:  :third_place: :third_place: :third_place: :third_place: :third_place: :third_place::third_place: :third_place: :third_place: :third_place::third_place: :third_place: :third_place: :third_place: ", true);
            builder.setAuthor("Profile Stats - #user123", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg", "https://www.clipartmax.com/png/full/65-659843_cactus-icons-free-icons-cactus.png");
            builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/c/cb/Investigator.png/revision/latest?cb=20141222203926");
            builder.setColor(Color.ORANGE);
            event.getMessage().getChannel().sendMessageEmbeds(builder.build()).queue();
        }
    }
}
