package net.araknid42.mcxdnd.item;

import net.araknid42.mcxdnd.MCxDND;
import net.araknid42.mcxdnd.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MCxDND.MOD_ID);

    /* Dice Tab */
    public static final RegistryObject<Item> D20 = ITEMS.register("d20", () -> new D20Item(new Item.Properties().durability(1)));
    public static final RegistryObject<Item> D12 = ITEMS.register("d12", () -> new D12Item(new Item.Properties().durability(1)));
    public static final RegistryObject<Item> D10 = ITEMS.register("d10", () -> new D10Item(new Item.Properties().durability(1)));
    public static final RegistryObject<Item> D8 = ITEMS.register("d8", () -> new D8Item(new Item.Properties().durability(1)));
    public static final RegistryObject<Item> D6 = ITEMS.register("d6", () -> new D6Item(new Item.Properties().durability(1)));
    public static final RegistryObject<Item> D4 = ITEMS.register("d4", () -> new D4Item(new Item.Properties().durability(1)));

    /* DND Weapons Tab */
    public static final RegistryObject<Item> DAGGER = ITEMS.register("dagger", () -> new Item(new Item.Properties().durability(1)));

    /* Spells Tab */
    public static final RegistryObject<Item> SPELLBOOK = ITEMS.register("spellbook", () -> new Item(new Item.Properties().durability(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
