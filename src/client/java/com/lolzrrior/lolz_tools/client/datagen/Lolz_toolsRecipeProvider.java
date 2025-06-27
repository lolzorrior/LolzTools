package com.lolzrrior.lolz_tools.client.datagen;


import com.lolzrrior.lolz_tools.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class Lolz_toolsRecipeProvider extends FabricRecipeProvider {
    public Lolz_toolsRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                // Not currently needed
                // RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
                LOGGER.info("Generating recipes for Lolz Tools...");

                LOGGER.info("Creating Laughing Ax recipe...");
                createShaped(RecipeCategory.TOOLS, ModItems.LAUGHING_AX)
                        .pattern("gg")
                        .pattern("gs")
                        .pattern(" s")
                        .input('g', Items.GUNPOWDER)
                        .input('s', Items.STICK)
                        .group("lolz_tools")
                        .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
                        .offerTo(exporter);
                LOGGER.info("Laughing Ax recipe created successfully.");
            }
        };
    }

    @Override
    public String getName() {
        return "Lolz_toolsRecipeProvider";
    }
}
