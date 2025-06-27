package com.lolzrrior.lolz_tools.events;

import com.lolzrrior.lolz_tools.item.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class ModEntityEvents {
    public static void register() {

        // Registers an event listener for when a ZombieEntity is loaded by the server.
        // When a zombie is loaded, it has a 5% chance to spawn with a Laughing Ax in its main hand.
        // This also forces zombies to always drop their main hand item when killed
        // and allows them to pick up loot.
        ServerEntityEvents.ENTITY_LOAD.register(((entity, serverWorld) -> {
            // Only applies to Zombies loaded by the server
            if (!(entity instanceof ZombieEntity zombie)) return;
            if (serverWorld.isClient()) return;

            if (serverWorld.getRandom().nextFloat() < 0.05f) {
                zombie.setStackInHand(Hand.MAIN_HAND, new ItemStack(ModItems.LAUGHING_AX));
                zombie.setEquipmentDropChance(EquipmentSlot.MAINHAND, 1.0f);
                zombie.setCanPickUpLoot(true);
            }
        }));

    }
}
