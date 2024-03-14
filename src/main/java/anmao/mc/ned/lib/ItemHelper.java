package anmao.mc.ned.lib;

import anmao.mc.amlib.math._Random;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemHelper {
    public static List<Item> ITEMS = new ArrayList<>();
    public static Item getItem(String id){
        if (Objects.equals(id, "")){
            return null;
        }
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
    }
    public static void loadAllItems(){
        ITEMS.clear();
        ForgeRegistries.ITEMS.forEach(item -> ITEMS.add(item));
    }
    public static Item getRandomItem(){
        int s = ITEMS.size();
        if (s < 1){
            return Items.DIAMOND;
        }else {
            s = _Random.getIntRandomNumber(0,s - 1);
            return ITEMS.get(s);
        }
    }
}
