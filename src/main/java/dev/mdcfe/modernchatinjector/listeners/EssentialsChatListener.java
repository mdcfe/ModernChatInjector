package dev.mdcfe.modernchatinjector.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.essentialsx.api.v2.events.chat.GlobalChatEvent;
import net.essentialsx.api.v2.events.chat.LocalChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

// Hook for EssentialsX Chat versions 2.20+
public class EssentialsChatListener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onGlobalChat(final GlobalChatEvent event) {
        final String format = event.getFormat();
        final String result = PlaceholderAPI.setBracketPlaceholders(event.getPlayer(), format);
        event.setFormat(result);
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onLocalChat(final LocalChatEvent event) {
        final String format = event.getFormat();
        final String result = PlaceholderAPI.setBracketPlaceholders(event.getPlayer(), format);
        event.setFormat(result);
    }
}
