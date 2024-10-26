package thelm.packagedexcrafting.menu;

import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.items.SlotItemHandler;
import thelm.packagedauto.menu.BaseMenu;
import thelm.packagedauto.slot.RemoveOnlySlot;
import thelm.packagedexcrafting.block.entity.EnderCrafterBlockEntity;
import thelm.packagedexcrafting.slot.EnderCrafterRemoveOnlySlot;

public class EnderCrafterMenu extends BaseMenu<EnderCrafterBlockEntity> {

	public EnderCrafterMenu(int windowId, Inventory inventory, EnderCrafterBlockEntity blockEntity) {
		super(PackagedExCraftingMenus.ENDER_CRAFTER.get(), windowId, inventory, blockEntity);
		addSlot(new SlotItemHandler(itemHandler, 10, 8, 53));
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				addSlot(new EnderCrafterRemoveOnlySlot(blockEntity, i*3+j, 44+j*18, 17+i*18));
			}
		}
		addSlot(new RemoveOnlySlot(itemHandler, 9, 134, 35));
		setupPlayerInventory();
	}
}
