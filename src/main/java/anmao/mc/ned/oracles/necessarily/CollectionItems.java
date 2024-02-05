package anmao.mc.ned.oracles.necessarily;

import anmao.mc.ned.cap.oracles.OracleProvider;
import anmao.mc.ned.lib.ComponentHelp;
import anmao.mc.ned.lib.ItemHelper;
import anmao.mc.ned.lib._Math;
import anmao.mc.ned.oracles.Oracle;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.registries.RegistryObject;

public class CollectionItems extends Oracle {
    public CollectionItems() {
        super("CollectionItems");
    }
    @Override
    public <T extends Event> void run(T event) {
        if (event instanceof EntityItemPickupEvent pickupEvent){
            if (pickupEvent.getEntity() instanceof ServerPlayer serverPlayer){
                CompoundTag compoundTag = serverPlayer.getPersistentData().getCompound(getSaveKey());
                Item item = ItemHelper.getItem(compoundTag.getString("item"));
                if (item != null){
                    Inventory inv = serverPlayer.getInventory();
                    int itemNumber = inv.countItem(item);
                    if (itemNumber >= compoundTag.getInt("number")){
                        serverPlayer.getCapability(OracleProvider.PLAYER_ORACLE).ifPresent(oracleCap -> {
                            oracleCap.setOracle(null);
                            ComponentHelp.sendFormatMsg(serverPlayer,MSG_END_HAPPY);
                        });
                    }
                }
            }
        }else if (event instanceof TickEvent.PlayerTickEvent playerTickEvent && playerTickEvent.phase == TickEvent.Phase.START){
            if (playerTickEvent.player instanceof ServerPlayer serverPlayer){
                CompoundTag compoundTag = serverPlayer.getPersistentData().getCompound(getSaveKey());
                if (serverPlayer.getServer().overworld().getGameTime() - compoundTag.getLong("start") > compoundTag.getInt("time")) {
                    serverPlayer.getCapability(OracleProvider.PLAYER_ORACLE).ifPresent(oracleCap -> {
                        oracleCap.setOracle(null);
                        ComponentHelp.sendFormatMsg(serverPlayer,MSG_END_BAD);
                        penalty(serverPlayer);
                    });
                }
            }
        }
    }
    @Override
    public void generateOracleDat(Player player) {
        //获取随机物品
        //Item item = ItemHelper.getRandomItem();
        if (player != null) {
            CompoundTag nbt = new CompoundTag();
            ItemStack itemStack = new ItemStack(ItemHelper.getRandomItem());
            int number = _Math.getIntRandomNumber(1, itemStack.getMaxStackSize() * 10);
            int time = _Math.getIntRandomNumber(1, 7);
            nbt.putString("item", itemStack.getItem().toString());
            nbt.putInt("number", number);
            nbt.putLong("start", player.getServer().overworld().getGameTime());
            nbt.putInt("time", time);
            player.getPersistentData().put(getSaveKey(), nbt);
            ComponentHelp.sendFormatMsg((ServerPlayer) player, getDescriptionId(), time, number, itemStack.getDisplayName());
        }
    }
}
