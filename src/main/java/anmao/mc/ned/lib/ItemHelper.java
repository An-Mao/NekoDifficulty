package anmao.mc.ned.lib;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class ItemHelper {
    public static List<Item> ITEMS = new ArrayList<>();
    public static Item getItem(String id){
        if (Objects.equals(id, "")){
            return null;
        }
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
    }

    /**
     * 初始化时调用
     */
    public static void loadAllItems(){
        ITEMS.clear();
        ForgeRegistries.ITEMS.forEach(item -> ITEMS.add(item));
    }
    public static Item getRandomItem(){
        int s = ITEMS.size();
        if (s < 1){
            return Items.DIAMOND;
        }else {
            s = _Math.getIntRandomNumber(0,s - 1);
            return ITEMS.get(s);
        }
    }
}
