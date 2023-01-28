package me.partlysunny.commands.subcommands;

import me.partlysunny.util.classes.Gear;
import me.partlysunny.util.classes.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class CombatKit extends KitSubCommand {
    @Override
    public String getId() {
        return "combat";
    }

    @Override
    protected Gear getGear() {
        return Gear.GearBuilder.builder()
                .setArmor(
                        ItemBuilder.builder(Material.NETHERITE_BOOTS).setName("Combat boots").setUnbreakable(true)
                                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                .addEnchantment(Enchantment.THORNS, 3)
                                .addEnchantment(Enchantment.DEPTH_STRIDER, 3)
                                .addEnchantment(Enchantment.PROTECTION_FALL, 4).build(),
                        ItemBuilder.builder(Material.NETHERITE_LEGGINGS).setName("Combat leggings").setUnbreakable(true)
                                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                .addEnchantment(Enchantment.THORNS, 3).build(),
                        ItemBuilder.builder(Material.NETHERITE_CHESTPLATE).setName("Combat chestplate").setUnbreakable(true)
                                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                .addEnchantment(Enchantment.THORNS, 3).build(),
                        ItemBuilder.builder(Material.NETHERITE_HELMET).setName("Combat helmet").setUnbreakable(true)
                                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                                .addEnchantment(Enchantment.THORNS, 3)
                                .addEnchantment(Enchantment.WATER_WORKER, 1)
                                .addEnchantment(Enchantment.OXYGEN, 3).build()
                )
                .setHotbar(
                        ItemBuilder.builder(Material.NETHERITE_SWORD).setName("Combat sword").setUnbreakable(true)
                                .addEnchantment(Enchantment.DAMAGE_ALL, 4).build(),
                        ItemBuilder.builder(Material.ENCHANTED_GOLDEN_APPLE).setAmount(64).build(),
                        null, null, null, null, null, null, null
                )
                .setOffHand(
                        ItemBuilder.builder(Material.SHIELD).setName("Combat shield").setUnbreakable(true).build()
                )
                .build();
    }
}
