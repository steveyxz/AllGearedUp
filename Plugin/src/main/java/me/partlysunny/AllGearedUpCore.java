package me.partlysunny;

import me.partlysunny.commands.AGUCommand;
import me.partlysunny.commands.AGUTabCompleter;
import me.partlysunny.commands.subcommands.CombatKit;
import me.partlysunny.commands.subcommands.HelpSubCommand;
import me.partlysunny.commands.subcommands.MiningKit;
import me.partlysunny.commands.subcommands.RedstoneKit;
import me.partlysunny.gui.SelectGuiManager;
import me.partlysunny.gui.textInput.ChatListener;
import me.partlysunny.util.Util;
import me.partlysunny.version.Version;
import me.partlysunny.version.VersionManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static me.partlysunny.commands.AGUCommand.command;

public final class AllGearedUpCore extends JavaPlugin {

    private static VersionManager manager;

    public static VersionManager manager() {
        return manager;
    }

    public static void reload() {
    }

    @Override
    public void onEnable() {
        //Get version
        Version v = new Version(this.getServer().getVersion());
        ConsoleLogger.console("Enabling AllGearedUp...");
        //Load modules (currently not used)
        manager = new VersionManager(this);
        manager.checkServerVersion();
        try {
            manager.load();
        } catch (ReflectiveOperationException e) {
            ConsoleLogger.error("This version (" + v.get() + ") is not supported by AllGearedUp!", "Shutting down plugin...");
            setEnabled(false);
            return;
        }
        manager.enable();
        //Copy in default files if not existent
        try {
            initDefaults();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Register subcommands
        registerCommands();
        registerListeners();
        reload();
        registerGuis();
        ConsoleLogger.console("Enabled AllGearedUp on version " + v.get());
    }

    @Override
    public void onDisable() {
        if (manager != null) {
            manager.disable();
        }
        ConsoleLogger.console("Disabling AllGearedUp...");
    }

    private void registerGuis() {
        SelectGuiManager.init();
    }

    private void registerCommands() {
        //Register all sub commands here
        AGUCommand.registerSubCommand(new HelpSubCommand());
        AGUCommand.registerSubCommand(new CombatKit());
        AGUCommand.registerSubCommand(new RedstoneKit());
        AGUCommand.registerSubCommand(new MiningKit());
        PluginCommand mainCommand = getCommand(command);
        if (mainCommand == null) {
            ConsoleLogger.error("Main command doesn't exist! Check plugin.yml for more info");
            return;
        }
        mainCommand.setExecutor(new AGUCommand());
        mainCommand.setTabCompleter(new AGUTabCompleter());
    }

    private void initDefaults() throws IOException {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        //Initialise default config
        copyFileWithName("config.yml");
    }

    private void copyFileWithName(String key) throws IOException {
        File f = getDataFolder();
        if (!f.exists()) {
            f.mkdir();
        }
        CodeSource src = AllGearedUpCore.class.getProtectionDomain().getCodeSource();
        if (src != null) {
            URL jar = src.getLocation();
            ZipInputStream zip = new ZipInputStream(jar.openStream());
            while (true) {
                ZipEntry e = zip.getNextEntry();
                if (e == null)
                    break;
                String name = e.getName();
                if (name.equals(key)) {
                    File destination = new File(f + "/" + key);
                    Util.copy(name, destination);
                }
            }
        }
    }

    private void copyFolderFromInToOut(String key) throws IOException {
        File f = new File(getDataFolder(), key);
        if (!f.exists()) {
            f.mkdir();
        }
        CodeSource src = AllGearedUpCore.class.getProtectionDomain().getCodeSource();
        if (src != null) {
            URL jar = src.getLocation();
            ZipInputStream zip = new ZipInputStream(jar.openStream());
            while (true) {
                ZipEntry e = zip.getNextEntry();
                if (e == null)
                    break;
                String name = e.getName();
                if (name.startsWith(key + "/") && !name.equals(key + "/")) {
                    File destination = new File(f + "/" + name.substring(key.length() + 1));
                    Util.copy(name, destination);
                }
            }
        }
    }

    private void registerListeners() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ChatListener(), this);
    }
}
