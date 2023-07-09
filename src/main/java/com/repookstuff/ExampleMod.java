package com.repookstuff;

import com.repookstuff.item.ModItems;
import com.repookstuff.util.ModLootTableModifiers;
import com.repookstuff.world.gen.treedecorator.StoneTreeDecorator;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	public static final String MOD_ID = "repooks-stuff";
    public static final Logger LOGGER = LoggerFactory.getLogger("repooks-stuff");

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModLootTableModifiers.modifyLootTables();
		StoneTreeDecorator.registerModTreeDecorators();
	}

}