package thelm.packagedexcrafting.integration.appeng.tile;

import com.mojang.authlib.GameProfile;

import appeng.api.IAppEngApi;
import appeng.api.config.Actionable;
import appeng.api.networking.GridFlags;
import appeng.api.networking.IGrid;
import appeng.api.networking.IGridHost;
import appeng.api.networking.IGridNode;
import appeng.api.networking.energy.IEnergyGrid;
import appeng.api.networking.security.IActionHost;
import appeng.api.networking.security.IActionSource;
import appeng.api.networking.storage.IStorageGrid;
import appeng.api.storage.IMEMonitor;
import appeng.api.storage.channels.IItemStorageChannel;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.util.AECableType;
import appeng.api.util.AEPartLocation;
import appeng.core.Api;
import appeng.me.helpers.MachineSource;
import appeng.util.Platform;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;
import thelm.packagedauto.integration.appeng.networking.BaseGridBlock;
import thelm.packagedexcrafting.tile.MarkedPedestalTile;

public class AEMarkedPedestalTile extends MarkedPedestalTile implements IGridHost, IActionHost {

	public boolean firstTick = true;
	public BaseGridBlock<AEMarkedPedestalTile> gridBlock;
	public IActionSource source;
	public IGridNode gridNode;

	public AEMarkedPedestalTile() {
		super();
		gridBlock = new BaseGridBlock<>(this);
		source = new MachineSource(this);
		gridBlock.flags.remove(GridFlags.REQUIRE_CHANNEL);
	}

	@Override
	public void tick() {
		if(firstTick) {
			firstTick = false;
			if(!level.isClientSide) {
				getActionableNode().updateState();
			}
		}
		super.tick();
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		if(gridNode != null) {
			gridNode.destroy();
		}
	}

	@Override
	public void onChunkUnloaded() {
		super.onChunkUnloaded();
		if(gridNode != null) {
			gridNode.destroy();
		}
	}

	@Override
	public IGridNode getGridNode(AEPartLocation dir) {
		return getActionableNode();
	}

	@Override
	public AECableType getCableConnectionType(AEPartLocation dir) {
		return AECableType.SMART;
	}

	@Override
	public void securityBreak() {
		level.destroyBlock(worldPosition, true);
	}

	@Override
	public IGridNode getActionableNode() {
		if(gridNode == null && !Platform.isClient()) {
			IAppEngApi api = Api.instance();
			gridNode = api.grid().createGridNode(gridBlock);
			if(ownerUUID != null) {
				gridNode.setPlayerID(api.registries().players().getID(new GameProfile(ownerUUID, "[UNKNOWN]")));
			}
		}
		return gridNode;
	}

	@Override
	public void ejectItem() {
		if(getActionableNode().isActive()) {
			IGrid grid = getActionableNode().getGrid();
			IStorageGrid storageGrid = grid.getCache(IStorageGrid.class);
			IEnergyGrid energyGrid = grid.getCache(IEnergyGrid.class);
			IItemStorageChannel storageChannel = Api.instance().storage().getStorageChannel(IItemStorageChannel.class);
			IMEMonitor<IAEItemStack> inventory = storageGrid.getInventory(storageChannel);
			ItemStack is = itemHandler.getStackInSlot(0);
			if(!is.isEmpty()) {
				IAEItemStack stack = storageChannel.createStack(is);
				IAEItemStack rem = Api.instance().storage().poweredInsert(energyGrid, inventory, stack, source, Actionable.MODULATE);
				if(rem == null || rem.getStackSize() == 0) {
					itemHandler.setStackInSlot(0, ItemStack.EMPTY);
				}
				else if(rem.getStackSize() < stack.getStackSize()) {
					itemHandler.setStackInSlot(0, rem.createItemStack());
				}
			}
		}
		super.ejectItem();
	}

	@Override
	public void load(BlockState blockState, CompoundNBT nbt) {
		super.load(blockState, nbt);
		if(nbt.contains("Node")) {
			getActionableNode().loadFromNBT("Node", nbt);
		}
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		super.save(nbt);
		if(gridNode != null) {
			gridNode.saveToNBT("Node", nbt);
		}
		return nbt;
	}
}
