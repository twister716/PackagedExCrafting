package thelm.packagedexcrafting.block;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PackagedExCraftingBlocks {

	private PackagedExCraftingBlocks() {}

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks("packagedexcrafting");

	public static final DeferredBlock<Block> BASIC_CRAFTER = BLOCKS.register("basic_crafter", BasicCrafterBlock::new);
	public static final DeferredBlock<Block> ADVANCED_CRAFTER = BLOCKS.register("advanced_crafter", AdvancedCrafterBlock::new);
	public static final DeferredBlock<Block> ELITE_CRAFTER = BLOCKS.register("elite_crafter", EliteCrafterBlock::new);
	public static final DeferredBlock<Block> ULTIMATE_CRAFTER = BLOCKS.register("ultimate_crafter", UltimateCrafterBlock::new);
	public static final DeferredBlock<Block> ENDER_CRAFTER = BLOCKS.register("ender_crafter", EnderCrafterBlock::new);
	public static final DeferredBlock<Block> FLUX_CRAFTER = BLOCKS.register("flux_crafter", FluxCrafterBlock::new);
	public static final DeferredBlock<Block> COMBINATION_CRAFTER = BLOCKS.register("combination_crafter", CombinationCrafterBlock::new);
	public static final DeferredBlock<Block> MARKED_PEDESTAL = BLOCKS.register("marked_pedestal", MarkedPedestalBlock::new);
}
