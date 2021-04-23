package me.tvhee.custommotd.bungeecord.util;

import me.tvhee.api.bungeecord.chat.ChatUtils;
import me.tvhee.custommotd.bungeecord.CustomMOTDPlugin;

import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ServerPing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MOTDUtils
{
    private static CustomMOTDPlugin plugin = CustomMOTDPlugin.getInstance();

    public static void getMotdsAndFaviconsAndHover() throws IOException
    {
        HashMaps.motds.clear();
        HashMaps.favicons.clear();
        HashMaps.hovers.clear();

        for(final String server : ConfigFiles.getConfig().getSection("servers").getKeys())
        {
            getMotd(server, ConfigFiles.getConfig().getBoolean("servers." + server + ".motd.enabled"));
            getFavicon(server, ConfigFiles.getConfig().getBoolean("servers." + server + ".favicon.enabled"));
            getHover(server, ConfigFiles.getConfig().getBoolean("servers." + server + ".hover.enabled"));
        }
    }

    public static void getMotd(String server, boolean enabled) throws IOException
    {
        String ip = getIp(server);
        if(!enabled)
        {
            HashMaps.motds.put(ip, null);
            return;
        }

        if(ConfigFiles.getConfig().getBoolean("servers." + server + ".motd.center"))
        {
            final String motdCentered = ChatUtils.centerText(ConfigFiles.getConfig().getString("servers." + server + ".motd.line1"), 125) + "§r\n" + ChatUtils.centerText(ConfigFiles.getConfig().getString("servers." + server + ".motd.line2"), 125);
            HashMaps.motds.put(ip, motdCentered);
        }
        else
        {
            final String motdDefault = ConfigFiles.getConfig().getString("servers." + server + ".motd.line1") + "§r\n" + ConfigFiles.getConfig().getString("servers." + server + ".motd.line2");
            HashMaps.motds.put(ip, motdDefault);
        }
    }

    public static void getHover(String server, boolean enabled) throws IOException
    {
        String ip = getIp(server);
        if(!enabled)
        {
            HashMaps.hovers.put(ip, null);
            return;
        }

        List<String> hover = new ArrayList<>();

        if(ConfigFiles.getConfig().getBoolean("servers." + server + ".hover.center"))
        {
            List<String> config = ConfigFiles.getConfig().getStringList("servers." + server + ".hover.text");
            for(int i = 0; i < config.size(); i++)
            {
                hover.add(ChatUtils.centerText(config.get(i), 90));
            }
        }
        else
        {
            hover = ConfigFiles.getConfig().getStringList("servers." + server + ".hover.text");
        }
        HashMaps.hovers.put(ip, hover);
    }

    public static String getIp(String server) throws IOException
    {
        String ip;
        if(!server.equals("default"))
        {
            ip = ConfigFiles.getConfig().getString("servers." + server + ".ip").toLowerCase();
        }
        else
        {
            ip = "default";
        }
        return ip;
    }

    public static void getFavicon(String server, boolean enabled) throws IOException
    {
        String ip = getIp(server);
        if(!enabled)
        {
            HashMaps.favicons.put(ip, null);
            return;
        }

        final String name = ConfigFiles.getConfig().getString("servers." + server + ".favicon.file");
        final File file = new File(plugin.getDataFolder(), "/favicons/" + name);
        BufferedImage image;

        image = ImageIO.read(new File(file.getPath()));

        try
        {
            if(image != null)
            {
                final Favicon favicon = Favicon.create(image);
                HashMaps.favicons.put(ip, favicon);
            }
            else
            {
                plugin.getLogger().info("Error: Can't find image! On image: " + name);
            }
        }
        catch(IllegalArgumentException e)
        {
            plugin.getLogger().info("Error: Your icon must be exactly 64 x 64 pixels! On image: " + name);
        }
    }

    public static ServerPing.PlayerInfo[] sendHoverToPlayer(final List<String> hoverText)
    {
        final int size = hoverText.size();
        final ServerPing.PlayerInfo[] profiles = new ServerPing.PlayerInfo[size];
        for(int i = 0; i < size; ++i)
        {
            profiles[i] = new ServerPing.PlayerInfo(ChatUtils.format(hoverText.get(i)), new UUID(0L, 0L));
        }
        return profiles;
    }
}
