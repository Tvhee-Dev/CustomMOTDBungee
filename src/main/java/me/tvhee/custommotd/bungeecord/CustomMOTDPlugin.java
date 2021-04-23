package me.tvhee.custommotd.bungeecord;

import me.tvhee.api.bungeecord.files.CustomFile;
import me.tvhee.custommotd.bungeecord.commands.CMCommand;
import me.tvhee.custommotd.bungeecord.listeners.PostLogin;
import me.tvhee.custommotd.bungeecord.listeners.ServerListPing;
import me.tvhee.custommotd.bungeecord.util.ConfigFiles;
import me.tvhee.custommotd.bungeecord.util.MOTDUtils;

import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;

public class CustomMOTDPlugin extends Plugin
{
    private static CustomMOTDPlugin instance;

    public void onEnable()
    {
        setInstance(this);
        ConfigFiles.saveDefaultMessagesConfig();
        ConfigFiles.saveDefaultConfig();

        new CustomFile(this, "/favicons/default.png").saveDefaultFile();

        this.getProxy().getPluginManager().registerListener(this, new ServerListPing());
        this.getProxy().getPluginManager().registerListener(this, new PostLogin());
        this.getProxy().getPluginManager().registerCommand(this, new CMCommand());

        try
        {
            MOTDUtils.getMotdsAndFaviconsAndHover();
        }
        catch(IOException ignored)
        {

        }

        this.getLogger().info("has been enabled!");
        this.getLogger().info("made by " + this.getDescription().getAuthor());
    }

    public static void setInstance(CustomMOTDPlugin instance)
    {
        CustomMOTDPlugin.instance = instance;
    }

    public static CustomMOTDPlugin getInstance()
    {
        return CustomMOTDPlugin.instance;
    }

    public void onDisable()
    {
        this.getLogger().info("made by " + this.getDescription().getAuthor());
        this.getLogger().info("has been disabled!");
    }
}