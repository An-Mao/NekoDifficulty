package anmao.mc.ned.lib;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.util.Map;
import java.util.Objects;

/**
 * 自定义注册储存核心
 * @param <I>
 *     id
 * @param <O>
 *     Obj
 */
public class _MapClassCore<I, O> {
    private static final Logger logger = LogUtils.getLogger();
    private ImmutableMap<I, Supplier<O>> map;
    public _MapClassCore(Map<I, Supplier<O>> initialMap){
        ImmutableMap.Builder<I, Supplier<O>> builder = ImmutableMap.builder();
        builder.putAll(initialMap);
        this.map = builder.build();
    }
    public O getClass(I id){
        return Objects.requireNonNull(map.get(id)).get();
    }
    public void clearData(){
        logger.info("Clearing data.");
        this.map = ImmutableMap.of();
    }
}
