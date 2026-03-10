package fr.lunix.bettermc;


import fr.lunix.bettermc.entity.ModMobs;
import fr.lunix.bettermc.init.ModBlockEntities;
import fr.lunix.bettermc.init.ModItems;
import fr.lunix.bettermc.init.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BetterMC.MODID)
public class BetterMC {

    public static final String MODID = "bettermc";

    public BetterMC()
    {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(bus);
        ModBlocks.ITEMS.register(bus);
        ModItems.ITEMS.register(bus);
        ModMobs.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
    }

    private void setup(FMLCommonSetupEvent e)
    {

    }

    private void clientSetup(FMLClientSetupEvent e)
    {
        net.minecraft.client.renderer.entity.EntityRenderers.register(
            fr.lunix.bettermc.entity.ModMobs.BETTER_SALMON.get(), fr.lunix.bettermc.entity.render.BetterSalmonRenderer::new
        );
    }
            event.register(new ResourceLocation(BetterMC.MODID, "block/pixel_block"));
        }
        @SubscribeEvent
        public static void onModelBake(ModelEvent.BakingCompleted event) {
        }
    }
}




