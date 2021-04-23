package me.tvhee.custommotd.bungeecord.util;

public class Messages
{
    public static String pluginMenu1 = ConfigFiles.getMessagesConfig().getString("messages.plugin.line1");
    public static String pluginMenu2 = ConfigFiles.getMessagesConfig().getString("messages.plugin.line2");
    public static String pluginMenu3 = ConfigFiles.getMessagesConfig().getString("messages.plugin.line3");
    public static String pluginMenu4 = ConfigFiles.getMessagesConfig().getString("messages.plugin.line4");
    public static String pluginMenu5 = ConfigFiles.getMessagesConfig().getString("messages.plugin.line5");

    public static String helpMenu1 = ConfigFiles.getMessagesConfig().getString("messages.help.line1");
    public static String helpMenu2 = ConfigFiles.getMessagesConfig().getString("messages.help.line2");
    public static String helpMenu3 = ConfigFiles.getMessagesConfig().getString("messages.help.line3");
    public static String helpMenu4 = ConfigFiles.getMessagesConfig().getString("messages.help.line4");
    public static String helpMenu5 = ConfigFiles.getMessagesConfig().getString("messages.help.line5");
    public static String helpMenu6 = ConfigFiles.getMessagesConfig().getString("messages.help.line6");

    public static String commandNoPermission = ConfigFiles.getMessagesConfig().getString("messages.command.no-permission");
    public static String commandNotFound = ConfigFiles.getMessagesConfig().getString("messages.command.not-found");
    public static String commandReload = ConfigFiles.getMessagesConfig().getString("messages.commands.reload");

    public static String igprefix = ConfigFiles.getConfig().getString("plugin.prefix");

    public static String updateAvailable = ConfigFiles.getMessagesConfig().getString("messages.update.available");
    public static String updateAvailable2 = ConfigFiles.getMessagesConfig().getString("messages.update.available_2");
    public static String noUpdateAvailable = ConfigFiles.getMessagesConfig().getString("messages.update.not-available");
    public static String updateCheckDeactivated = ConfigFiles.getMessagesConfig().getString("messages.update.deactivated");
}
