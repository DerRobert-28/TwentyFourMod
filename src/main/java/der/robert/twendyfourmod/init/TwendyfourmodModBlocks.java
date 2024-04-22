
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package der.robert.twendyfourmod.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import der.robert.twendyfourmod.block.WorkbenchBlock;
import der.robert.twendyfourmod.TwendyfourmodMod;

public class TwendyfourmodModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK, TwendyfourmodMod.MODID);
	public static final DeferredHolder<Block, Block> WORKBENCH = REGISTRY.register("workbench", () -> new WorkbenchBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
