package thelm.packagedexcrafting.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import thelm.packagedexcrafting.PackagedExCrafting;
import thelm.packagedexcrafting.client.event.ClientEventHandler;

@Mod(value = PackagedExCrafting.MOD_ID, dist = Dist.CLIENT)
public class PackagedExCraftingClient {

	public PackagedExCraftingClient(IEventBus modEventBus) {
		ClientEventHandler.getInstance().onConstruct(modEventBus);
	}
}
