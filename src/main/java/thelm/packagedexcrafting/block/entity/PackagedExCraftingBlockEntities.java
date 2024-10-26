package thelm.packagedexcrafting.block.entity;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import thelm.packagedauto.util.MiscHelper;
import thelm.packagedexcrafting.block.PackagedExCraftingBlocks;
import thelm.packagedexcrafting.integration.appeng.blockentity.AEAdvancedCrafterBlockEntity;
import thelm.packagedexcrafting.integration.appeng.blockentity.AEBasicCrafterBlockEntity;
import thelm.packagedexcrafting.integration.appeng.blockentity.AECombinationCrafterBlockEntity;
import thelm.packagedexcrafting.integration.appeng.blockentity.AEEliteCrafterBlockEntity;
import thelm.packagedexcrafting.integration.appeng.blockentity.AEEnderCrafterBlockEntity;
import thelm.packagedexcrafting.integration.appeng.blockentity.AEFluxCrafterBlockEntity;
import thelm.packagedexcrafting.integration.appeng.blockentity.AEMarkedPedestalBlockEntity;
import thelm.packagedexcrafting.integration.appeng.blockentity.AEUltimateCrafterBlockEntity;

public class PackagedExCraftingBlockEntities {

	private PackagedExCraftingBlockEntities() {}

	public static <T extends BlockEntity> Supplier<BlockEntityType<T>> of(BooleanSupplier condition, Supplier<Supplier<BlockEntityType.BlockEntitySupplier<? extends T>>> trueSupplier, Supplier<Supplier<BlockEntityType.BlockEntitySupplier<? extends T>>> falseSupplier, Supplier<Block>... validBlocks) {
		return ()->new BlockEntityType<>(MiscHelper.INSTANCE.conditionalSupplier(condition, trueSupplier, falseSupplier).get(), Arrays.stream(validBlocks).map(Supplier::get).filter(Objects::nonNull).collect(Collectors.toSet()), null);
	}

	public static final BooleanSupplier AE2_LOADED = ()->ModList.get().isLoaded("ae2");

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, "packagedexcrafting");

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BasicCrafterBlockEntity>> BASIC_CRAFTER = BLOCK_ENTITIES.register(
			"basic_crafter", of(AE2_LOADED, ()->()->AEBasicCrafterBlockEntity::new, ()->()->BasicCrafterBlockEntity::new, PackagedExCraftingBlocks.BASIC_CRAFTER));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AdvancedCrafterBlockEntity>> ADVANCED_CRAFTER = BLOCK_ENTITIES.register(
			"advanced_crafter", of(AE2_LOADED, ()->()->AEAdvancedCrafterBlockEntity::new, ()->()->AdvancedCrafterBlockEntity::new, PackagedExCraftingBlocks.ADVANCED_CRAFTER));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EliteCrafterBlockEntity>> ELITE_CRAFTER = BLOCK_ENTITIES.register(
			"elite_crafter", of(AE2_LOADED, ()->()->AEEliteCrafterBlockEntity::new, ()->()->EliteCrafterBlockEntity::new, PackagedExCraftingBlocks.ELITE_CRAFTER));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UltimateCrafterBlockEntity>> ULTIMATE_CRAFTER = BLOCK_ENTITIES.register(
			"ultimate_crafter", of(AE2_LOADED, ()->()->AEUltimateCrafterBlockEntity::new, ()->()->UltimateCrafterBlockEntity::new, PackagedExCraftingBlocks.ULTIMATE_CRAFTER));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EnderCrafterBlockEntity>> ENDER_CRAFTER = BLOCK_ENTITIES.register(
			"ender_crafter", of(AE2_LOADED, ()->()->AEEnderCrafterBlockEntity::new, ()->()->EnderCrafterBlockEntity::new, PackagedExCraftingBlocks.ENDER_CRAFTER));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FluxCrafterBlockEntity>> FLUX_CRAFTER = BLOCK_ENTITIES.register(
			"flux_crafter", of(AE2_LOADED, ()->()->AEFluxCrafterBlockEntity::new, ()->()->FluxCrafterBlockEntity::new, PackagedExCraftingBlocks.FLUX_CRAFTER));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CombinationCrafterBlockEntity>> COMBINATION_CRAFTER = BLOCK_ENTITIES.register(
			"combination_crafter", of(AE2_LOADED, ()->()->AECombinationCrafterBlockEntity::new, ()->()->CombinationCrafterBlockEntity::new, PackagedExCraftingBlocks.COMBINATION_CRAFTER));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MarkedPedestalBlockEntity>> MARKED_PEDESTAL = BLOCK_ENTITIES.register(
			"marked_pedestal", of(AE2_LOADED, ()->()->AEMarkedPedestalBlockEntity::new, ()->()->MarkedPedestalBlockEntity::new, PackagedExCraftingBlocks.MARKED_PEDESTAL));
}
