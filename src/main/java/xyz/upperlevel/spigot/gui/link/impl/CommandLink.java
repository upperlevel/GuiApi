package xyz.upperlevel.spigot.gui.link.impl;

import org.bukkit.entity.Player;
import xyz.upperlevel.spigot.gui.config.placeholders.PlaceholderValue;
import xyz.upperlevel.spigot.gui.link.Link;

public class CommandLink implements Link {
    private final PlaceholderValue<String> command;

    public CommandLink(PlaceholderValue<String> command) {
        this.command = command;
    }

    public CommandLink(String command) {
        this(PlaceholderValue.strValue(command));
    }

    @Override
    public void run(Player player) {
        player.performCommand(command.get(player));
    }
}
