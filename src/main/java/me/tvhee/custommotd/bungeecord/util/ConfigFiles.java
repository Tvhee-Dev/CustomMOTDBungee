package me.tvhee.custommotd.bungeecord.util;

import me.tvhee.api.bungeecord.files.ConfigFile;
import me.tvhee.custommotd.bungeecord.CustomMOTDPlugin;

import net.md_5.bungee.config.Configuration;

public class ConfigFiles
{
    private static CustomMOTDPlugin plugin = CustomMOTDPlugin.getInstance();
    private static ConfigFile messagesConfig = new ConfigFile(plugin, "messages");
    private static ConfigFile config = new ConfigFile(plugin, "config");

    public static Configuration getMessagesConfig()
    {
        return messagesConfig.getConfig();
    }

    public static void saveMessagesConfig()
    {
        messagesConfig.saveConfig();
    }

    public static void saveDefaultMessagesConfig()
    {
        messagesConfig.saveDefaultConfig();
    }

    public static void reloadMessagesConfig()
    {
        messagesConfig.reloadConfig();
    }

    public static Configuration getConfig()
    {
        return config.getConfig();
    }

    public static void saveConfig()
    {
        config.saveConfig();
    }

    public static void saveDefaultConfig()
    {
        config.saveDefaultConfig();
    }

    public static void reloadConfig()
    {
        config.reloadConfig();
    }
}
