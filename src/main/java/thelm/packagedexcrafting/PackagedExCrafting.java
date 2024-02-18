package thelm.packagedexcrafting;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thelm.packagedexcrafting.block.BlockAdvancedCrafter;
import thelm.packagedexcrafting.block.BlockBasicCrafter;
import thelm.packagedexcrafting.block.BlockCombinationCrafter;
import thelm.packagedexcrafting.block.BlockEliteCrafter;
import thelm.packagedexcrafting.block.BlockEnderCrafter;
import thelm.packagedexcrafting.block.BlockUltimateCrafter;
import thelm.packagedexcrafting.proxy.CommonProxy;
import thelm.packagedexcrafting.tile.TileAdvancedCrafter;
import thelm.packagedexcrafting.tile.TileBasicCrafter;
import thelm.packagedexcrafting.tile.TileCombinationCrafter;
import thelm.packagedexcrafting.tile.TileEliteCrafter;
import thelm.packagedexcrafting.tile.TileEnderCrafter;
import thelm.packagedexcrafting.tile.TileUltimateCrafter;

@Mod(
		modid = PackagedExCrafting.MOD_ID,
		name = PackagedExCrafting.NAME,
		version = PackagedExCrafting.VERSION,
		dependencies = PackagedExCrafting.DEPENDENCIES,
		guiFactory = PackagedExCrafting.GUI_FACTORY
		)
public class PackagedExCrafting {

	public static final String MOD_ID = "packagedexcrafting";
	public static final String NAME = "PackagedExCrafting";
	public static final String VERSION = "1.12.2-0@VERSION@";
	public static final String DEPENDENCIES = "required-after:packagedauto@[1.12.2-1.0.10,);required-after:extendedcrafting@[1.5.6,);";
	public static final String GUI_FACTORY = "thelm.packagedexcrafting.client.gui.GuiPackagedExCraftingConfigFactory";
	public static final CreativeTabs CREATIVE_TAB = new CreativeTabs("packagedexcrafting") {
		@SideOnly(Side.CLIENT)
		@Override
		public ItemStack createIcon() {
			if(TileUltimateCrafter.enabled) {
				return new ItemStack(BlockUltimateCrafter.INSTANCE);
			}
			if(TileCombinationCrafter.enabled) {
				return new ItemStack(BlockCombinationCrafter.INSTANCE);
			}
			if(TileEliteCrafter.enabled) {
				return new ItemStack(BlockEliteCrafter.INSTANCE);
			}
			if(TileAdvancedCrafter.enabled) {
				return new ItemStack(BlockAdvancedCrafter.INSTANCE);
			}
			if(TileEnderCrafter.enabled) {
				return new ItemStack(BlockEnderCrafter.INSTANCE);
			}
			if(TileBasicCrafter.enabled) {
				return new ItemStack(BlockBasicCrafter.INSTANCE);
			}
			return ItemStack.EMPTY;
		}
	};
	@SidedProxy(
			clientSide = "thelm.packagedexcrafting.proxy.ClientProxy",
			serverSide = "thelm.packagedexcrafting.proxy.CommonProxy",
			modId = PackagedExCrafting.MOD_ID)
	public static CommonProxy proxy;

	@EventHandler
	public void firstMovement(FMLPreInitializationEvent event) {
		proxy.register(event);
	}
}
