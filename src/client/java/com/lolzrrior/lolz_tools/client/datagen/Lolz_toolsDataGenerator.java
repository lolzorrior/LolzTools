package com.lolzrrior.lolz_tools.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import static com.lolzrrior.lolz_tools.Lolz_tools.LOGGER;

public class Lolz_toolsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        //entry point for the english language provider
        LOGGER.info("Adding Lolz tools language provider entry point...");
        pack.addProvider(Lolz_toolsEnglishLangProvider::new);
        //entry point for the recipe provider
        LOGGER.info("Adding Lolz tools recipe provider entry point...");
        pack.addProvider(Lolz_toolsRecipeProvider::new);
    }
}
