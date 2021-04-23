package me.tvhee.custommotd.bungeecord.listeners;

import me.tvhee.api.bungeecord.chat.ChatUtils;
import me.tvhee.custommotd.bungeecord.util.ConfigFiles;
import me.tvhee.custommotd.bungeecord.util.Messages;
import me.tvhee.plugin.Updater;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PostLogin implements Listener
{
    @EventHandler
    public void onPlayerLogin(PostLoginEvent e)
    {
        ProxiedPlayer player = e.getPlayer();

        if(player.hasPermission("custommotd.updates"))
        {
            if(ConfigFiles.getConfig().getBoolean("plugin.update-check"))
            {
                if(Updater.getInstance().updateAvailable())
                {
                    player.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.updateAvailable).replaceAll("%version%", String.valueOf(Updater.getInstance().getCurrentVersion())).replaceAll("%new-version%", String.valueOf(Updater.getInstance().getNewestVersion()))));
                    player.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.updateAvailable2)));
                    return;
                }
                else
                {
                    if(ConfigFiles.getConfig().getBoolean("plugin.no-update-available-message-on-join"))
                    {
                        player.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.noUpdateAvailable)));
                    }
                    return;
                }
            }
            else
            {
                player.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.updateCheckDeactivated)));
                return;
            }
        }
    }
}
