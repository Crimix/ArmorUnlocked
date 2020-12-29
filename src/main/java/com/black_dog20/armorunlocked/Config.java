package com.black_dog20.armorunlocked;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_GENERAL = "general";

    private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec SERVER_CONFIG;

    public static ForgeConfigSpec.BooleanValue FULL_UNLOCKED;

    static {

        SERVER_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        FULL_UNLOCKED = SERVER_BUILDER.comment("Is the max potential for damage reduction fully unlocked", "In Vanilla it is 80%", "If fullUnlocked = false it is 90%", "If fullUnlocked = true it is fully uncapped", "It also applies to mobs wearing armor")
                .define("fullUnlocked", false);
        SERVER_BUILDER.pop();
        SERVER_CONFIG = SERVER_BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }
}
