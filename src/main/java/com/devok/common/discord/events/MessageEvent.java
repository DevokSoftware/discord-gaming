package com.devok.common.discord.events;

import com.devok.common.models.Character;
import com.devok.common.services.CharacterService;
import com.devok.games.geoguessr.api.common.ImageService;
import com.devok.games.geoguessr.api.model.mapillary.ImageList;
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
    private ImageService imageService;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equals("dv rank")) {
            Character character = characterService.fetchCharacterByUserIdAndServer(event.getAuthor().getId(), event.getGuild().getId());
            if (character != null) {
                event.getMessage().getChannel().sendMessage("Level: " + characterService.calculateLevel(character.getExp())).queue();
            }
        } else if (event.getMessage().getContentRaw().equals("dv geo")) {
            ImageList images = imageService.getRandomImage();
            if (!images.getImages().isEmpty()) {
                EmbedBuilder builder = new EmbedBuilder();
                builder.setThumbnail(images.getImages().get(0).getImageUrl());
                event.getMessage().getChannel().sendMessage(images.getImages().get(0).getImageUrl()).queue();

                String address = imageService.getAddressCoordinates(images.getImages().get(0).getGeometry().getCoordinates());
                event.getMessage().getChannel().sendMessage(address).queue();

            }
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
