package de.HiorCraft.nex.commands;

import dev.jorel.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;

public class TestCommand extends CommandAPICommand {
    public TestCommand(String commandName) {
        super(commandName);

        executesPlayer((player, args) -> {

            player.sendMessage(Component.text("Hallo " + player.getName() + "!"));

        });
    }
}
