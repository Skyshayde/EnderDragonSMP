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

import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.service.config.DefaultConfig;

import java.io.File;
import java.io.IOException;

/**
 * Created by Skyshayde.
 * skyshayde.github.io
 */
public class ConfigManager {
    private final EnderDragonSMP plugin;

    public int XP_DISTRIBUTION = 0;
    // 0 for original
    // 1 to split evenly between all players who took part
    // 2 to split based on damage dealt.  If you dealt 15% of the damage, you get 15% of the XP
    public int EGG_DISTRIBUTION = 0;
    // 0 for original
    // 1 to spawn egg entity where dragon died
    // 2 to directly give it to player who dealt most damage
    // 3 to give it to a random player who took part
    // 4 to give one to everyone
    @Inject
    @DefaultConfig(sharedRoot = true)
    private File defaultConfig;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private ConfigurationLoader<CommentedConfigurationNode> configManager;
    public ConfigManager(EnderDragonSMP plugin) {
        this.plugin = plugin;
    }

    public ConfigurationNode loadConfig(EnderDragonSMP main) {
        try {
            if (!defaultConfig.exists()) {
                defaultConfig.createNewFile();
                ConfigurationNode config = configManager.load();

                config.getNode("XP_Distribution").setValue(0);
                config.getNode("Egg_Distribution").setValue(0);
                configManager.save(config);
            }
            ConfigurationNode config = configManager.load();

            return config;

        } catch (IOException exception) {
            plugin.getLogger().error("The default configuration could not be loaded or created!");
            return null;
        }
    }
}
