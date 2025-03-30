package net.araknid42.mcxdnd.item;

import net.araknid42.mcxdnd.MCxDND;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MCxDND.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DICE_TAB = CREATIVE_MODE_TABS.register("dice_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.D20.get()))
                    .title(Component.translatable("creativetab.mcxdnd.dice"))
                    .displayItems((itemDisplayParameters, output) ->{
                        output.accept(ModItems.D20.get());
                        output.accept(ModItems.D12.get());
                        output.accept(ModItems.D10.get());
                        output.accept(ModItems.D8.get());
                        output.accept(ModItems.D6.get());

                    }).build());

    public static final RegistryObject<CreativeModeTab> DND_WEAPONS_TAB = CREATIVE_MODE_TABS.register("dnd_weapons_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.D4.get()))
                    .withTabsBefore(DICE_TAB.getId())
                    .title(Component.translatable("creativetab.mcxdnd.weapons"))
                    .displayItems((itemDisplayParameters, output) ->{
                        output.accept(ModItems.D4.get());

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
