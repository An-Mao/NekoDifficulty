package anmao.mc.ned.mob$skill;

import anmao.mc.ned.NED;
import anmao.mc.ned.config.Config;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.Event;

public abstract class MobSkill extends MobSkillCDT implements MobSkillInterface {
    private final String id;
    private final String fullId;
    private final ResourceLocation texture ;
    protected MobSkill(String id){
        this.id = id;
        this.fullId = "skill.ned." + id;
        texture = new ResourceLocation(NED.MOD_ID,"textures/skill/" + id + ".png");
    }
    @Override
    public boolean canAdd(String[] skills) {
        return true;
    }

    @Override
    public <T extends Event> void event(T event, CompoundTag dat) {
    }
    public String GetID() {
        return id;
    }
    public String getFullId(){
        return fullId;
    }

    public Component GetName() {
        return Component.translatable(getFullId());
    }
    public ResourceLocation getTexture() {
        return texture;
    }

    public JsonObject getSkillData(){
        return Config.getMobSkillConfig().getData(id);
    }
}
