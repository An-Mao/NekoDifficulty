package anmao.mc.ned.cap.oracles;

import anmao.mc.ned.cap.skill.SkillCap;
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

public class OracleProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<OracleCap> PLAYER_ORACLE = CapabilityManager.get(new CapabilityToken<OracleCap>() {
    });
    private OracleCap oracleCap = null;
    private final LazyOptional<OracleCap> optional = LazyOptional.of(this::create);
    private OracleCap create() {
        if (oracleCap == null){
            oracleCap = new OracleCap();
        }
        return oracleCap;
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_ORACLE){
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
