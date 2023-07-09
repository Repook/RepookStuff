package com.repookstuff.item;

import com.repookstuff.ExampleMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    // new item registry
    public static final Item AMBER = registerItem("amber", new Item(new FabricItemSettings()));

    //adds items to custom item group
    private static void addItemsToIngredientsItemGroup(FabricItemGroupEntries entries){
        entries.add(AMBER);
    }
//what is used to register items
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(ExampleMod.MOD_ID,name),item);
    }
    //what registers items
    public static void registerModItems(){
        ExampleMod.LOGGER.info("Registering Mod Items for"+ExampleMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientsItemGroup);
    }
}
