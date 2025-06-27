package com.lolzrrior.lolz_tools.client;

import com.lolzrrior.lolz_tools.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Lolz_toolsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((itemStack, context, type, lines) -> {
            if (itemStack.getItem() == ModItems.LAUGHING_AX) {
                lines.add(Text.translatable("itemTooltip.lolz_tools.laughing_ax").formatted(Formatting.GOLD));
            }
        });
    }
}
