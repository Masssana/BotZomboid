package com.develop.project.botzomboid;

import com.develop.project.botzomboid.ifaces.TextSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:commands.properties")
public class CommandsHandler {
    private final TextSender textSender;

    @Value("${command.one}")
    private String addPower;
    @Value("${command.two}")
    private String addSpeed;
    @Value("${command.tree}")
    private String respawn;
    @Value("${command.four}")
    private String setNextLevelOfSprinters;

    public void startCommandOnReceivedVote(Long id){
        String answer = "Hello, here is all commands that you can choose: " +
                "\n" + addPower  +
                "\n" + addSpeed +
                "\n" + respawn +
                "\n" + setNextLevelOfSprinters + "\n";
        textSender.sendText(id, answer);
    }
}
