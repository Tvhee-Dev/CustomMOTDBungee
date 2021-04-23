package me.tvhee.custommotd.bungeecord.commands;

import me.tvhee.api.bungeecord.chat.ChatUtils;

import me.tvhee.custommotd.bungeecord.updater.Updater;
import me.tvhee.custommotd.bungeecord.util.ConfigFiles;
import me.tvhee.custommotd.bungeecord.CustomMOTDPlugin;
import me.tvhee.custommotd.bungeecord.util.MOTDUtils;
import me.tvhee.custommotd.bungeecord.util.Messages;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

import java.io.IOException;

public class CMCommand extends Command
{
    private CustomMOTDPlugin plugin = CustomMOTDPlugin.getInstance();

    public CMCommand()
    {
        super("cm");
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        if(args.length == 0)
        {
            sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.pluginMenu1)));
            sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.pluginMenu2) + plugin.getDescription().getVersion()));
            sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.pluginMenu3)));
            sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.pluginMenu4) + " tvhee"));
            sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.pluginMenu5)));
            return;
        }
        else
        {
            if(args[0].equalsIgnoreCase("help"))
            {
                sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.helpMenu1)));
                sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.helpMenu2)));
                sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.helpMenu3)));
                sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.helpMenu4)));
                sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.helpMenu5)));
                sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.helpMenu6)));
                return;

            }
            else if(args[0].equalsIgnoreCase("reload"))
            {
                if(sender.hasPermission("custommotd.reload"))
                {
                    ConfigFiles.reloadConfig();
                    ConfigFiles.reloadMessagesConfig();

                    try
                    {
                        MOTDUtils.getMotdsAndFaviconsAndHover();
                    }
                    catch(IOException ignored)
                    {

                    }

                    sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.commandReload)));

                    if(ConfigFiles.getConfig().getBoolean("plugin.update-check"))
                    {
                        if(Updater.getInstance().updateAvailable())
                        {
                            sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.updateAvailable).replaceAll("%version%", String.valueOf(Updater.getInstance().getCurrentVersion())).replaceAll("%new-version%", String.valueOf(Updater.getInstance().getNewestVersion()))));
                            sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.updateAvailable2)));
                            return;
                        }
                        else
                        {
                            sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.noUpdateAvailable)));
                            return;
                        }
                    }
                    else
                    {
                        sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.updateCheckDeactivated)));
                        return;
                    }
                }
                else
                {
                    sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.commandNoPermission)));
                    return;
                }
            }
            else if(args[0].equalsIgnoreCase("update"))
            {
                if(sender.hasPermission("custommotd.updates"))
                {
                    if(ConfigFiles.getConfig().getBoolean("plugin.update-check"))
                    {
                        if(Updater.getInstance().updateAvailable())
                        {
                            sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.updateAvailable).replaceAll("%version%", String.valueOf(Updater.getInstance().getCurrentVersion())).replaceAll("%new-version%", String.valueOf(Updater.getInstance().getNewestVersion()))));
                            sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.updateAvailable2)));
                            return;
                        }
                        else
                        {
                            sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.noUpdateAvailable)));
                            return;
                        }
                    }
                    else
                    {
                        sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.updateCheckDeactivated)));
                        return;
                    }
                }
                else
                {
                    sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.commandNoPermission)));
                    return;
                }
            }
            else
            {
                sender.sendMessage(new TextComponent(ChatUtils.format(Messages.igprefix + Messages.commandNotFound)));
                return;
            }
        }
    }
}