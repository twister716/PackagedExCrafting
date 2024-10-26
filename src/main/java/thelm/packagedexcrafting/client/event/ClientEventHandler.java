package thelm.packagedexcrafting.client.event;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import thelm.packagedexcrafting.block.entity.PackagedExCraftingBlockEntities;
import thelm.packagedexcrafting.client.renderer.CombinationCrafterRenderer;
import thelm.packagedexcrafting.client.renderer.MarkedPedestalRenderer;
import thelm.packagedexcrafting.client.screen.AdvancedCrafterScreen;
import thelm.packagedexcrafting.client.screen.BasicCrafterScreen;
import thelm.packagedexcrafting.client.screen.CombinationCrafterScreen;
import thelm.packagedexcrafting.client.screen.EliteCrafterScreen;
import thelm.packagedexcrafting.client.screen.EnderCrafterScreen;
import thelm.packagedexcrafting.client.screen.FluxCrafterScreen;
import thelm.packagedexcrafting.client.screen.UltimateCrafterScreen;
import thelm.packagedexcrafting.menu.PackagedExCraftingMenus;

public class ClientEventHandler {

	public static final ClientEventHandler INSTANCE = new ClientEventHandler();

	public static ClientEventHandler getInstance() {
		return INSTANCE;
	}

	public void onConstruct(IEventBus modEventBus) {
		modEventBus.register(this);
	}

	@SubscribeEvent
	public void onClientSetup(FMLClientSetupEvent event) {
		BlockEntityRenderers.register(PackagedExCraftingBlockEntities.COMBINATION_CRAFTER.get(), CombinationCrafterRenderer::new);
		BlockEntityRenderers.register(PackagedExCraftingBlockEntities.MARKED_PEDESTAL.get(), MarkedPedestalRenderer::new);
	}

	@SubscribeEvent
	public void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
		event.register(PackagedExCraftingMenus.BASIC_CRAFTER.get(), BasicCrafterScreen::new);
		event.register(PackagedExCraftingMenus.ADVANCED_CRAFTER.get(), AdvancedCrafterScreen::new);
		event.register(PackagedExCraftingMenus.ELITE_CRAFTER.get(), EliteCrafterScreen::new);
		event.register(PackagedExCraftingMenus.ULTIMATE_CRAFTER.get(), UltimateCrafterScreen::new);
		event.register(PackagedExCraftingMenus.ENDER_CRAFTER.get(), EnderCrafterScreen::new);
		event.register(PackagedExCraftingMenus.FLUX_CRAFTER.get(), FluxCrafterScreen::new);
		event.register(PackagedExCraftingMenus.COMBINATION_CRAFTER.get(), CombinationCrafterScreen::new);
	}
}
