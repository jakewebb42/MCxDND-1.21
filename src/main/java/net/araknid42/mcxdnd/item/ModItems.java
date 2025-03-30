package net.araknid42.mcxdnd.item;

import net.araknid42.mcxdnd.MCxDND;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MCxDND.MOD_ID);

    public static final RegistryObject<Item> D20 = ITEMS.register("d20", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> D12 = ITEMS.register("d12", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> D10 = ITEMS.register("d10", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> D8 = ITEMS.register("d8", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> D6 = ITEMS.register("d6", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> D4 = ITEMS.register("d4", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
