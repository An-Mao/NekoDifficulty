package anmao.mc.ned.cap.skill;

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

public class SkillProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<SkillCap> MOB_SKILLS = CapabilityManager.get(new CapabilityToken<SkillCap>() {
    });
    private SkillCap skills = null;
    private final LazyOptional<SkillCap> optional = LazyOptional.of(this::create);
    private SkillCap create() {
        if (skills == null){
            skills = new SkillCap();
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
