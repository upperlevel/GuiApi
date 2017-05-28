package xyz.upperlevel.guiapi.hotbar;

import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.guiapi.impl.link.Link;

public interface HotbarLink {
    Link getAction();
    ItemStack getDisplay();

    static HotbarLink newLink(Link link, ItemStack display) {
        return new HotbarLink() {
            @Override
            public Link getAction() {
                return link;
            }

            @Override
            public ItemStack getDisplay() {
                return display;
            }
        };
    }

    static HotbarLink of(Link link, ItemStack display) {
        return newLink(link, display);
    }
}
