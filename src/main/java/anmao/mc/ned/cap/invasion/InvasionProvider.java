package anmao.mc.ned.cap.invasion;

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

public class InvasionProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<Invasion> levelInvasion = CapabilityManager.get(new CapabilityToken<Invasion>() {
    });
    private Invasion invasion = null;
    private final LazyOptional<Invasion> optional = LazyOptional.of(this::create);

    private Invasion create() {
        if (invasion == null){
            invasion = new Invasion();
        }
        return invasion;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == levelInvasion){
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
