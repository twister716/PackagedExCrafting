package thelm.packagedexcrafting.creativetab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import thelm.packagedexcrafting.item.PackagedExCraftingItems;

public class PackagedExCraftingCreativeTabs {

	private PackagedExCraftingCreativeTabs() {}

	public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "packagedexcrafting");

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_TABS.register(
			"tab", ()->CreativeModeTab.builder().
			title(Component.translatable("itemGroup.packagedexcrafting")).
			icon(PackagedExCraftingItems.ULTIMATE_CRAFTER::toStack).
			displayItems((parameters, output)->{
				output.accept(PackagedExCraftingItems.BASIC_CRAFTER);
				output.accept(PackagedExCraftingItems.ADVANCED_CRAFTER);
				output.accept(PackagedExCraftingItems.ELITE_CRAFTER);
				output.accept(PackagedExCraftingItems.ULTIMATE_CRAFTER);
				output.accept(PackagedExCraftingItems.ENDER_CRAFTER);
				output.accept(PackagedExCraftingItems.FLUX_CRAFTER);
				output.accept(PackagedExCraftingItems.COMBINATION_CRAFTER);
				output.accept(PackagedExCraftingItems.MARKED_PEDESTAL);
			}).build());
}
