package anmao.mc.ned.net;

import anmao.mc.ned.NED;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class NEDNetCore {
    private static final String PROTOCOL_VERSION = "1";
    private static int packetId = 0;
    private static int id(){
        return packetId++;
    }
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(NED.MOD_ID, "network.skill"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    public static void  reg(){
        //INSTANCE.registerMessage(id(), Packet_Index_ServerToClient.class,);

        INSTANCE.messageBuilder(SkillDataSendToClient.class,id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SkillDataSendToClient::new)
                .encoder(SkillDataSendToClient::toBytes)
                .consumerMainThread(SkillDataSendToClient::handle).add();
        //INSTANCE.messageBuilder(Packet_Index_Core.class,id()).decoder(Packet_Index_Core::Decoder).encoder(Packet_Index_Core::Encoder).consumerMainThread(Packet_Index_Core::handle).add();
        INSTANCE.messageBuilder(MobSkillDataSendToClient.class,id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(MobSkillDataSendToClient::new)
                .encoder(MobSkillDataSendToClient::toBytes)
                .consumerMainThread(MobSkillDataSendToClient::handle).add();
    }

    public static <MSG> void sendToServer(MSG msg){
        INSTANCE.sendToServer(msg);
    }
    public static <MSG> void sendToPlayer(MSG msg, Entity entity){
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(()->entity), msg);
    }
}
