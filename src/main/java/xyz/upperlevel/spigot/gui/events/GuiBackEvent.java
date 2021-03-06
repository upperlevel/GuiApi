package xyz.upperlevel.spigot.gui.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import xyz.upperlevel.spigot.gui.Gui;

public class GuiBackEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    @Getter
    @Setter
    private boolean cancelled = false;
    @Getter
    private final Player player;
    @Getter
    private final Gui gui;
    @Getter
    private final Gui oldGui;

    public GuiBackEvent(Player player, Gui gui, Gui oldGui) {
        this.player = player;
        this.gui = gui;
        this.oldGui = oldGui;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
