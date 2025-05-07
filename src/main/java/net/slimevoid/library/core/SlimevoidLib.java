/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version. This program is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU
 * Lesser General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>
 */
package net.slimevoid.library.core;

import net.slimevoid.library.ICommonProxy;
import net.slimevoid.library.core.lib.ConfigurationLib;
import net.slimevoid.library.util.helpers.SlimevoidHelper;
import net.slimevoid.library.util.json.JSONLoader;
import net.slimevoid.library.util.xml.XMLVariables;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
    modid = SlimevoidLib.MOD_ID,
    name = "Slimevoid Library",
    version = Tags.VERSION,
    dependencies = "required-after:Forge@[10.13.4.1614,)")
public class SlimevoidLib {

    public static final String MOD_ID = "SlimevoidLib";

    @SidedProxy(
        clientSide = "net.slimevoid.library.proxy.SV_ClientProxy",
        serverSide = "net.slimevoid.library.proxy.SV_CommonProxy")
    public static ICommonProxy proxy;

    @EventHandler
    public static void SlimevoidLibPreInit(FMLPreInitializationEvent event) {
        proxy.preInit();
        ConfigurationLib.preInit(event.getSuggestedConfigurationFile());
        SlimevoidHelper.init();
    }

    @EventHandler
    public static void SlimevoidLibInit(FMLInitializationEvent event) {

        proxy.preInit();
        SlimevoidHelper.init();
        ConfigurationLib.init();
        XMLVariables.registerDefaultXMLVariables();
    }

    @EventHandler
    public static void SlimevoidLibPostInit(FMLPostInitializationEvent event) {
        JSONLoader.loadJSON();
    }
}
