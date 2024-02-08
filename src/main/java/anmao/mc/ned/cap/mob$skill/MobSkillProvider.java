package anmao.mc.ned.cap.mob$skill;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MobSkillProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<MobSkillCap> MOB_SKILLS = CapabilityManager.get(new CapabilityToken<MobSkillCap>() {
    });
    private MobSkillCap skills = null;
    private final LazyOptional<MobSkillCap> optional = LazyOptional.of(this::create);
    private MobSkillCap create() {
        if (skills == null){
            skills = new MobSkillCap();
        }
        return skills;
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == MOB_SKILLS){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        create().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        create().loadNBTData(nbt);
    }
}
