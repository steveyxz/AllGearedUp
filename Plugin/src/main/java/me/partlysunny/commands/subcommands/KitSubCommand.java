package me.partlysunny.commands.subcommands;

import me.partlysunny.util.classes.Gear;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.partlysunny.commands.AGUCommand.command;
import static me.partlysunny.commands.AGUCommand.executeSubCommand;

public abstract class KitSubCommand implements AGUSubCommand {

    @Override
    public String getDescription() {
        return "Gives you the " + getId() + " kit";
    }

    @Override
    public String getUsage() {
        return "/" + command + " " + getId();
    }

    @Override
    public void execute(CommandSender executor, String[] args) {
        if (executor instanceof Player p) {
            getGear().equip(p);
        }
    }

    protected abstract Gear getGear();
}
