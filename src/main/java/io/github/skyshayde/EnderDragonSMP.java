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
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.entity.living.complex.EnderDragon;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.living.LivingDeathEvent;
import org.spongepowered.api.event.state.ServerStartedEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.config.DefaultConfig;

import java.io.File;

/**
 * Created by Skyshayde.
 * skyshayde.github.io
 */

@Plugin(id = "enderdragonsmp", name = "EnderDragonSMP", version = "0.1.0")
public class EnderDragonSMP {
    static Game game;
    ConfigManager ConfigManager = new ConfigManager(this);
    @Inject
    @DefaultConfig(sharedRoot = true)
    private File defaultConfig;
    @Inject
    @DefaultConfig(sharedRoot = true)
    private ConfigurationLoader<CommentedConfigurationNode> configManager;
    @Inject
    private Logger logger;

    public Logger getLogger() {
        return logger;
    }

    @Subscribe
    public void onServerStart(ServerStartedEvent event) {
        getLogger().info("Hello");
        ConfigManager.loadConfig(defaultConfig, configManager);
    }

    @Subscribe
    public void onLivingDeath(LivingDeathEvent event) {
        if (event.getLiving() instanceof EnderDragon && event.getEntity() instanceof Player) {
            Player killer = (Player) event.getEntity();

        }
    }


}
