/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Teddy Heinen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.github.skyshayde;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.File;
import java.io.IOException;

/**
 * Created by Skyshayde.
 * skyshayde.github.io
 */
public class ConfigManager {
    public static int XP_DISTRIBUTION = 0;
    // 0 for original
    // 1 to split evenly between all players who took part
    // 2 to split based on damage dealt.  If you dealt 15% of the damage, you get 15% of the XP
    public static int EGG_DISTRIBUTION = 0;

    // 0 for original.  No egg will be created if portal is disabled.
    // 1 to spawn egg entity where dragon died
    // 2 to directly give it to player who dealt most damage
    // 3 to give it to a random player who took part
    // 4 to give one to everyone
    public static boolean GENERATE_PORTAL = true;


    // true to create portal
    // false to disable portal creation
    private final EnderDragonSMP plugin;

    ConfigurationNode config;
    ConfigurationNode xpNode;
    ConfigurationNode eggNode;
    ConfigurationNode portalNode;

    public ConfigManager(EnderDragonSMP plugin) {
        this.plugin = plugin;
    }

    public ConfigurationNode loadConfig(File defaultConfig, ConfigurationLoader<CommentedConfigurationNode> configManager) {
        try {
            if (!defaultConfig.exists()) {
                defaultConfig.createNewFile();

                config.getNode("XP_Distribution").setValue(0);
                config.getNode("Egg_Distribution").setValue(0);
                config.getNode("Generate_Portal_on_Death").setValue(true);

                configManager.save(config);
            }
            config = configManager.load();
            xpNode = config.getNode("XP_Distribution");
            eggNode = config.getNode("Egg_Distribution");
            portalNode = config.getNode("Generate_Portal_on_Death");

            XP_DISTRIBUTION = xpNode.getInt();
            EGG_DISTRIBUTION = eggNode.getInt();
            GENERATE_PORTAL = portalNode.getBoolean();

            checkConfigForMissingNodes(configManager);

            return config;

        } catch (IOException exception) {
            plugin.getLogger().error("The default configuration could not be loaded or created!");
            return null;
        }
    }

    public void checkConfigForMissingNodes(ConfigurationLoader<CommentedConfigurationNode> configManager) {
        try {
            ConfigurationNode config = configManager.load();
            ConfigurationNode xpNode = config.getNode("XP_Distribution");
            ConfigurationNode eggNode = config.getNode("Egg_Distribution");
            ConfigurationNode portalNode = config.getNode("Generate_Portal_on_Death");

            if (xpNode.getValue() == null) {
                plugin.getLogger().warn("Configuration node XP_Distribution doesn't exist and has been reset to default.  ");
                xpNode.setValue(0);

            }
            if (eggNode.getValue() == null) {
                plugin.getLogger().warn("Configuration node Egg_Distribution doesn't exist and has been reset to default.  ");
                eggNode.setValue(0);

            }
            if (portalNode.getValue() == null) {
                plugin.getLogger().warn("Configuration node Generate_Portal_On_Death doesn't exist has been reset to default.  ");
                portalNode.setValue(true);

            }

            int xpNodeValue = xpNode.getInt();
            if (xpNodeValue < 0 || xpNodeValue > 2) {
                plugin.getLogger().warn("Configuration node XP_Distribution value is outside range and has been set to default.  ");
                xpNode.setValue(0);
            }
            int eggNodeValue = xpNode.getInt();
            if (eggNodeValue < 0 || eggNodeValue > 4) {
                plugin.getLogger().warn("Configuration node XP_Distribution value is outside range and has been set to default.  ");
                xpNode.setValue(0);
            }
            configManager.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
