package me.partlysunny.commands.subcommands;

import me.partlysunny.util.classes.Gear;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RedstoneKit extends KitSubCommand {
    @Override
    public String getId() {
        return "redstone";
    }

    @Override
    protected Gear getGear() {
        return Gear.GearBuilder.builder()
                .setHotbar(
                        new ItemStack(Material.REDSTONE, 1),
                        new ItemStack(Material.REPEATER, 1),
                        new ItemStack(Material.COMPARATOR, 1),
                        new ItemStack(Material.REDSTONE_TORCH, 1),
                        new ItemStack(Material.WHITE_WOOL, 1),
                        new ItemStack(Material.PISTON, 1),
                        new ItemStack(Material.STICKY_PISTON, 1),
                        new ItemStack(Material.ACACIA_BUTTON, 1),
                        new ItemStack(Material.LEVER, 1)
                )
                .build();
    }
}
