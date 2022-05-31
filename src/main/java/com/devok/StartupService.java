package com.devok;

import com.devok.games.geoguessr.events.GeoguessrMessageEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

@Startup
@Singleton
public class StartupService {

    @Inject
    private GeoguessrMessageEvent geoguessrMessageEvent;

    @PostConstruct
    public void start() throws LoginException {
        JDABuilder jdaBuilder = JDABuilder.createDefault(System.getProperty("dv8tion.tokenKey"));
        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL);
        JDA jda = jdaBuilder.build();
        jda.addEventListener(geoguessrMessageEvent);
    }
}