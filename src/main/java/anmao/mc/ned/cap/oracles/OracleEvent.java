package anmao.mc.ned.cap.oracles;

import anmao.mc.ned.NED;
import anmao.mc.ned.lib.AdvancementHelper;
import anmao.mc.ned.oracle.Oracle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class OracleEvent {
    @Mod.EventBusSubscriber(modid = NED.MOD_ID)
    public class OracleAllEvent{
        private static final String[] ADVS = {
                "minecraft:story/smelt_iron",
                "minecraft:story/upgrade_tools",
                "minecraft:story/mine_diamond",
                "minecraft:story/lava_bucket",
                "minecraft:end/kill_dragon",
                "minecraft:adventure/sleep_in_bed",
                "minecraft:adventure/shoot_arrow",
                "minecraft:end/find_end_city",
                "minecraft:story/iron_tools",
                "minecraft:adventure/kill_all_mobs",
                "minecraft:story/enter_the_end",
                "minecraft:story/enter_the_nether"
        };
        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(OracleCap.class);
        }
        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player player) {
                if (!player.getCapability(OracleProvider.PLAYER_ORACLE).isPresent()) {
                    event.addCapability(new ResourceLocation(NED.MOD_ID, "oracle"), new OracleProvider());
                }
            }
        }
        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event){
            if (event.getEntity() instanceof ServerPlayer player) {
                event.getOriginal().reviveCaps();
                player.getCapability(OracleProvider.PLAYER_ORACLE).ifPresent(newDat -> event.getOriginal().getCapability(OracleProvider.PLAYER_ORACLE).ifPresent(newDat::copyFrom));

            }
        }
        @SubscribeEvent
        public static void onJoin(EntityJoinLevelEvent event){
            if (event.getEntity() instanceof ServerPlayer serverPlayer){
                serverPlayer.getCapability(OracleProvider.PLAYER_ORACLE).ifPresent(oracleCap -> {
                    if (oracleCap.getPace() < 1){
                        oracleCap.addPace();
                        serverPlayer.sendSystemMessage(oracleCap.getPaceTip());
                    }
                });
            }
        }
        @SubscribeEvent
        public static void onAdv(AdvancementEvent.AdvancementEarnEvent event){
            if (event.getEntity() instanceof ServerPlayer serverPlayer){
                //System.out.println("adv::"+ event.getAdvancement().getId());
                for (String id :ADVS) {
                    if (AdvancementHelper.isVanillaAdvancement(event.getAdvancement(), id)) {
                        serverPlayer.getCapability(OracleProvider.PLAYER_ORACLE).ifPresent(oracleCap -> {
                            if (!oracleCap.isMaxPlace()) {
                                oracleCap.addPace();
                                serverPlayer.sendSystemMessage(oracleCap.getPaceTip());
                            }
                        });
                        break;
                    }
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event){
            if (event.player instanceof ServerPlayer serverPlayer) {
                serverPlayer.getCapability(OracleProvider.PLAYER_ORACLE).ifPresent(oracleCap -> {
                    if (oracleCap.getOracle() == null){
                        oracleCap.addTime();
                        if (oracleCap.getOracle() != null){
                            oracleCap.getOracle().generateOracleDat(serverPlayer);
                        }
                    }else {
                        oracleCap.getOracle().run(event);
                    }
                });
            }
        }
        @SubscribeEvent
        public static void onPickup(EntityItemPickupEvent event){
            playerOracle(event.getEntity(), event);
        }

        public static void playerOracle(Player player, Event event){
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.getCapability(OracleProvider.PLAYER_ORACLE).ifPresent(oracleCap -> {
                    Oracle oracle = oracleCap.getOracle();
                    if (oracle != null) {
                        oracle.run(event);
                    }
                });
            }
        }
    }
}
