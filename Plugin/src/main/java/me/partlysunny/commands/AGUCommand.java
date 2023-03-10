package me.partlysunny.commands;

import me.partlysunny.commands.subcommands.AGUSubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AGUCommand implements CommandExecutor {

    public static final String command = "agu";
    public static Map<String, AGUSubCommand> subCommands = new HashMap<>();

    public static void registerSubCommand(AGUSubCommand c) {
        subCommands.put(c.getId(), c);
    }

    public static boolean executeSubCommand(String id, CommandSender exe, String[] args) {
        AGUSubCommand slbSubCommand = subCommands.get(id);
        if (slbSubCommand == null) {
            return false;
        }
        slbSubCommand.execute(exe, args);
        return true;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player p) {
            if (!p.hasPermission(AGUCommand.command + ".admin")) {
                p.sendMessage(ChatColor.RED + "You cannot use this command!");
                return true;
            }
            if (strings.length == 0) {
                executeSubCommand("help", commandSender, new String[]{});
                return true;
            }
            String subCommand = strings[0];
            ArrayList<String> newArgs = new ArrayList<>(Arrays.asList(strings));
            newArgs.remove(0);
            if (!executeSubCommand(subCommand, commandSender, newArgs.toArray(new String[0]))) {
                p.sendMessage(ChatColor.RED + "That command does not exist!");
            }
        }
        return true;
    }

}
