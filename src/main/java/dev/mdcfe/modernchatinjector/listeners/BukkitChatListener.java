package dev.mdcfe.modernchatinjector.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

// Legacy hook if not using a compatible version of EssentialsX Chat
@SuppressWarnings("deprecation")
public class BukkitChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onChat(final AsyncPlayerChatEvent event) {
        final String format = event.getFormat();
        final String result = PlaceholderAPI.setBracketPlaceholders(event.getPlayer(), format);
        event.setFormat(result);
    }

}
