package thelm.packagedexcrafting.menu;

import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.items.SlotItemHandler;
import thelm.packagedauto.menu.BaseMenu;
import thelm.packagedauto.slot.RemoveOnlySlot;
import thelm.packagedexcrafting.block.entity.CombinationCrafterBlockEntity;
import thelm.packagedexcrafting.slot.CombinationCrafterRemoveOnlySlot;

public class CombinationCrafterMenu extends BaseMenu<CombinationCrafterBlockEntity> {

	public CombinationCrafterMenu(int windowId, Inventory inventory, CombinationCrafterBlockEntity blockEntity) {
		super(PackagedExCraftingMenus.COMBINATION_CRAFTER.get(), windowId, inventory, blockEntity);
		addSlot(new SlotItemHandler(itemHandler, 2, 8, 53));
		addSlot(new CombinationCrafterRemoveOnlySlot(blockEntity, 0, 53, 35));
		addSlot(new RemoveOnlySlot(itemHandler, 1, 107, 35));
		setupPlayerInventory();
	}
}
