package thelm.packagedexcrafting.menu;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import thelm.packagedauto.menu.factory.PositionalBlockEntityMenuFactory;

public class PackagedExCraftingMenus {

	private PackagedExCraftingMenus() {}

	public static <C extends AbstractContainerMenu, T extends BlockEntity> Supplier<MenuType<C>> of(PositionalBlockEntityMenuFactory.Factory<C, T> factory) {
		return ()->IMenuTypeExtension.create(new PositionalBlockEntityMenuFactory<>(factory));
	}

	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, "packagedexcrafting");

	public static final DeferredHolder<MenuType<?>, MenuType<BasicCrafterMenu>> BASIC_CRAFTER = MENUS.register("basic_crafter", of(BasicCrafterMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<AdvancedCrafterMenu>> ADVANCED_CRAFTER = MENUS.register("advanced_crafter", of(AdvancedCrafterMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<EliteCrafterMenu>> ELITE_CRAFTER = MENUS.register("elite_crafter", of(EliteCrafterMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<UltimateCrafterMenu>> ULTIMATE_CRAFTER = MENUS.register("ultimate_crafter", of(UltimateCrafterMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<EnderCrafterMenu>> ENDER_CRAFTER = MENUS.register("ender_crafter", of(EnderCrafterMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<FluxCrafterMenu>> FLUX_CRAFTER = MENUS.register("flux_crafter", of(FluxCrafterMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CombinationCrafterMenu>> COMBINATION_CRAFTER = MENUS.register("combination_crafter", of(CombinationCrafterMenu::new));
}
