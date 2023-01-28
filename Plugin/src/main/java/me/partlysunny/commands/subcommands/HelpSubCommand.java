package me.partlysunny.commands.subcommands;

import me.partlysunny.commands.AGUCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Collection;

import static me.partlysunny.commands.AGUCommand.command;

public class HelpSubCommand implements AGUSubCommand {
    @Override
    public String getId() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Get a list of all commands.";
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public void execute(CommandSender executor, String[] args) {
        Collection<AGUSubCommand> commands = AGUCommand.subCommands.values();
        executor.sendMessage(ChatColor.YELLOW + "List of commands (run with /" + command + "<command>):");
        for (AGUSubCommand c : commands) {
            executor.sendMessage(ChatColor.WHITE + c.getId() + c.getUsage() + ": " + c.getDescription());
        }
    }
}
