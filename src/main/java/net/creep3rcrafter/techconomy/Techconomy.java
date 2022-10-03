package net.creep3rcrafter.techconomy;

import com.mojang.logging.LogUtils;
import net.creep3rcrafter.techconomy.commands.GiveCommand;
import net.creep3rcrafter.techconomy.register.ModBlocks;
import net.creep3rcrafter.techconomy.register.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Techconomy.MODID)
public class Techconomy {
    public static final String MODID = "techconomy";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Techconomy(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::commonSetup);
        ModItems.ITEMS.register(eventBus);
        ModBlocks.BLOCKS.register(eventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event){

    }
    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event){
        GiveCommand.register(event.getDispatcher());
    }
}
