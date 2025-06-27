package com.lolzrrior.lolz_tools.client.datagen;

import com.lolzrrior.lolz_tools.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class Lolz_toolsEnglishLangProvider extends FabricLanguageProvider {
    protected Lolz_toolsEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }
    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.LAUGHING_AX, "Laughing Ax");
        translationBuilder.add("itemTooltip.lolz_tools.laughing_ax", "Only a true maniac could wield this ax.");
        translationBuilder.add("text.lolz_tools.laughing_ax.use", "A wild cackling fills the air around you.");
    }
}
