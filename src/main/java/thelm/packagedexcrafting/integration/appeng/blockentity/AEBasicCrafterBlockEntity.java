package thelm.packagedexcrafting.integration.appeng.blockentity;

import appeng.api.config.Actionable;
import appeng.api.config.PowerMultiplier;
import appeng.api.config.PowerUnit;
import appeng.api.features.IPlayerRegistry;
import appeng.api.networking.GridHelper;
import appeng.api.networking.IGrid;
import appeng.api.networking.IGridNode;
import appeng.api.networking.IGridNodeListener;
import appeng.api.networking.IInWorldGridNodeHost;
import appeng.api.networking.IManagedGridNode;
import appeng.api.networking.energy.IEnergyService;
import appeng.api.networking.security.IActionHost;
import appeng.api.networking.security.IActionSource;
import appeng.api.networking.storage.IStorageService;
import appeng.api.stacks.AEItemKey;
import appeng.api.storage.MEStorage;
import appeng.api.storage.StorageHelper;
import appeng.api.util.AECableType;
import appeng.api.util.AEColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import thelm.packagedexcrafting.block.PackagedExCraftingBlocks;
import thelm.packagedexcrafting.block.entity.BasicCrafterBlockEntity;

public class AEBasicCrafterBlockEntity extends BasicCrafterBlockEntity implements IInWorldGridNodeHost, IGridNodeListener<AEBasicCrafterBlockEntity>, IActionHost {

	public boolean firstTick = true;
	public IActionSource source;
	public IManagedGridNode gridNode;

	public AEBasicCrafterBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
		source = IActionSource.ofMachine(this);
	}

	@Override
	public void tick() {
		if(firstTick) {
			firstTick = false;
			getMainNode().create(level, worldPosition);
		}
		super.tick();
		if(drawMEEnergy && !level.isClientSide && level.getGameTime() % 8 == 0) {
			chargeMEEnergy();
		}
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
	public IGridNode getGridNode(Direction dir) {
		return getActionableNode();
	}

	@Override
	public AECableType getCableConnectionType(Direction dir) {
		return AECableType.SMART;
	}

	@Override
	public void onSaveChanges(AEBasicCrafterBlockEntity nodeOwner, IGridNode node) {
		setChanged();
	}

	public IManagedGridNode getMainNode() {
		if(gridNode == null) {
			gridNode = GridHelper.createManagedNode(this, this);
			gridNode.setTagName("node");
			gridNode.setVisualRepresentation(PackagedExCraftingBlocks.BASIC_CRAFTER);
			gridNode.setGridColor(AEColor.TRANSPARENT);
			gridNode.setIdlePowerUsage(1);
			gridNode.setInWorldNode(true);
			if(ownerUUID != null && level instanceof ServerLevel) {
				gridNode.setOwningPlayerId(IPlayerRegistry.getMapping(level).getPlayerId(ownerUUID));
			}
		}
		return gridNode;
	}

	@Override
	public IGridNode getActionableNode() {
		return getMainNode().getNode();
	}

	@Override
	protected void ejectItems() {
		if(getMainNode().isActive()) {
			IGrid grid = getMainNode().getGrid();
			IStorageService storageService = grid.getStorageService();
			IEnergyService energyService = grid.getEnergyService();
			MEStorage inventory = storageService.getInventory();
			int endIndex = isWorking ? 9 : 0;
			for(int i = 9; i >= endIndex; --i) {
				ItemStack is = itemHandler.getStackInSlot(i);
				if(is.isEmpty()) {
					continue;
				}
				AEItemKey key = AEItemKey.of(is);
				int count = is.getCount();
				int inserted = (int)StorageHelper.poweredInsert(energyService, inventory, key, count, source, Actionable.MODULATE);
				if(inserted == count) {
					itemHandler.setStackInSlot(i, ItemStack.EMPTY);
				}
				else {
					itemHandler.setStackInSlot(i, key.toStack(count-inserted));
				}
			}
		}
		else {
			super.ejectItems();
		}
	}

	protected void chargeMEEnergy() {
		if(getMainNode().isActive()) {
			IGrid grid = getMainNode().getGrid();
			IEnergyService energyService = grid.getEnergyService();
			double conversion = PowerUnit.FE.convertTo(PowerUnit.AE, 1);
			int request = Math.min(energyStorage.getMaxReceive(), energyStorage.getMaxEnergyStored()-energyStorage.getEnergyStored());
			double available = energyService.extractAEPower((request+0.5)*conversion, Actionable.SIMULATE, PowerMultiplier.CONFIG);
			int extract = (int)(available/conversion);
			energyService.extractAEPower(extract*conversion, Actionable.MODULATE, PowerMultiplier.CONFIG);
			energyStorage.receiveEnergy(extract, false);
		}
	}

	@Override
	public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
		super.loadAdditional(nbt, registries);
		if(nbt.contains("node")) {
			getMainNode().loadFromNBT(nbt);
		}
	}

	@Override
	public void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
		super.saveAdditional(nbt, registries);
		if(gridNode != null) {
			gridNode.saveToNBT(nbt);
		}
	}
}
