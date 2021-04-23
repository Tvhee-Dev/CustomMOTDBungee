package me.tvhee.custommotd.bungeecord.listeners;

import me.tvhee.api.bungeecord.chat.ChatUtils;
import me.tvhee.custommotd.bungeecord.util.HashMaps;
import me.tvhee.custommotd.bungeecord.util.MOTDUtils;

import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerListPing implements Listener
{
    @EventHandler
    public void onServerPing(ProxyPingEvent e)
    {
        final ServerPing ping = e.getResponse();
        String host = e.getConnection().getVirtualHost().getHostString().toLowerCase();

        BaseComponent component = null;

        if(HashMaps.motds.get(host) != null)
        {
            final String motd = HashMaps.motds.get(host);
            component = new TextComponent(ChatUtils.format(motd));
        }
        else if(HashMaps.motds.get("default") != null)
        {
            component = new TextComponent(ChatUtils.format(HashMaps.motds.get("default")));
        }

        Favicon favicon = null;
        if(HashMaps.favicons.get(host) != null)
        {
            favicon = HashMaps.favicons.get(host);
        }
        else if(HashMaps.favicons.get("default") != null)
        {
            favicon = HashMaps.favicons.get("default");
        }

        if(HashMaps.hovers.get(host) != null)
        {
            ping.getPlayers().setSample(MOTDUtils.sendHoverToPlayer(HashMaps.hovers.get(host)));
        }
        else if(HashMaps.hovers.get("default") != null)
        {
            ping.getPlayers().setSample(MOTDUtils.sendHoverToPlayer(HashMaps.hovers.get("default")));
        }

        if(component != null)
        {
            ping.setDescriptionComponent(component);
        }

        if(favicon != null)
        {
            ping.setFavicon(favicon);
        }

        e.setResponse(ping);
    }
}
