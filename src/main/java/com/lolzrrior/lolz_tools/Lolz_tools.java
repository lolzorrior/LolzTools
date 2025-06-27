package com.lolzrrior.lolz_tools;

import com.lolzrrior.lolz_tools.events.ModEntityEvents;
import com.lolzrrior.lolz_tools.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lolz_tools implements ModInitializer {

    public static final String MOD_ID = "lolz_tools";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Hello From {}!", MOD_ID);
        LOGGER.info("Initializing {} ModItems...", MOD_ID);
        ModItems.initialize();
        LOGGER.info("{} ModItems initialized successfully.", MOD_ID);
        LOGGER.info("Initializing {} ModEntityEvents...", MOD_ID);
        ModEntityEvents.register();
        LOGGER.info("{} ModEntityEvents registered successfully.", MOD_ID);
    }
}
