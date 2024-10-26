package thelm.packagedexcrafting.item;

import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import thelm.packagedexcrafting.block.PackagedExCraftingBlocks;

public class PackagedExCraftingItems {

	private PackagedExCraftingItems() {}

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("packagedexcrafting");

	public static final DeferredItem<?> BASIC_CRAFTER = ITEMS.registerSimpleBlockItem(PackagedExCraftingBlocks.BASIC_CRAFTER);
	public static final DeferredItem<?> ADVANCED_CRAFTER = ITEMS.registerSimpleBlockItem(PackagedExCraftingBlocks.ADVANCED_CRAFTER);
	public static final DeferredItem<?> ELITE_CRAFTER = ITEMS.registerSimpleBlockItem(PackagedExCraftingBlocks.ELITE_CRAFTER);
	public static final DeferredItem<?> ULTIMATE_CRAFTER = ITEMS.registerSimpleBlockItem(PackagedExCraftingBlocks.ULTIMATE_CRAFTER);
	public static final DeferredItem<?> ENDER_CRAFTER = ITEMS.registerSimpleBlockItem(PackagedExCraftingBlocks.ENDER_CRAFTER);
	public static final DeferredItem<?> FLUX_CRAFTER = ITEMS.registerSimpleBlockItem(PackagedExCraftingBlocks.FLUX_CRAFTER);
	public static final DeferredItem<?> COMBINATION_CRAFTER = ITEMS.registerSimpleBlockItem(PackagedExCraftingBlocks.COMBINATION_CRAFTER);
	public static final DeferredItem<?> MARKED_PEDESTAL = ITEMS.registerSimpleBlockItem(PackagedExCraftingBlocks.MARKED_PEDESTAL);
}
