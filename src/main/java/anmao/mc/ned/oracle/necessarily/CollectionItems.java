package anmao.mc.ned.oracle.necessarily;

import anmao.mc.amlib.math._Random;
import anmao.mc.amlib.time.TimeHelper;
import anmao.mc.ned.cap.oracles.OracleProvider;
import anmao.mc.ned.lib.ComponentHelp;
import anmao.mc.ned.lib.ItemHelper;
import anmao.mc.ned.oracle.Oracle;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.Event;

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
                            ComponentHelp.sendFormatMsgWithKey(serverPlayer,MSG_END_HAPPY);
                        });
                    }
                }
            }
        }else if (event instanceof TickEvent.PlayerTickEvent playerTickEvent && playerTickEvent.phase == TickEvent.Phase.START){
            if (playerTickEvent.player instanceof ServerPlayer serverPlayer){
                CompoundTag compoundTag = serverPlayer.getPersistentData().getCompound(getSaveKey());
                if (TimeHelper.getOverWorldTime(serverPlayer) - compoundTag.getLong("start") > compoundTag.getInt("time")) {
                    serverPlayer.getCapability(OracleProvider.PLAYER_ORACLE).ifPresent(oracleCap -> {
                        oracleCap.setOracle(null);
                        ComponentHelp.sendFormatMsgWithKey(serverPlayer,MSG_END_BAD);
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
            int number = _Random.getIntRandomNumber(1, itemStack.getMaxStackSize() * 10);
            int time = _Random.getIntRandomNumber(1, 7);
            nbt.putString("item", itemStack.getItem().toString());
            nbt.putInt("number", number);
            nbt.putLong("start", TimeHelper.getOverWorldTime((ServerPlayer) player));
            nbt.putInt("time", time);
            player.getPersistentData().put(getSaveKey(), nbt);

            ComponentHelp.sendFormatMsgWithKey((ServerPlayer) player, getDescriptionId(), time, number, itemStack.getDisplayName());
        }
    }
}
