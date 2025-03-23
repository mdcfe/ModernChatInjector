package dev.mdcfe.modernchatinjector;

import dev.mdcfe.modernchatinjector.listeners.BukkitChatListener;
import dev.mdcfe.modernchatinjector.listeners.EssentialsChatListener;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class ModernChatInjector extends JavaPlugin {

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            final String papiVersion = getPluginVersion("PlaceholderAPI");
            getLogger().log(Level.INFO, "Using PlaceholderAPI version {0}", papiVersion);
        } else {
            // Should never get here because we hard depend on PAPI
            throw new IllegalStateException("This plugin requires PlaceholderAPI to be installed.");
        }

        if (tryHookEssChat()) {
            return;
        }

        if (tryHookBukkitChat()) {
            return;
        }

        throw new IllegalStateException("No suitable hooks were found!");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

    private String getPluginVersion(final String pluginName) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
        if (plugin == null) {
            return "";
        }

        return plugin.getDescription().getVersion();
    }

    private boolean tryHookEssChat() {
        final String essChatVersion = getPluginVersion("EssentialsChat");

        if (!Bukkit.getPluginManager().isPluginEnabled("EssentialsChat")) {
            getLogger().warning("EssentialsX Chat was not installed.");
            return false;
        }

        try {
            Class.forName("net.essentialsx.api.v2.events.chat.GlobalChatEvent");
        } catch (ClassNotFoundException e) {
            getLogger().log(Level.WARNING, "Unsupported version {0} of EssentialsX Chat was installed - you should update EssentialsX Chat.", essChatVersion);
            return false;
        }

        Bukkit.getPluginManager().registerEvents(new EssentialsChatListener(), this);
        getLogger().log(Level.INFO, "Hooked EssentialsX Chat version {0}", essChatVersion);
        return true;
    }

    private boolean tryHookBukkitChat() {
        Bukkit.getPluginManager().registerEvents(new BukkitChatListener(), this);
        getLogger().warning("Hooked legacy Bukkit events. Note that this mode is incompatible with modern chat plugins!");
        return true;
    }
}
