package me.tvhee.custommotd.bungeecord.updater;

import me.tvhee.api.updater.UpdateChecker;
import me.tvhee.custommotd.bungeecord.CustomMOTDPlugin;

public class Updater
{
    private UpdateChecker updateChecker;
    private static Updater updater = new Updater();
    private CustomMOTDPlugin plugin = CustomMOTDPlugin.getInstance();

    public Updater()
    {
        setInstance(this);
        this.updateChecker = new UpdateChecker(82470, plugin.getDescription().getVersion());
    }

    public static Updater getInstance()
    {
        return updater;
    }

    public static void setInstance(Updater updater)
    {
        Updater.updater = updater;
    }

    public String getCurrentVersion()
    {
        return plugin.getDescription().getVersion();
    }

    public String getNewestVersion()
    {
        return updateChecker.getNewestVersion();
    }

    public boolean updateAvailable()
    {
        if(updateChecker.updateAvailable())
        {
            return true;
        }
        return false;
    }
}
