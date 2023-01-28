package me.partlysunny.commands.subcommands;

import me.partlysunny.util.Util;
import me.partlysunny.util.classes.Gear;
import me.partlysunny.util.classes.ItemBuilder;
import me.partlysunny.util.classes.PotionBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class MiningKit extends KitSubCommand {
    @Override
    public String getId() {
        return "mining";
    }

    @Override
    protected Gear getGear() {
        return Gear.GearBuilder.builder()
                .setHotbar(
                        ItemBuilder.builder(Material.NETHERITE_PICKAXE)
                                .setName("Mining pickaxe")
                                .setUnbreakable(true)
                                .addEnchantment(Enchantment.DIG_SPEED, 100).build(),
                        PotionBuilder.builder(PotionBuilder.PotionFormat.SPLASH)
                                .setName("Diggy potion")
                                .setPotionData(PotionType.getByEffect(PotionEffectType.FAST_DIGGING), Color.fromRGB(1, 1, 1))
                                .addCustomEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100000000, 50, true, false))
                                .addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 100000000, 50, true, false))
                                .build(),
                        null, null, null, null, null, null, null
                )
                .build();
    }
}
